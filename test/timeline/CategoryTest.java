/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package timeline;

import java.awt.Color;
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
public class CategoryTest {
    static Category testCategory;
    public CategoryTest() {
        testCategory = new Category("");
    }

    /**
     * Test of getName method, of class Category.
     */
    @Test
    public void testGetName() {
        //System.out.println("getName");
        String expResult = "";
        String result = testCategory.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Category.
     */
    @Test
    public void testSetName() {
        //System.out.println("setName");
        String name = "";
        testCategory.setName(name);
        assertEquals(name, testCategory.getName());
    }

    /**
     * Test of setColor method, of class Category.
     */
    @Test
    public void testSetColor() {
        //System.out.println("setColor");
        Color catColor;
        catColor = Color.BLUE;
        testCategory.setColor(catColor);
        assertEquals(catColor, testCategory.getColor());
    }
    
}
