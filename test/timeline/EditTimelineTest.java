/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

import java.util.ArrayList;
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
public class EditTimelineTest {
    EditTimeline etl;
    public EditTimelineTest() {
        etl = new EditTimeline(new Timeline("t"),new FileIO(),
                new ManageTimelines(new FileIO()));
    }

    /**
     * Test of setSelectedItem method, of class EditTimeline.
     */
    @Test
    public void testInstantiation() {
        assertNotNull(etl);
    }

    /**
     * Test of addEditEvent method, of class EditTimeline.
     */
    @Test
    public void testAddRemoveEditEvent() {
        EditEvent ee = new EditEvent(new Event("e"),new FileIO(),
                new Timeline("t"),etl);
        etl.addEditEvent(ee);
        etl.removeEditEvent(ee);
        assertNotNull(etl);
    }

    /**
     * Test of getTimeline method, of class EditTimeline.
     */
    @Test
    public void testGetTimeline() {
        Timeline t = etl.getTimeline();
        assertEquals(t.getTitle(),"t");
    }

    /**
     * Test of getEditEvents method, of class EditTimeline.
     */
    @Test
    public void testGetEditEvents() {
        ArrayList<EditEvent> array = etl.getEditEvents();
        EditEvent ee = new EditEvent(new Event("e"),new FileIO(),
                new Timeline("t"),etl);
        array = etl.getEditEvents();
        assertEquals(array.size(),1);
    }
}