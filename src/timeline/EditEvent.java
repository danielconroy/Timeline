package timeline;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

/**
 * This is a JFrame by which to edit events.
 * 
 * @author Daniel
 * @author Kayley
 */
public class EditEvent extends javax.swing.JFrame {

    /**
     * Creates new form EditEvent
     */
    private Event event;
    private Category selectedCategory;
    private Timeline timeline;
    private EditTimeline superTimeline;
    private EditEvent thisEditEvent;
    /**
     * Constructor
     * 
     * @param event The event to edit.
     * @param fileIO The fileIO by which to save.
     * @param timeline 
     * @param superTimeline The timeline to which the event belongs.
     */
    public EditEvent(Event event, FileIO fileIO, Timeline timeline,
            EditTimeline superTimeline) {
        this.event = event;
        this.fileIO = fileIO;
        this.timeline = timeline;
        this.superTimeline =superTimeline;
        thisEditEvent = this;
        superTimeline.addEditEvent(thisEditEvent);
        initComponents();
    }

    private final FileIO fileIO;
    private JButton finishedButton;
    private JButton refreshButton;
    private JComboBox jComboBox1;
    private JLabel titleLabel;
    private JTextField nameTextField;
    private JTextArea descriptionTextArea;
    private JTextField startTextField;
    private JTextField endTextField;
    private JScrollPane scroll;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        addWindowListener();
        
        setResizable(false);
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int) ((d.getHeight() - getHeight()) / 2);        
        int x = (int) ((d.getWidth() - getWidth()) / 2);
        setLocation(x, y);
        
        titleLabel = new JLabel();
        nameTextField = new JTextField();
        descriptionTextArea = new JTextArea();
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setBounds(10, 10, 136, 200);       
        scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.add(descriptionTextArea);
        scroll.setViewportView(descriptionTextArea);
        //scroll.setSize(136, 200);
        
        startTextField = new JTextField();
        endTextField = new JTextField();
        jComboBox1 = new JComboBox();
        finishedButton = new JButton();
        refreshButton = new JButton();
        
        try{
            titleLabel.setFont(new Font("Vijaya", 0, 42));
        }catch(Exception e){
            titleLabel.setFont(new Font("Times new Roman", 0, 34));
        }
        titleLabel.setText("Edit Event");

        fillTextFields();
        setComboBox();
        
        jComboBox1.addActionListener(new ComboBoxListener());

        finishedButton.setText("Finished");
        finishedButton.addActionListener(new EEListener());
        
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setComboBox();
            }
        });

        ImageIcon icon = createImageIcon("refresh.png",
                                 "refresh");
        
        refreshButton.setIcon(icon);


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(titleLabel)
                    .addComponent(nameTextField)
                    .addComponent(startTextField)
                    .addComponent(endTextField)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(finishedButton, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(refreshButton, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                    .addComponent(scroll)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)    
                .addComponent(scroll)    
                )
            .addComponent(refreshButton, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)     
                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(startTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(endTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(finishedButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

        pack();
    }
    /**
     * Fills in the textFields.
     */
    public void fillTextFields(){
        if(event.getTitle()!=null)
            nameTextField.setText(event.getTitle());
        else
            nameTextField.setText("<Name>");
        if(event.getDescription()!=null)
            descriptionTextArea.setText(event.getDescription());
        else
            descriptionTextArea.setText("<Des>");
        if(event.getStartDate()!=null)
            startTextField.setText(""+event.getStartDate());
        else
            startTextField.setText("<Start>");
        if(event.getEndDate()!=null)
            endTextField.setText(""+event.getEndDate());  
        else
            endTextField.setText("<End>");
    }
    /**
     * Sets the comboBox.
     */
    public void setComboBox(){
        Iterator<Category> categoryIterator =  fileIO.getCategoryIterator();
        String[] names = new String[fileIO.catSize()+1];
        int i = 0;
        int catIndex = 0;
        Category c = new Category("Base");
        while(categoryIterator.hasNext()){
            c = categoryIterator.next();
            names[i++] = c.getName();
        }
        names[i] = "New Category";
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(names));
        selectedCategory = c;
        jComboBox1.setSelectedItem(event.getCategory().getName());
    }
    
    /**
     * Validates the entered event.
     * 
     * @return true if valid event, false if not.
     */
    public boolean submitTextFields(){
        String name = nameTextField.getText();
        String description = descriptionTextArea.getText();
        String startDate = startTextField.getText();
        String endDate = endTextField.getText();
        Integer start, end;
        //Name and startDate are essential descriptors. The others are optional.
        if(name.equals("<Name>") || startDate.equals("<Start>")){
            JOptionPane.showMessageDialog(
                    null, "You must fill out the event name and start date!", 
                    "FATAL_ERROR", 
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try{
            start = Integer.parseInt(startDate);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(
                    null, "The start date has to be an integer!", 
                    "FATAL_ERROR", 
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        event.setTitle(name);
        event.setStartDate(start);
        event.setCategory(selectedCategory);
        
        if(!description.equals("<Des>")){
            event.setDescription(description);
        }
        if(!endDate.equals("<End>")){
            try{
                end = Integer.parseInt(endDate);
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(
                    null, "The end date must be an integer!", 
                    "FATAL_ERROR", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            } 
            //The end date can't happen before the starting date!
            if(end >= start){
                event.setEndDate(end);
            }else{
                JOptionPane.showMessageDialog(
                    null, "The end date must be later than the start date!", 
                    "JUST_TO_LET_YOU_KNOW", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        return true;
    }
    
    /** 
     * Returns an ImageIcon, or null if the path was invalid. 
     * Code by http://docs.oracle.com/javase/tutorial/uiswing/components/icon.html.
     */
    protected ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    /**
     * Adds the window functionality.
     */
    private void addWindowListener(){
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);    
        addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                superTimeline.removeEditEvent(thisEditEvent);
            }
        });
    }
    /**
     * Returns the event being edited.
     * 
     * @return The event being edited.
     */
    public Event getEvent(){
        return event;
    }
    /**
     * Listener to complete the editing process.
     */
    private class EEListener implements ActionListener{
        /**
         * Constructor
         */
        public EEListener(){}
        /**
         * Implements the actionPerformed method to save and dispose.
         * 
         * @param ae The ActionEvent received.
         */
        public void actionPerformed(ActionEvent ae){
            JButton thisButton = (JButton) ae.getSource();
            if(thisButton == finishedButton){
                if(submitTextFields()){
                    timeline.addEvent(event);
                    superTimeline.setComboBox();   
                    superTimeline.setSelectedItem(event);
                    fileIO.save();
                    superTimeline.removeEditEvent(thisEditEvent);
                    setVisible(false);
                    dispose();
                }
            }
        }
    }
    /**
     * The ComboBoxListener.
     */
    private class ComboBoxListener implements ActionListener{
        /**
         * Constructor
         */
        public ComboBoxListener(){}
        /**
         * Implements the actionPerformed method to select the category.
         * 
         * @param ae The ActionEvent received.
         */
        public void actionPerformed(ActionEvent ae){
            JComboBox thisBox = (JComboBox) ae.getSource();

            if(thisBox.getSelectedItem().equals("New Category")){
                  java.awt.EventQueue.invokeLater(new Runnable() {
                       public void run() {
                            new EditCategory(new Category("<New>"), fileIO, thisEditEvent).setVisible(true);
                       }
                   });
                   return;
            }

            Iterator<Category> categoryIterator =  fileIO.getCategoryIterator();
            Category c;
            while(categoryIterator.hasNext()){
                c = categoryIterator.next();
                if(thisBox.getSelectedItem().equals(c.getName())){
                    selectedCategory = c;
                    break;
                }            
            }
        }
    }
}