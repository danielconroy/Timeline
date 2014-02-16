/*
 * To change this template, choose Tools | Templates
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
 * @author Daniel
 */
public class DisplayTest {
    static Display display1;
    static Display display2;
    static Display display3;
    static Surface surface1;
    static Surface surface2;
    static Surface surface3;
    static Timeline timeline1 = new Timeline("test1");
    static Timeline timeline2 = new Timeline("test2");
    static Timeline timeline3 = new Timeline("test3");
    static Category person = new Category("Person",Color.CYAN);
    public DisplayTest() {
        Event e1 = new Event("first",4,person);
        timeline1.addEvent(e1);
        Event e2 = new Event("first",100,person);
        e2.setEndDate(250);
        timeline2.addEvent(e2);
        timeline3.addEvent(e1);
        timeline3.addEvent(e2);
    }

    @Test
    public void testCreateDisplay() {
        display1 = new Display(timeline1);
        display2 = new Display(timeline2);
        display3 = new Display(timeline3);
        assertNotNull(display1);
        assertNotNull(display2);
        assertNotNull(display3);
    }
    
    @Test
    public void testCreateSurface() {
        surface1 = new Surface(timeline1);
        surface2 = new Surface(timeline2);
        surface3 = new Surface(timeline3);
        assertNotNull(surface1);
        assertNotNull(surface2);
        assertNotNull(surface3);
    }
}