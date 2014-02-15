/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

import java.util.*;

/**
 * Class by which to test the FileIO class.
 * 
 * @author Daniel
 */
public class FileIOTest {
    /**
     * Main method by which to test the FileIO.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        FileIO io = new FileIO();
        Iterator<Category> itC = io.getCategoryIterator();
        Iterator<Timeline> itT = io.getTimelineIterator();
        Timeline timeline = new Timeline("first");
        Category cat = new Category("cat");
        timeline.addEvent(new Event("event",24,cat));
        io.addCategory(cat);
        io.addTimeline(timeline);
        io.save();
    }
}
