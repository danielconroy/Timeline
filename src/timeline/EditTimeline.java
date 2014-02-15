
package timeline;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * A JFrame by which to edit a timeline.
 * 
 * @author Daniel
 * @author Kayley
 */
public class EditTimeline extends javax.swing.JFrame {
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
    private EditTimeline thisEditTimeline;
    private static ArrayList<EditEvent> openEditEvents;
    private ManageTimelines superManage;
    /**
     * Constructor
     * 
     * @param timeline The timeline to edit.
     * @param fileIO The fileIO by which to save.
     * @param superManage The parent window.
     */
    public EditTimeline(Timeline timeline, FileIO fileIO, ManageTimelines superManage) {
        openEditEvents = new ArrayList<EditEvent>();
        this.timeline = timeline;
        this.fileIO = fileIO;
        thisEditTimeline = this;
        this.superManage = superManage;
        superManage.addEditTimeline(thisEditTimeline);
        initComponents();
    }
    /**
     * The means by which the components are added.
     */
    private void initComponents() {
        
        setResizable(false);
        
        addWindowListener();
        
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
    }
    /**
     * A method by which to add window functionality.
     */
    private void addWindowListener(){
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);    
        addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e){
                superManage.removeEditTimeline(thisEditTimeline);
            }
        });
    }
    /**
     * Selects the event.
     * 
     * @param e The event selected.
     */
    public void setSelectedItem(Event e){
        jComboBox1.setSelectedItem(e.getTitle());
    }
    /**
     * Sets the ComboBox.
     */
    public void setComboBox(){
       Iterator<Event> eventIterator = timeline.getEventIterator();
       String[] names = new String[timeline.eventSize()+1];
       names[0] = "All Events";
       int i = 1;
       
       while(eventIterator.hasNext()){
           names[i++] = eventIterator.next().getTitle();
       }
        
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(names));
        selectedEvent = null;
    }
    /**
     * Adds an EditEvent window to the known children windows.
     * 
     * @param e The window to add.
     */
    public void addEditEvent(EditEvent e){
        openEditEvents.add(e);
    }
    /**
     * Removes an EditEvent window from the known children windows.
     * 
     * @param e The window to remove.
     */
    public void removeEditEvent(EditEvent e){
        openEditEvents.remove(e);
    }
    /**
     * Method to access the timeline being edited.
     * 
     * @return The timeline being edited.
     */
    public Timeline getTimeline(){
        return timeline;
    }
    /**
     * Method to access the children EditEvents windows.
     * 
     * @return An arraylist of EditEvent child windows.
     */
    public ArrayList<EditEvent> getEditEvents(){
        return openEditEvents;
    }
    /**
     * An ActionListener to edit an event.
     */
    private class ETListener implements ActionListener{
        /**
         * Constructor
         */
        public ETListener(){}
        /**
         * Implements the actionPerformed method to edit an event.
         * 
         * @param ae The actionEvent received.
         */
        public void actionPerformed(ActionEvent ae){
            JButton thisButton = (JButton) ae.getSource();
            if(thisButton == editButton){
                if(timeline.eventSize() == 0) return;
                if(selectedEvent == null) return;
                
                //You can't open two windows editing the same event at once!
                for(EditEvent e : openEditEvents)
                    if(e.getEvent().getTitle().equals(selectedEvent.getTitle()))
                        return;

                java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new EditEvent(selectedEvent, fileIO, timeline, thisEditTimeline).setVisible(true);
                }
                });

           }else if(thisButton == deleteButton){
               //numbers
               if(timeline.eventSize() == 0) return;
               if(selectedEvent != null){
                    for(EditEvent e : openEditEvents){
                        if(e.getEvent().getTitle().equals(selectedEvent.getTitle())){
                            e.dispose();
                            e.setVisible(false);
                            removeEditEvent(e);
                            return;
                        }
                    }  
                   timeline.deleteEvent(selectedEvent);
                   setComboBox();
               }
               fileIO.save();
           }else if(thisButton == createButton){
               String name = nameTextField.getText();
                for(EditEvent e : openEditEvents)
                    if(e.getEvent().getTitle().equals(name))
                        return;
                if(timeline.containsTitle(name)){
                    return;
                }
                java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    Event event = new Event(nameTextField.getText()); 
                    new EditEvent(event, fileIO, timeline, thisEditTimeline).setVisible(true);
                    setComboBox();                 
                }
                });
           }
        }
    }
    /**
     * A class to manage the ComboBox.
     */
    private class ComboBoxListener implements ActionListener{
        /**
         * Constructor
         */
        public ComboBoxListener(){}
        /**
         * Implements the actionPerformed method to select an event.
         * 
         * @param ae The actionEvent received.
         */
        public void actionPerformed(ActionEvent ae){
            JComboBox thisBox = (JComboBox) ae.getSource();

            Iterator<Event> eventIterator = timeline.getEventIterator();
            while(eventIterator.hasNext()){
                Event e = eventIterator.next();
                if(thisBox.getSelectedItem().equals(e.getTitle())){
                    selectedEvent = e;
                    break;
                }
                selectedEvent = null;
            }
        }
    }
}