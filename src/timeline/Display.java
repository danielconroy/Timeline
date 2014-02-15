/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.Iterator;
import java.text.DecimalFormat;

/** The Display Class takes timelines and graphically displays them in a new window.
 *
 * @author Daniel
 * @author Kayley
 */
public class Display extends JFrame{
    /** timeline stores the current timeline being displayed. */
    private static Timeline timeline;
    /**
     * Constructor
     * 
     * @param timeline Timeline to be displayed.
     */
    public Display(Timeline timeline){
        this.timeline = timeline;
        if(timeline.eventSize()<0){
            this.dispose();
        }
        initUI();
    }
    /**
     * Creates a new Surface and sets window size and close operations.
     */
    private void initUI(){
        setTitle(timeline.getTitle());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,250);
        add(new Surface(timeline));
        setLocationRelativeTo(null);
    }
    /**
     * Main Class for purposes of testing.
     * 
     * @param args 
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                Timeline tl = new Timeline("test");
                Category person = new Category("Person",Color.CYAN);
                Event e1 = new Event("first",100,person);
                e1.setEndDate(120);
                //tl.addEvent(e1);
                tl.addEvent(new Event("second",200,person));
                Display display = new Display(tl);
                display.setVisible(true);
            }
        });
    }
}
/** The Surface Class extend JPanel to be the field of vision of the current
 * graphics.
 * 
 * @author Daniel
 * @author Kayley
 */
class Surface extends JPanel{
    private Double factor;
    private ArrayList<Event> events = new ArrayList<Event>();
    private Double horizontalShift = 0.0;
    private Double zoom = 1.0;
    private Integer width;
    private JLabel descriptionLabel;
    
    /**
     * Constructor
     * 
     * @param timeline Timeline to be displayed.
     */
    protected Surface(Timeline timeline){
        setLayout(null);
        Iterator<Event> eventIterator = timeline.getEventIterator();
        while(eventIterator.hasNext())
            events.add(eventIterator.next());
    }
    /**
     * Method called when the parent JFrame needs to be repainted.
     * 
     * @param g The Graphics of the parent.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
    }
    /**
     * Does the legwork of repainting Surface, generating central line, marks,
     * and populating events and buttons.
     * 
     * @param g The Graphics of the parent.
     */
    private void doDrawing(Graphics g){
        removeAll();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.DARK_GRAY);
        Dimension size = getSize();
        width = size.width;
        Insets insets = getInsets();
        
        Integer start, end;
        
        //Setting start and end.
        if(events.isEmpty()){
            start = 1;
            end = 1;
        }else
            start = events.get(0).getStartDate();
            end = events.get(0).getStartDate();
        for(Iterator<Event> it = events.iterator();it.hasNext();){
            Event e = it.next();
            Integer sDate = e.getStartDate();
            Integer eDate = e.getEndDate();
            if(sDate<start)
                start = sDate;
            if(sDate>end)
                end = sDate;
            if(e.getEndDate()!=null){
                if(eDate<start)
                    start = eDate;
                if(eDate>end)
                    end = eDate;
            }else{
                end=start*2;
            }
        }
        
        Integer w = size.width - insets.left - insets.right;
        Integer h = size.height - insets.top - insets.bottom;
        
        Integer range = end - start;

        start = start-range/2;
        end = end+range/2;
        range = end - start;
        
        //to convert, y = (slope)x - start(slope)
        Double slope = w.doubleValue()/range;
        
        g2d.drawLine(0, h/2, w, h/2);
        Double increase = range.doubleValue()/10;
        drawLines(start,increase,slope,h,g2d);
        drawEvents(start,slope,h,g2d,this);
        addButtons(this,w,h);
    }
    /**
     * Maps x to the location of the pixel to display.
     * 
     * @param x The input value
     * @param slope The "slope" coefficient for display.
     * @param start The "intercept" for display.
     * @return The converted value.
     */
    private Double convert(Double x, Double slope, Double start){
        return (x-start)*slope*zoom;
    }
    /**
     * Draws the horizontal line and all measurement markings and numbers.
     * 
     * @param start Starting point to display.
     * @param increase Distance between marks.
     * @param slope "slope" coefficient for mapping purposes.
     * @param h Max height to display.
     * @param g2d Graphics on which to act.
     */
    private void drawLines(Integer start, Double increase, Double slope,
            Integer h, Graphics2D g2d){
        for(int i = -100; i<100; i++){
            Double current = start.doubleValue();
            Integer temp = current.intValue();
            current = temp.doubleValue();
            temp = increase.intValue();
            Double increase2 = temp.doubleValue()/zoom;
            current = current + increase2*i;
            Double convert = convert(current,slope,start.doubleValue());
            int x = convert.intValue();
            Integer y = h/2+h/32;
            g2d.drawLine(x+horizontalShift.intValue(),y,
                    x+horizontalShift.intValue(),h/2);
            DecimalFormat df = new DecimalFormat("#.0");
            g2d.drawString(""+df.format(current), x+horizontalShift.intValue()
                    , h/2);
        }
    }
    /**
     * Graphs the Events and corresponding demarcated lines.
     * 
     * @param start Starting point to display.
     * @param slope "slope" coefficient for mapping purposes.
     * @param h Max height to display.
     * @param g2d Graphics on which to act.
     * @param surface Parent Surface on which to paint.
     */
    private void drawEvents(Integer start, Double slope, Integer h,
            Graphics2D g2d, Surface surface){
        Integer height = surface.getHeight()/4;
        Integer vertShift = height/events.size();
        for(Iterator<Event> it = events.iterator();it.hasNext();){
            vertShift = vertShift-height/events.size();
            Event e = it.next();
            Integer eStart = e.getStartDate();
            Integer eEnd = e.getEndDate();
            Double begin = convert(eStart.doubleValue(),slope,start.doubleValue());
            Double finish;
            if(eEnd!=null){
                finish = convert(eEnd.doubleValue(),slope,start.doubleValue());
                g2d.drawLine(finish.intValue()+horizontalShift.intValue(),
                        h/2, finish.intValue()+horizontalShift.intValue(),
                        h/2-h/8+h/32+vertShift);
                g2d.drawLine(begin.intValue()+horizontalShift.intValue(),
                        h/2-h/8+h/32+vertShift, 
                        finish.intValue()+horizontalShift.intValue(),
                        h/2-h/8+h/32+vertShift);
            }
            g2d.drawLine(begin.intValue()+horizontalShift.intValue(), h/2, 
                    begin.intValue()+horizontalShift.intValue(), h/2-h/8+vertShift);
            JLabel label = new JLabel(e.getTitle());
            /*if(e.getDescription()!=null){
                descriptionLabel = new JLabel();
                final String name = e.getDescription();
               //descriptionLabel.setVisible(false);
                descriptionLabel.setLocation(begin.intValue()+horizontalShift.intValue(),
                    h/2-h/8+30+vertShift);
               descriptionLabel.setSize(100,10);
               label.addMouseListener(new MouseListener()
                    {
                    public void mouseClicked(MouseEvent arg0) {
                    }
                    public void mouseEntered(MouseEvent arg0) {
                        System.out.println("hello");
                        descriptionLabel.setText(name);
                        descriptionLabel.setVisible(true);
                    }
                    public void mouseExited(MouseEvent arg0) {
                    }
                    public void mousePressed(MouseEvent arg0) {
                    }
                    public void mouseReleased(MouseEvent arg0) {
                    }
                    });
                surface.add(descriptionLabel);
                
            }
            */
            label.setLocation(begin.intValue()+horizontalShift.intValue(),
                    h/2-h/8-10+vertShift);
            label.setSize(50,10);
            label.setForeground(e.getCategory().getColor());
            surface.add(label);
        }
    }
    /**
     * Paints buttons to the screen.
     * 
     * @param surface The parent surface on which to paint.
     * @param w The width of the paint area.
     * @param h The height of the paint area.
     */
    private void addButtons(Surface surface, Integer w, Integer h){
        JButton right = new JButton("Shift Right");
        right.setLocation(w/2+100,h-20);
        right.setSize(100,20);
        surface.add(right);
        JButton zIn = new JButton("Zoom In");
        zIn.setLocation(w/2,h-20);
        zIn.setSize(100,20);
        surface.add(zIn);
        JButton zOut = new JButton("Zoom Out");
        zOut.setLocation(w/2-100,h-20);
        zOut.setSize(100,20);
        surface.add(zOut);
        JButton left = new JButton("Shift Left");
        left.setLocation(w/2-200,h-20);
        left.setSize(100,20);
        surface.add(left);
        
        addActionListeners(right,zIn,zOut,left,surface);
    }
    /**
     * Adds ActionListeners to the buttons.
     * 
     * @param right The button to shift the viewer right.
     * @param zIn The button to zoom in.
     * @param zOut The button to zoom out.
     * @param left The button to shift the viewer left.
     * @param surface The parent Surface on which to paint.
     */
    private void addActionListeners(JButton right,JButton zIn,JButton zOut,
            JButton left, final Surface surface){
        right.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Thread(new Runnable(){
                    public void run(){
                        horizontalShift-=100;
                        System.out.println(horizontalShift);
                        surface.repaint();
                    }
                }).start();
            }
        });
        left.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Thread(new Runnable(){
                    public void run(){
                        horizontalShift+=100;
                        System.out.println(horizontalShift);
                        surface.repaint();
                    }
                }).start();
            }
        });
        zIn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Thread(new Runnable(){
                    public void run(){
                        zoom = zoom*2;
                        System.out.println("width "+width);
                        //horizontalShift = horizontalShift + width/4;
                        System.out.println(zoom);
                        System.out.println(horizontalShift);
                        surface.repaint();
                    }
                }).start();
            }
        });
        zOut.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Thread(new Runnable(){
                    public void run(){
                        zoom = zoom/2;
                        System.out.println("width "+width);
                       // horizontalShift = horizontalShift - width/4;
                        System.out.println(zoom);
                        System.out.println(horizontalShift);
                        surface.repaint();
                    }
                }).start();
            }
        });
    }
}
