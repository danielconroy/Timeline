
package timeline;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

/**
 * JFrame by which to manage timelines.
 * 
 * @author Daniel
 */
public class ManageTimelines extends javax.swing.JFrame {
    private FileIO fileIO;
    private Timeline selectedTimeline = null;
    private static ArrayList<EditTimeline> openEditTimelines;
    private ManageTimelines thisManageTimelines;
    
    /**
     * Constructor
     * 
     * @param fileIO The FileIO by which to save.
     */
    public ManageTimelines(FileIO fileIO) {
        openEditTimelines = new ArrayList<EditTimeline>();
        this.fileIO = fileIO;
        thisManageTimelines = this;
        initComponents();
    }
    
    private javax.swing.JButton manageButton;
    private javax.swing.JButton createButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField nameTextField;


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
        jComboBox1 = new javax.swing.JComboBox();
        manageButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
        nameTextField = new javax.swing.JTextField();
        nameTextField.setDocument(new JTextFieldLimit(12));

        deleteButton = new javax.swing.JButton();
        
        manageButton.addActionListener(new MTListener());
        createButton.addActionListener(new MTListener());
        deleteButton.addActionListener(new MTListener());
        
        jComboBox1.addActionListener(new ComboBoxListener());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setText("Manage Timelines");

        try{
            titleLabel.setFont(new Font("Vijaya", 0, 42));
        }catch(Exception e){
            titleLabel.setFont(new Font("Times new Roman", 0, 34));
        }
                
        setComboBox();

        manageButton.setText("Edit Timeline");
        createButton.setText("Create Timeline");
        nameTextField.setText("<Name>");
        deleteButton.setText("Delete Timeline");
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup()
                         .addComponent(titleLabel)    
                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(manageButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)    
                                    .addComponent(deleteButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(createButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                ))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(createButton)
                .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageButton))
                .addComponent(deleteButton)
                ));

        pack();
        
    }
    /**
     * Sets the combobox with timelines known to FileIO.
     */
    private void setComboBox(){
        Iterator<Timeline> timelineIterator =  fileIO.getTimelineIterator();
        String[] names = new String[fileIO.timeSize()+1];
        int i = 1;
        names[0] = "All Timelines";
        Timeline t;
        while(timelineIterator.hasNext()){
            t = timelineIterator.next();
            names[i++] = t.getTitle();
        }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(names));
    }
    /**
     * Deletes a timeline.
     */
    private void deleteTimeline(){
        //Iterating over two ArrayLists.
        new Thread(new Runnable(){
                public void run(){
            for(EditTimeline et : openEditTimelines){
                    if(et.getTimeline().getTitle().equals(selectedTimeline.getTitle())){
                        for(EditEvent ee : et.getEditEvents()){
                            ee.dispose();
                            ee.setVisible(false);
                            }  
                        et.dispose();
                        et.setVisible(false);
                        removeEditTimeline(et);
                        break;
                        }
            }

            fileIO.deleteTimeline(selectedTimeline);
            setComboBox();
            fileIO.save();
        }}).start();
    }
    /**
     * Adds an EditTimeline window to the children of this instance.
     * 
     * @param e The EditTimeline child to be added.
     */
    public void addEditTimeline(EditTimeline e){
        openEditTimelines.add(e);
    }
    /**
     * Removes an EditTimeline window from the children of this instance.
     * 
     * @param e The EditTimeline to be removed.
     */
    public void removeEditTimeline(EditTimeline e){
        openEditTimelines.remove(e);
    }
    /**
     * A listener to edit a timeline.
     */
    private class MTListener implements ActionListener{
        /**
         * Constructor
         */
        public MTListener(){}
        /**
         * Implements the actionPerformed metho to edit a specific timeline.
         * 
         * @param ae The actionEvent detected.
         */
        public void actionPerformed(ActionEvent ae){
            JButton thisButton = (JButton) ae.getSource();
            if(thisButton == manageButton){
                if(fileIO.timeSize()==0) return;
                if(selectedTimeline == null) return;
                for(EditTimeline e : openEditTimelines)
                    if(e.getTimeline().getTitle().equals(selectedTimeline.getTitle()))
                        return;

                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new EditTimeline(selectedTimeline, fileIO, thisManageTimelines).setVisible(true);
                    }
                });
           }else if(thisButton == deleteButton){
               if(selectedTimeline != null){
                    int result = JOptionPane.showConfirmDialog(
                     thisManageTimelines,
                     "Are you sure you want to delete this timeline?",
                     "Delete",
                      JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION)
                        deleteTimeline();
               }
           }else if(thisButton == createButton){
               String name = nameTextField.getText();
               for(EditTimeline e : openEditTimelines)
                    if(e.getTimeline().getTitle().equals(name))
                        return;
               if(name.equals("<Name>")) return;
               if(fileIO.containsTimeline(name)) return;
               Timeline time = new Timeline(name);
               fileIO.addTimeline(time);
               setComboBox(); // Reset combo box to display the new timeline.
               jComboBox1.setSelectedItem(time.getTitle());
               selectedTimeline = time;
               java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new EditTimeline(selectedTimeline, fileIO, thisManageTimelines).setVisible(true);
                }
            });
            fileIO.save();
           }
        }
    }
    /**
     * Listener for selections in the comboBox.
     */
    private class ComboBoxListener implements ActionListener{
        /**
         * Constructor
         */
        public ComboBoxListener(){}
        /**
         * implements the actionPerformed method for selecting a timeline.
         * 
         * @param ae The actionEvent.
         */
        public void actionPerformed(ActionEvent ae){
            JComboBox thisBox = (JComboBox) ae.getSource();
            Iterator<Timeline> timelineIterator =  fileIO.getTimelineIterator();
            Timeline t;
            while(timelineIterator.hasNext()){
                t = timelineIterator.next();
                if(thisBox.getSelectedItem().equals(t.getTitle())){
                    selectedTimeline = t;
                    break;
                }      
            }
        }
    }
}

    


