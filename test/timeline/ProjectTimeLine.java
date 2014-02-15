package timeline;

import javax.swing.*;

/**
 *
 * @author Daniel
 */
public class ProjectTimeLine {

    /**
     * The main method to start the ProjectTimeLine by generating an
     * OpenScreen window.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileIO fileIO = new FileIO();
        OpenScreen openScreen = new OpenScreen(fileIO);
        openScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        openScreen.setLocation(200,200);
        openScreen.setVisible(true);
    }
}