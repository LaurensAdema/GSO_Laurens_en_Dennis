/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fontys.time.DayInWeek;
import fontys.time.Time;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class TimeTest {

    public TimeTest() {
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
    public void TestTimeConstructor() {
        Time time1 = new Time(1996, 7, 11, 0, 0);
        Time time1e = null;
        String time1f = "TIIIIMMEEEEE";
        Time time2 = new Time(1996, 7, 11, 0, 0);
        assertEquals(time1 + " is not equal to " + time2, time1, time2);
        assertFalse(time1.equals(time1e));
        assertFalse(time1.equals(time1f));

        Time time3 = new Time(1995, 7, 11, 0, 0);
        assertFalse(time1 + " is equal to " + time3, time1.equals(time3));
        Time time1a = null;
        Time time1b = null;
        Time time1c = null;
        Time time1d = null;
        try {
            time1a = new Time(1996, 42, 11, 0, 0);
        } catch (Exception e) {
            //this should error
        }
        try {
            time1b = new Time(1996, 7, 42, 0, 0);
        } catch (Exception e) {
            //this should error
        }
        try {
            time1c = new Time(1996, 7, 11, 42, 0);
        } catch (Exception e) {
            //this should error
        }
        try {
            time1d = new Time(1996, 7, 11, 0, 420);
        } catch (Exception e) {
            //this should error
        }

        assertNull(time1a);
        assertNull(time1b);
        assertNull(time1c);
        assertNull(time1d);

    }

    @Test
    public void TestDayInWeek() {
        Time time4 = new Time(2015, 10, 5, 13, 1);
        assertEquals(DayInWeek.MON + " is not equal to " + time4.getDayInWeek().ordinal() + ", " + time4.getDay() + "-" + time4.getMonth() + "-" + time4.getYear(), DayInWeek.MON, time4.getDayInWeek());
        Time time4a = new Time(2015, 10, 6, 13, 1);
        Time time4b = new Time(2015, 10, 7, 13, 1);
        Time time4c = new Time(2015, 10, 8, 13, 1);
        Time time4d = new Time(2015, 10, 9, 13, 1);
        Time time4e = new Time(2015, 10, 10, 13, 1);
        Time time4f = new Time(2015, 10, 11, 13, 1);
        assertEquals(time4a.getDayInWeek(), DayInWeek.TUE);
        assertEquals(time4b.getDayInWeek(), DayInWeek.WED);
        assertEquals(time4c.getDayInWeek(), DayInWeek.THU);
        assertEquals(time4d.getDayInWeek(), DayInWeek.FRI);
        assertEquals(time4e.getDayInWeek(), DayInWeek.SAT);
        assertEquals(time4f.getDayInWeek(), DayInWeek.SUN);
    }

    @Test
    public void TestTimePlus() {
        Time time5 = new Time(2015, 10, 5, 13, 1);
        Time time6 = new Time(2015, 10, 5, 13, 11);
        assertEquals(time5.plus(10) + " is not equal to " + time6, time5.plus(10), time6);
    }

    @Test
    public void TestTimeDifference() {
        Time time7 = new Time(2015, 10, 5, 13, 1);
        Time time8 = new Time(2015, 10, 5, 13, 11);
        assertEquals(time7.difference(time8) + " is not equal to -10min", -10, time7.difference(time8));
    }
    
    @Test
    public void TestCompareTo() {
        Time time9 = new Time(2015, 10, 5, 13, 1);
        Time time10 = new Time(2015, 10, 5, 13, 11);
        assertEquals(1, time9.compareTo(time10));
    }
}
