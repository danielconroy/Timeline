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
public class EditCategoryTest {
    
    EditCategory instance;
    FileIO fileIO;
    Category c;
    
    public EditCategoryTest() {
        fileIO = new FileIO();
        c = new Category("Papple");
        instance = new EditCategory(c, fileIO, 
            new ManageCategories(fileIO));
    }


    /**
     * Test of getCategory method, of class EditCategory.
     */
    @Test
    public void testGetCategory() {
        Category result = instance.getCategory();
        assertEquals(c, result);
    }
    
    /*
    * These next two tests don't succeed because these 
    * methods have private access in EditCategory, currently.
    */
      /**
     * Test of submitTextFields method, of class EditCategory.
     */
    @Test
    public void testSubmitTextFields() {
        assertFalse(instance.submitTextFields());
    }
    
     /**
     * Test of makeColor method, of class EditCategory.
     */
    @Test
    public void testMakeColor() {
        assertFalse(instance.makeColor());
    }
  
}
