
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

public class EditEvent extends javax.swing.JFrame {

    /**
     * Creates new form EditEvent
     */
    private Event event;
    private Category selectedCategory;
    private Timeline timeline;
    private EditTimeline superTimeline;
    
    public EditEvent(Event event, FileIO fileIO, Timeline timeline, EditTimeline superTimeline) {
        this.event = event;
        this.fileIO = fileIO;
        this.timeline = timeline;
        this.superTimeline =superTimeline;
        initComponents();
    }

    private final FileIO fileIO;
    private JButton finishedButton;
    private JComboBox jComboBox1;
    private JLabel jLabel1;
    private JTextField nameTextField;
    private JTextField descriptionTextField;
    private JTextField startTextField;
    private JTextField endTextField;

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
        
        jLabel1 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        descriptionTextField = new javax.swing.JTextField();
        startTextField = new javax.swing.JTextField();
        endTextField = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        finishedButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Edit Event");

        fillTextFields();
        setComboBox();
        
        jComboBox1.addActionListener(new ComboBoxListener());

        finishedButton.setText("Finished");
        finishedButton.addActionListener(new EEListener());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(nameTextField)
                    .addComponent(descriptionTextField)
                    .addComponent(startTextField)
                    .addComponent(endTextField)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(finishedButton, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(finishedButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void fillTextFields(){
        if(event.getTitle()!=null)
            nameTextField.setText(event.getTitle());
        else
            nameTextField.setText("<Name>");
        if(event.getDescription()!=null)
            descriptionTextField.setText(event.getDescription());
        else
            descriptionTextField.setText("<Des>");
        if(event.getStartDate()!=null)
            startTextField.setText(""+event.getStartDate());
        else
            startTextField.setText("<Start>");
        if(event.getEndDate()!=null)
            endTextField.setText(""+event.getEndDate());  
        else
            endTextField.setText("<End>");
    }
    
    private void setComboBox(){
        Iterator<Category> categoryIterator =  fileIO.getCategoryIterator();
        String[] names = new String[fileIO.catSize()];
        int i = 0;
        int catIndex = 0;
        Category c = new Category("Base");
        while(categoryIterator.hasNext()){
            c = categoryIterator.next();

            names[i++] = c.getName();
        }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(names));
        selectedCategory = c;
        jComboBox1.setSelectedItem(event.getCategory().getName());
    }
    
    // Returns true if valid event, false if not.
    public boolean submitTextFields(){
        String name = nameTextField.getText();
        String description = descriptionTextField.getText();
        String startDate = startTextField.getText();
        String endDate = endTextField.getText();
        Integer start, end;
        //Name and startDate are essential descriptors. The others are optional.
        if(name.equals("<Name>") || startDate.equals("<Start>")){
            //Note: inform user they're wrong first.
            return false;
        }
        try{
            start = Integer.parseInt(startDate);
        }catch(NumberFormatException e){
            System.out.println("Invalid start date!");
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
                System.out.println("Invalid end date!");
                return false;
            } 
            //The end date can't happen before the starting date!
            if(end >= start) event.setEndDate(end);
        }
        return true;
    }

    private class EEListener implements ActionListener{
    
        public EEListener(){
        }
    
        public void actionPerformed(ActionEvent ae){
            JButton thisButton = (JButton) ae.getSource();
            if(thisButton == finishedButton){
                if(submitTextFields()){
                    timeline.addEvent(event);
                    superTimeline.setComboBox();                 
                }
                setVisible(false);
                dispose();
            }
        }
    
    }
    
    private class ComboBoxListener implements ActionListener{
    
    public ComboBoxListener(){
        
    }
    
    public void actionPerformed(ActionEvent ae){
        JComboBox thisBox = (JComboBox) ae.getSource();
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