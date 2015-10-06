/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deel2;

import fontys.time.Time;
import fontys.time.TimeSpan;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author laure
 */
public class ContactTest {

    public ContactTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of getName method, of class Contact.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        Contact instance = new Contact("Henk");
        String expResult = "Henk";
        String result = instance.getName();
        assertEquals("De naam komt niet overeen", expResult, result);
    }

    /**
     * Test of addAppointment method, of class Contact.
     */
    @Test
    public void testAddAppointment()
    {
        Appointment a = new Appointment("Test", new TimeSpan(new Time(2015, 1, 1, 1, 0), new Time(2015, 1, 1, 2, 0)));
        Contact kees = new Contact("Kees");
        // TEST 1
        System.out.println("addAppointment");
        boolean result = kees.addAppointment(a);
        assertTrue("De afspraak is niet toegevoegd", result);

        // TEST 2
        Appointment b = new Appointment("Test2", new TimeSpan(new Time(2015, 1, 1, 1, 30), new Time(2015, 1, 1, 2, 0)));
        boolean result2 = kees.addAppointment(b);
        assertFalse("De overlappende afspraak is toch toegevoegd", result2);
    }

    /**
     * Test of removeAppointment method, of class Contact.
     */
    @Test
    public void testRemoveAppointment()
    {
        Appointment a = new Appointment("Test", new TimeSpan(new Time(2015, 1, 1, 1, 0), new Time(2015, 1, 1, 2, 0)));
        Contact kees = new Contact("Kees");
        kees.addAppointment(a);
        System.out.println("removeAppointment");
        kees.removeAppointment(a);
        boolean result = false;
        while (kees.appointments().hasNext())
        {
            Appointment appointment = kees.appointments().next();

            if (appointment.equals(a))
            {
                result = true;
                break;
            }
        }

        assertFalse("De afspraak is niet verwijderd", result);
    }

    /**
     * Test of appointments method, of class Contact.
     */
    @Test
    public void testAppointments()
    {
        System.out.println("appointments");
        Contact harry = new Contact("Harry");
        Appointment a = new Appointment("Test", new TimeSpan(new Time(2015, 1, 1, 1, 0), new Time(2015, 1, 1, 2, 0)));
        Appointment b = new Appointment("Test2", new TimeSpan(new Time(2015, 2, 1, 1, 0), new Time(2015, 2, 1, 2, 0)));
        harry.addAppointment(a);
        harry.addAppointment(b);

        List<Appointment> list = new ArrayList();
        list.add(a);
        list.add(b);

        Iterator<Appointment> expResult = list.iterator();
        Iterator<Appointment> result = harry.appointments();

        boolean boolResult = true;

        while (expResult.hasNext())
        {
            Appointment appointmentExpected = expResult.next();

            boolean innerResult = false;

            while (result.hasNext())
            {
                Appointment appointmentResult = result.next();

                if (appointmentExpected.equals(appointmentResult))
                {
                    innerResult = true;
                    break;
                }
            }

            boolResult = innerResult;
        }

        assertTrue("De lijst met afspraken klopt niet", boolResult);
    }

}
