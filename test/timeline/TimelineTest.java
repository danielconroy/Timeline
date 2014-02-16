/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package timeline;

import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kayley Lane
 */
public class TimelineTest {
    
    Timeline instance;
    
    public TimelineTest() {
        instance = new Timeline("");
    }

    /**
     * Test of getTitle method, of class Timeline.
     */
    @Test
    public void testGetTitle() {
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class Timeline.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "";
        instance.setTitle(title);
        assertTrue(title.equals(instance.getTitle()));
    }

    /**
     * Test of addEvent method, of class Timeline.
     */
    @Test
    public void testAddEvent() {
        Event e = new Event("Title");
        instance.addEvent(e);
        assertTrue(instance.containsTitle(e.getTitle()));
    }

    /**
     * Test of deleteEvent method, of class Timeline.
     */
    @Test
    public void testDeleteEvent() {
        System.out.println("deleteEvent");
        Event e = new Event("Title");
        instance.deleteEvent(e);
        assertFalse(instance.containsTitle(e.getTitle()));
    }

    /**
     * Test of containsTitle method, of class Timeline.
     */
    @Test
    public void testContainsTitle() {
        System.out.println("containsTitle");
        String title = "";
        boolean expResult = false;
        boolean result = instance.containsTitle(title);
        assertEquals(expResult, result);
    }

    /**
     * Test of eventSize method, of class Timeline.
     */
    @Test
    public void testEventSize() {
        System.out.println("eventSize");
        int expResult = 0;
        int result = instance.eventSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of findEvent method, of class Timeline.
     */
    @Test
    public void testFindEvent() throws Exception {
        System.out.println("findEvent");
        String title = "";
        Event expResult = new Event(title);
        instance.addEvent(expResult);
        Event result = instance.findEvent(title);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEventIterator method, of class Timeline.
     */
    @Test
    public void testGetEventIterator() {
        instance.addEvent(new Event("01"));
        instance.addEvent(new Event("02"));
        Iterator<Event> result = instance.getEventIterator();
        assertFalse(result == null);
    }
    
}
