/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

import javax.swing.ImageIcon;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel
 */
public class EditEventTest {
    static EditEvent instance;
    public EditEventTest() {
        instance = new EditEvent(new Event("e"), new FileIO(), 
                new Timeline("t"), new EditTimeline(
                new Timeline("t"), new FileIO(), new ManageTimelines(new FileIO())
                ));
    }

    /**
     * Test of setComboBox method, of class EditEvent.
     */
    @Test
    public void testInstantiation() {
        assertNotNull(instance);
    }

    /**
     * Test of getEvent method, of class EditEvent.
     */
    @Test
    public void testGetEvent() {
        Event e = instance.getEvent();
        assertNotNull(e);
        assertEquals(e.getTitle(),"e");
    }
}