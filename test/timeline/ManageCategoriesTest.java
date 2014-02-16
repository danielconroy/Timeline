/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package timeline;

import java.util.Iterator;
import javax.swing.ImageIcon;
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
public class ManageCategoriesTest {
    
    /* 
    * Class doesn't currently pass any of these tests because
    * It accesses fields/methods that are now private, but this was the original intent.
    */
    ManageCategories instance;
    FileIO fileIO;
    
    public ManageCategoriesTest() {
        fileIO = new FileIO();
        instance = new ManageCategories(fileIO);
    }


    /**
     * Test of addEditCategory method, of class ManageCategories.
     */
    @Test
    public void testAddEditCategory() {
        EditCategory e = new EditCategory(new Category("Papple"), 
                new FileIO(), instance);
        instance.addEditCategory(e);
        assertTrue(instance.openEditCategories.contains(e));
    }

    /**
     * Test of removeEditCategory method, of class ManageCategories.
     */
    @Test
    public void testRemoveEditCategory() {
        EditCategory e = new EditCategory(new Category("Papple"), 
                new FileIO(), instance);
        instance.addEditCategory(e);
        instance.removeEditCategory(e);
        assertFalse(instance.openEditCategories.contains(e));
    }
    
   @Test
   public void testresetEventCategories(){
       Category toDelete = new Category("Papple");
       fileIO.addCategory(toDelete);
       Event e = new Event("");
       e.setCategory(toDelete);
       Timeline t = new Timeline("");
       t.addEvent(e);
       fileIO.addTimeline(t);
       instance.resetEventCategories(toDelete);
       assertFalse(e.getCategory() == toDelete);
   }
    
}
