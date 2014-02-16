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
public class ManageTimelinesTest {
    
    /* 
    * Class doesn't currently pass any of these tests because
    * It accesses fields/methods that are now private/do not exist, but this was the original intent.
    */
    
    ManageTimelines instance;
    FileIO fileIO;
    
    public ManageTimelinesTest() {
        fileIO = new FileIO();
        instance = new ManageTimelines(fileIO);
    }
    
    /*
    * Test of the deleteTimelines method.
    */
    @Test
    public void testdeleteTimeline(){
        Timeline t = new Timeline("Blah");
        fileIO.addTimeline(t);
        instance.setSelectedTimeline(t);
        instance.deleteTimeline();
        assertFalse(fileIO.containsTimeline("Blah"));
    }

    
}
