/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

import java.util.ArrayList;
import java.util.Iterator;
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
public class FileIOTest {
    FileIO io;
    public FileIOTest() {
        io = new FileIO();
    }

    /**
     * Test of save method, of class FileIO.
     */
    @Test
    public void testInstantiation() {
        assertNotNull(io);
    }

    /**
     * Test of addTimeline method, of class FileIO.
     */
    @Test
    public void testAddTimelineContainsTimeline() {
        io.addTimeline(new Timeline("t"));
        assertTrue(io.containsTimeline("t"));
    }

    /**
     * Test of addCategory method, of class FileIO.
     */
    @Test
    public void testAddCategoryContainsCategory() {
        io.categories.removeAll(io.categories);
        io.addCategory(new Category("c"));
        assertEquals(io.categories.get(0).getName(),"c");
    }

    /**
     * Test of deleteTimeline method, of class FileIO.
     */
    @Test
    public void testDeleteTimeline_String() {
        Timeline t = new Timeline("t");
        io.addTimeline(t);
        io.deleteTimeline("t");
        assertFalse(io.containsTimeline("t"));
        io.addTimeline(t);
        io.deleteTimeline(t);
        assertFalse(io.containsTimeline("t"));
    }

    /**
     * Test of deleteCategory method, of class FileIO.
     */
    @Test
    public void testDeleteCategory_String() {
        Category c = new Category("c");
        io.addCategory(c);
        io.deleteCategory("c");
        assertFalse(io.categories.contains(c));
        io.addCategory(c);
        io.deleteCategory(c);
        assertFalse(io.categories.contains(c));
    }

    /**
     * Test of getNames method, of class FileIO.
     */
    @Test
    public void testGetNames() {
        io.timelines.removeAll(io.timelines);
        Timeline t = new Timeline("t");
        io.addTimeline(t);
        ArrayList<String> array = io.getNames();
        assertEquals(array.size(),1);
        assertTrue(array.contains("t"));
    }

    /**
     * Test of getCategoryIterator method, of class FileIO.
     */
    @Test
    public void testGetCategoryIterator() {
        io.categories.removeAll(io.categories);
        Category c = new Category("c");
        io.addCategory(c);
        Iterator<Category> cat = io.getCategoryIterator();
        assertTrue(cat.hasNext());
        assertEquals(cat.next(),c);
    }

    /**
     * Test of getTimelineIterator method, of class FileIO.
     */
    @Test
    public void testGetTimelineIterator() {
        io.timelines.removeAll(io.timelines);
        Timeline t = new Timeline("t");
        io.addTimeline(t);
        Iterator<Timeline> time = io.getTimelineIterator();
        assertTrue(time.hasNext());
        assertEquals(time.next(),t);
    }
}