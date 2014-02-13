
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class EditTimeline extends javax.swing.JFrame {

    /**
     * Creates new form EditTimeline
     */
    private Timeline timeline;
    private FileIO fileIO;
    private Event selectedEvent;
    private JButton editButton;
    private JButton createButton;
    private JButton deleteButton;
    private JComboBox jComboBox1;
    private JLabel titleLabel;
    private JLabel timelineLabel;
    private JTextField nameTextField;
    private EditTimeline thisET;
    
    public EditTimeline(Timeline timeline, FileIO fileIO) {
        this.timeline = timeline;
        this.fileIO = fileIO;
        thisET = this;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setResizable(false);
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int) ((d.getHeight() - getHeight()) / 2);        
        int x = (int) ((d.getWidth() - getWidth()) / 2);
        setLocation(x, y);
        
        titleLabel = new javax.swing.JLabel();
        timelineLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
        nameTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        setComboBox();
        jComboBox1.addActionListener(new ComboBoxListener());
        
        titleLabel.setText("Manage Events");
        timelineLabel.setText("              for Timeline "+timeline.getTitle());
        
        try{
            titleLabel.setFont(new Font("Vijaya", 0, 42));
        }catch(Exception e){
            titleLabel.setFont(new Font("Times new Roman", 0, 34));
        }        
        
        editButton.setText("Edit Event");
        deleteButton.setText("Delete Event");
        createButton.setText("Create Event");
        nameTextField.setText("<Name>");
        
        editButton.addActionListener(new ETListener());
        deleteButton.addActionListener(new ETListener());
        createButton.addActionListener(new ETListener());
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(titleLabel)
                        .addComponent(timelineLabel)
                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(editButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)    
                                    .addComponent(deleteButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(createButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                ))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addComponent(timelineLabel)    
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(createButton)
                .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton))
                .addComponent(deleteButton)
                ));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
public void setComboBox(){
    ArrayList<Event> events =  timeline.getEvents();
    String[] names = new String[events.size()+1];
    names[0] = "All Events";
    int i = 1;
    for(Event e : events){
            names[i++] = e.getTitle();
    }
    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(names));
}

private class ETListener implements ActionListener{
    
    public ETListener(){
    }
    
    public void actionPerformed(ActionEvent ae){
        JButton thisButton = (JButton) ae.getSource();
        if(thisButton == editButton){
            if(timeline.eventSize() == 0) return;
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditEvent(selectedEvent, fileIO, timeline, thisET).setVisible(true);
            }
            });
            
       }else if(thisButton == deleteButton){
           //numbers
           if(timeline.eventSize() == 0) return;
           if(selectedEvent.getTitle() != null){
               timeline.deleteEvent(selectedEvent);
               setComboBox();
           }
           fileIO.save();
       }else if(thisButton == createButton){          
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Event event = new Event(nameTextField.getText()); 
                new EditEvent(event, fileIO, timeline, thisET).setVisible(true);
                setComboBox();                 
            }
            });
       }
    }
    
    }

private class ComboBoxListener implements ActionListener{
    
    public ComboBoxListener(){
        
    }
    
    public void actionPerformed(ActionEvent ae){
        JComboBox thisBox = (JComboBox) ae.getSource();
        
        thisBox.getSelectedItem( ).equals( "female" );
        
        for(Event e : timeline.getEvents()){
            if(thisBox.getSelectedItem().equals(e.getTitle())){
                selectedEvent = e;
                break;
            }
        }

    }
}

}