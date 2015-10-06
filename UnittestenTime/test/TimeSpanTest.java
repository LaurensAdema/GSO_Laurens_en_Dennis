/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fontys.time.Time;
import fontys.time.TimeSpan;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laurens
 */
public class TimeSpanTest {

    
    public TimeSpanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void TestTimeSpanConstructor ()
    {
        Time time1 = new Time(1995, 7, 11, 0,0);
        Time time2 = new Time(1996, 7, 11, 0,0);
        TimeSpan timeSpan1 = new TimeSpan(time1, time2);
        
        // TEST 1 (begin tijd)
        assertEquals("De constructor is niet goed", timeSpan1.getBeginTime(), time1);
        
        // TEST 2 (eind tijd)
        assertEquals("De constructor is niet goed", timeSpan1.getEndTime(), time2);
        
        TimeSpan timeSpan2 = null;
        try {
            timeSpan2 = new TimeSpan(time2, time1);
        } catch (Exception e) {
            //error
        }
        assertNull("De begintijd is na de eindtijd", timeSpan2);
    }
    
    @Test
    public void TestTimeSpanLength(){
        Time time1 = new Time(1995, 7, 11, 0,0);
        Time time2 = new Time(1995, 7, 11, 0,10);
        TimeSpan timeSpan1 = new TimeSpan(time1, time2);
        
        assertEquals("De lengte klopt niet", timeSpan1.length(), 10);
    }
    
    @Test
    public void TestTimeSpanSetBeginTime()
    {
        // TEST 1 (begin tijd fout)
        Time time1 = new Time(1995, 7, 11, 0,0);
        Time time2 = new Time(1996, 7, 11, 0,0);
        TimeSpan timeSpan1 = new TimeSpan(time1, time2);
        
        Time time3 = new Time(1997, 7, 11, 0,0);
        try {
            timeSpan1.setBeginTime(time3);
        } catch (Exception e) {
        }
        
        assertFalse("De begintijd is toch veranderd", timeSpan1.getBeginTime().equals(time3));
        
        // TEST 2 (begin tijd goed)
        Time time4 = new Time(1995, 7, 11, 0,0);
        Time time5 = new Time(1997, 7, 11, 0,0);
        TimeSpan timeSpan2 = new TimeSpan(time4, time5);
        
        Time time6 = new Time(1996, 7, 11, 0,0);
        
        timeSpan2.setBeginTime(time6);
        
        assertEquals("De begintijd is niet veranderd", timeSpan2.getBeginTime(), time6);
    }
    
    @Test
    public void TestTimeSpanSetEndTime()
    {
        // TEST 1 (eind tijd)
        Time time1 = new Time(1995, 7, 11, 0,0);
        Time time2 = new Time(1996, 7, 11, 0,0);
        TimeSpan timeSpan1 = new TimeSpan(time1, time2);
        
        Time time3 = new Time(1994, 7, 11, 0,0);
        try {
            timeSpan1.setEndTime(time3);
        } catch (Exception e) {
            // error
        }
        
        assertFalse("De eindtijd is toch veranderd", timeSpan1.getEndTime().equals(time3));
        
        // TEST 2 (begin tijd goed)
        Time time4 = new Time(1995, 7, 11, 0,0);
        Time time5 = new Time(1997, 7, 11, 0,0);
        TimeSpan timeSpan2 = new TimeSpan(time4, time5);
        
        Time time6 = new Time(1998, 7, 11, 0,0);
        
        timeSpan2.setEndTime(time6);
        
        assertEquals("De eindtijd is niet veranderd", timeSpan2.getEndTime(), time6);
    }        
    
    @Test
    public void TestTimeSpanMove()
    {
        // TEST 1 (verander met 10)
        Time time1 = new Time(1995, 7, 11, 0,0);
        Time time2 = new Time(1996, 7, 11, 0,10);
        TimeSpan timeSpan1 = new TimeSpan(time1, time2);
        
        Time time3 = new Time(1995, 7, 11, 0,10);
        Time time4 = new Time(1996, 7, 11, 0,20);
        TimeSpan timeSpan2 = new TimeSpan(time3, time4);
        
        timeSpan1.move(10);
        
        assertEquals("Tijd niet opgeschoven", timeSpan1, timeSpan2);
    }
    
    @Test
    public void TestTimeSpanChangeLengthWith()
    {
        // TEST 1 (verlengen)
        Time time1 = new Time(1995, 7, 11, 0,0);
        Time time2 = new Time(1996, 7, 11, 0,10);
        TimeSpan timeSpan1 = new TimeSpan(time1, time2);
        
        Time time3 = new Time(1996, 7, 11, 0,20);
        TimeSpan timeSpan2 = new TimeSpan(time1, time3);
        
        timeSpan1.changeLengthWith(10);
        
        assertEquals("Tijd niet verlengd", timeSpan1, timeSpan2);
        
        // TEST 2 (negatief)
        try {
            timeSpan1.changeLengthWith(-10);
        } catch (Exception e) {
            // error
        }
        Time time4 = new Time(1996, 7, 11, 0,0);
        
        assertFalse("De tijd is toch negatief verlengd", timeSpan1.getEndTime().equals(time4));
    }  
    
    @Test
    public void TestTimeSpanIsPartOf()
    {
        // TEST 1 (valt binnen)
        Time time1 = new Time(1995, 7, 11, 0,0);
        Time time2 = new Time(1998, 7, 11, 0,0);
        TimeSpan timeSpan1 = new TimeSpan(time1, time2);
        
        Time time3 = new Time(1996, 7, 11, 0,0);
        Time time4 = new Time(1997, 7, 11, 0,0);
        TimeSpan timeSpan2 = new TimeSpan(time3, time4);
        
        assertTrue("Span 2 overlapt wel met span 1", timeSpan2.isPartOf(timeSpan1));
        
        // TEST 2 (valt niet binnen)
        Time time5 = new Time(1995, 7, 11, 0,0);
        Time time6 = new Time(1996, 7, 11, 0,0);
        TimeSpan timeSpan3 = new TimeSpan(time5, time6);
        
        Time time7 = new Time(1997, 7, 11, 0,0);
        Time time8 = new Time(1998, 7, 11, 0,0);
        TimeSpan timeSpan4 = new TimeSpan(time7, time8);
        
        assertFalse("Span 3 overlapt niet met span 4", timeSpan4.isPartOf(timeSpan3));
        // TEST 4 (valt niet binnen aan de andere kant)
        assertFalse("Span 3 overlapt niet met span 4", timeSpan3.isPartOf(timeSpan4));
        
        // TEST 3 (valt half binnen)
        Time time9 = new Time(1995, 7, 11, 0,0);
        Time time10 = new Time(1997, 7, 11, 0,0);
        TimeSpan timeSpan5 = new TimeSpan(time9, time10);
        
        Time time11 = new Time(1996, 7, 11, 0,0);
        Time time12 = new Time(1998, 7, 11, 0,0);
        TimeSpan timeSpan6 = new TimeSpan(time11, time12);
        
        assertFalse("Span 3 overlapt niet met span 4", timeSpan6.isPartOf(timeSpan5));
    }
    
    @Test
    public void TestTimeSpanUnionWith()
    {
        // TEST 1 (valt binnen)
        Time time1 = new Time(1995, 7, 11, 0,0);
        Time time2 = new Time(1998, 7, 11, 0,0);
        TimeSpan timeSpan1 = new TimeSpan(time1, time2);
        
        Time time3 = new Time(1996, 7, 11, 0,0);
        Time time4 = new Time(1997, 7, 11, 0,0);
        TimeSpan timeSpan2 = new TimeSpan(time3, time4);
        
        assertEquals("De union van 'valt binnen' is niet voltooid", timeSpan2.unionWith(timeSpan1), timeSpan1);
        
        // TEST 2 (overlapt eind)
        Time time5 = new Time(1995, 7, 11, 0,0);
        Time time6 = new Time(1997, 7, 11, 0,0);
        TimeSpan timeSpan3 = new TimeSpan(time5, time6);
        
        Time time7 = new Time(1996, 7, 11, 0,0);
        Time time8 = new Time(1998, 7, 11, 0,0);
        TimeSpan timeSpan4 = new TimeSpan(time7, time8);
        
        Time time9 = new Time(1995, 7, 11, 0,0);
        Time time10 = new Time(1998, 7, 11, 0,0);
        TimeSpan timeSpan5 = new TimeSpan(time9, time10);
        
        assertEquals("De union van 'overlapt eind' is niet voltooid", timeSpan4.unionWith(timeSpan3), timeSpan5);
        // TEST 3 (overlapt begin
        assertEquals("De union van 'overlapt begin' is niet voltooid", timeSpan3.unionWith(timeSpan4), timeSpan5);
        
        // TEST 4 (ver weg)
        Time time11 = new Time(1995, 7, 11, 0,0);
        Time time12 = new Time(1996, 7, 11, 0,0);
        TimeSpan timeSpan6 = new TimeSpan(time11, time12);
        
        Time time13 = new Time(1997, 7, 11, 0,0);
        Time time14 = new Time(1998, 7, 11, 0,0);
        TimeSpan timeSpan7 = new TimeSpan(time13, time14);
        
        assertNull("De union van 'ver weg' is wel voltooid", timeSpan7.unionWith(timeSpan6));
        // TEST 5 (ver weg andere kant)
        assertNull("De union van 'ver weg andere kant' is wel voltooid", timeSpan6.unionWith(timeSpan7));
    }
    
    @Test
    public void TestTimeSpanIntersectionWith ()
    {
        // TEST 1 (in elkaar)
        Time time1 = new Time(1995, 7, 11, 0,0);
        Time time2 = new Time(1997, 7, 11, 0,0);
        TimeSpan timeSpan1 = new TimeSpan(time1, time2);
        
        Time time3 = new Time(1996, 7, 11, 0,0);
        Time time4 = new Time(1998, 7, 11, 0,0);
        TimeSpan timeSpan2 = new TimeSpan(time3, time4);
        
        Time time5 = new Time(1996, 7, 11, 0,0);
        Time time6 = new Time(1997, 7, 11, 0,0);
        TimeSpan timeSpan3 = new TimeSpan(time5, time6);
        
        assertEquals("De teruggegeven intersectie klopt niet", timeSpan2.intersectionWith(timeSpan1), timeSpan3);
        
        // TEST 2 (uit elkaar)
        Time time7 = new Time(1995, 7, 11, 0,0);
        Time time8 = new Time(1996, 7, 11, 0,0);
        TimeSpan timeSpan4 = new TimeSpan(time7, time8);
        
        Time time9 = new Time(1997, 7, 11, 0,0);
        Time time10 = new Time(1998, 7, 11, 0,0);
        TimeSpan timeSpan5 = new TimeSpan(time9, time10);
        
        assertNull("De union van 'ver weg' is wel voltooid", timeSpan4.intersectionWith(timeSpan5));
    }
    
    @Test
    public void TestTimeSpanEquals ()
    {
        Time time1 = new Time(1995, 7, 11, 0,0);
        Time time2 = new Time(1997, 7, 11, 0,0);
        TimeSpan timeSpan1 = new TimeSpan(time1, time2);
        
        assertFalse("String wordt vergeleken met tijd", timeSpan1.equals("test"));
    }
}
