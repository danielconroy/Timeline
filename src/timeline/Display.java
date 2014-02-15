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
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.Iterator;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JButton;

/**
 *
 * @author Daniel
 */
public class Display extends JFrame{
    private static Timeline timeline;
    public Display(Timeline timeline){
        this.timeline = timeline;
        initUI();
    }
    private void initUI(){
        setTitle(timeline.getTitle());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,250);
        add(new Surface(timeline));
        setLocationRelativeTo(null);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                Timeline tl = new Timeline("test");
                Category person = new Category("Person",Color.CYAN);
                tl.addEvent(new Event("first",10,person));
                tl.addEvent(new Event("second",45,person));
                Display display = new Display(tl);
                display.setVisible(true);
            }
        });
    }
}
class Surface extends JPanel{
    private Double factor;
    private ArrayList<Event> events = new ArrayList<Event>();
    private Integer horizontalShift = 0;
    private Integer zoom = 0;
    protected Surface(Timeline timeline){
        setLayout(null);
        Iterator<Event> eventIterator = timeline.getEventIterator();
        while(eventIterator.hasNext())
            events.add(eventIterator.next());
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
    }
    private void doDrawing(Graphics g){
        removeAll();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.DARK_GRAY);
        Dimension size = getSize();
        Insets insets = getInsets();
        
        Integer start, end;
        
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
    private Double convert(Double x, Double slope, Double start){
        return slope*x-start*slope;
    }
    private void drawLines(Integer start, Double increase, Double slope,
            Integer h, Graphics2D g2d){
        for(int i = -20; i<20; i++){
            Double current = start.doubleValue();
            Integer temp = current.intValue();
            current = temp.doubleValue();
            temp = increase.intValue();
            Double increase2 = temp.doubleValue();
            current = current + increase2*i;
            Double convert = convert(current,slope,start.doubleValue());
            int x = convert.intValue();
            Integer y = h/2+h/32;
            g2d.drawLine(x,y,x,h/2);
            DecimalFormat df = new DecimalFormat("#.0");
            g2d.drawString(""+df.format(current), x, h/2);
        }
    }
    private void drawEvents(Integer start, Double slope, Integer h,
            Graphics2D g2d, Surface surface){
        for(Iterator<Event> it = events.iterator();it.hasNext();){
            Event e = it.next();
            Integer eStart = e.getStartDate();
            Integer eEnd = e.getEndDate();
            Double begin = convert(eStart.doubleValue(),slope,start.doubleValue());
            Double finish;
            if(eEnd!=null)
                finish = convert(eEnd.doubleValue(),slope,start.doubleValue());
            else
                finish = begin;
            g2d.drawLine(begin.intValue(), h/2, begin.intValue(), h/2-h/8);
            JLabel label = new JLabel(e.getTitle());
            label.setLocation(begin.intValue(), h/2-h/8-10);
            label.setSize(50,10);
            surface.add(label);
        }
    }
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
        
        addActionListeners(right,zIn,zOut,left);
    }
    private void addActionListeners(JButton right,JButton zIn,JButton zOut,
            JButton left){
        
    }
}
