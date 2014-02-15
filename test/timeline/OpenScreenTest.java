/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel
 */
public class OpenScreenTest {
    
    public OpenScreenTest() {
    }
    
    @Test
    public void test(){
        OpenScreen os = new OpenScreen(new FileIO());
        assertTrue(os!=null);
    }
}