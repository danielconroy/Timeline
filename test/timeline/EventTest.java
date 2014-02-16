/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package timeline;

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
public class EventTest {
    Event instance;
    
    public EventTest() {
        instance = new Event("");
    }

    /**
     * Test of getDescription method, of class Event.
     */
    @Test
    public void testGetDescription() {
        String expResult = null;
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTitle method, of class Event.
     */
    @Test
    public void testGetTitle() {
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartDate method, of class Event.
     */
    @Test
    public void testGetStartDate() {
        Integer expResult = null;
        Integer result = instance.getStartDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndDate method, of class Event.
     */
    @Test
    public void testGetEndDate() {
        Integer expResult = null;
        Integer result = instance.getEndDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Event.
     */
    @Test
    public void testSetDescription() {
        String description = "orange orange orange orange";
        instance.setDescription(description);
        assertEquals(instance.getDescription(), description);
    }

    /**
     * Test of setTitle method, of class Event.
     */
    @Test
    public void testSetTitle() {
        String title = "apple";
        instance.setTitle(title);
        assertEquals(instance.getTitle(), title);
    }

    /**
     * Test of setStartDate method, of class Event.
     */
    @Test
    public void testSetStartDate() {
        Integer startDate = 5;
        instance.setStartDate(startDate);
        assertEquals(instance.getStartDate(), startDate);
    }

    /**
     * Test of setEndDate method, of class Event.
     */
    @Test
    public void testSetEndDate() {
        Integer endDate = 5;
        instance.setEndDate(endDate);
        assertEquals(instance.getEndDate(), endDate);
    }

    /**
     * Test of setCategory method, of class Event.
     */
    @Test
    public void testSetCategory() {
        Category category = new Category("Apple");
        instance.setCategory(category);
        assertEquals(category, instance.getCategory());
    }
    
}
