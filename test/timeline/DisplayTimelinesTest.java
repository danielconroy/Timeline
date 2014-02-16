/*
 * To change this template, choose Tools | Templates
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
 * @author Daniel
 */
public class DisplayTimelinesTest {
    static DisplayTimelines dtl;
    public DisplayTimelinesTest() {
        dtl = new DisplayTimelines(new FileIO());
    }

    @Test
    public void testStart() {
        assertNotNull(dtl);
    }
}