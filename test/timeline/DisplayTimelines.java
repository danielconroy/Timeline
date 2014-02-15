package timeline;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * A JFrame by which to select which timeline to display.
 * 
 * @author Daniel
 * @author Kayley
 */
public class DisplayTimelines extends javax.swing.JFrame {
    private final FileIO fileIO;
    private Timeline selectedTimeline;
    /**
     * Constructor
     * 
     * @param fileIO The FileIO by which to save.
     */
    public DisplayTimelines(FileIO fileIO) {
        this.fileIO = fileIO;       
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private javax.swing.JButton displayButton;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel titleLabel;
    
    private void initComponents() {

        setResizable(false);
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int) ((d.getHeight() - getHeight()) / 2);        
        int x = (int) ((d.getWidth() - getWidth()) / 2);
        setLocation(x, y);

        titleLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        displayButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setText("Display Timelines");

        try{
            titleLabel.setFont(new Font("Vijaya", 0, 32));
        }catch(Exception e){
            titleLabel.setFont(new Font("Times new Roman", 0, 24));
        }

        setComboBox();
        
        displayButton.setText("Display");
        displayButton.addActionListener(new DTListener());
        jComboBox1.addActionListener(new ComboBoxListener());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                .addComponent(displayButton))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(titleLabel))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(displayButton)))
        );

        pack();
    }// </editor-fold>                        
    /**
     * Sets the ComboBox.
     * Updates the combo box with all timelines stored in fileIO.
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
     * A class to listen for a selection to display.
     */
    private class DTListener implements ActionListener{
        /**
         * Constructor
         */
        public DTListener(){}
        /**
         * Implements the actionPerformed to display the timeline.
         * 
         * @param ae The actionEvent received.
         */
        public void actionPerformed(ActionEvent ae){
            if(selectedTimeline == null) return;
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    Display display = new Display(selectedTimeline);
                    display.setVisible(true);
                }
            });
        }
    }
    /**
     * A class to listen for a selection of a timeline.
     */
    private class ComboBoxListener implements ActionListener{
        /**
         * Constructor
         */
        public ComboBoxListener(){}
        /**
         * Implements the actionPerformed method to select a timeline.
         * 
         * @param ae The ActionEvent received.
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