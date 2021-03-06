
package timeline;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * JFrame to display at program opening.
 * 
 * @author Daniel
 * @author Kayley
 */
public class OpenScreen extends JFrame {
    private final FileIO fileIO;
    /**
     * Constructor
     * 
     * @param fileIO The fileIO used for saving.
     */
    public OpenScreen(FileIO fileIO) {
        this.fileIO = fileIO;
        fileIO.addCategory(new Category("Default"));
        initComponents();
    }

    private JButton manageTButton;
    private JButton displayButton;
    private JButton manageCButton;
    private JButton quitButton;
    private JLabel titleLabel;
    
    /**
     * Initializes the components.
     */
    private void initComponents() {
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        titleLabel = new JLabel();
        manageTButton = new JButton();
        displayButton = new JButton();
        manageCButton = new JButton();
        quitButton = new JButton();
        
        manageTButton.addActionListener(new MenuListener());
        displayButton.addActionListener(new MenuListener());
        manageCButton.addActionListener(new MenuListener());
        quitButton.addActionListener(new MenuListener());
        
        try{
            titleLabel.setFont(new Font("Vijaya", 0, 42));
        }catch(Exception e){
            titleLabel.setFont(new Font("Times new Roman", 0, 34));
        }
        titleLabel.setText(" TimeLine");

        manageTButton.setText("Manage Timelines");
        displayButton.setText("Display TimeLines");
        manageCButton.setText("Manage Categories");
        quitButton.setText("Save & Quit");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(manageTButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(displayButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageCButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(quitButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(titleLabel))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addComponent(manageTButton)
                .addComponent(displayButton)
                .addComponent(manageCButton)
                .addComponent(quitButton))
        );
                
        pack();
    }
    /**
     * A listener to listen to a button selection.
     */
    private class MenuListener implements ActionListener{
        /**
         * Constructor
         */
        public MenuListener(){}
        /**
         * Implements the actionPerformed method to open child windows.
         * 
         * @param ae The ActionEvent.
         */
        public void actionPerformed(ActionEvent ae){
            JButton thisButton = (JButton) ae.getSource();
            if(thisButton == manageTButton){
                        /* Create and display the form */
                EventQueue.invokeLater(new Runnable() {
                public void run() {
                    ManageTimelines mt = new ManageTimelines(fileIO);
                    mt.setVisible(true);
                }
                });
            }else if(thisButton == displayButton){
                         /* Create and display the form */
                 EventQueue.invokeLater(new Runnable() {
                 public void run() {
                     DisplayTimelines dt = new DisplayTimelines(fileIO);
                     dt.setVisible(true);
                 }
                });

            }else if(thisButton == manageCButton){
                 EventQueue.invokeLater(new Runnable() {
                 public void run() {
                     ManageCategories mc;
                     mc = new ManageCategories(fileIO);
                     mc.setVisible(true);
                 }
                });    
            }else if(thisButton == quitButton){
                fileIO.save();
                System.exit(0);
            }
        }
    }
}