/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deel2;

import fontys.time.ITimeSpan;
import fontys.time.Time;
import fontys.time.TimeSpan;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gebruiker
 */
public class AppointmentTest {

    Appointment contactAppointment = new Appointment("Bobbigheid", new TimeSpan(new Time(2015, 10, 10, 10, 10), new Time(2015, 10, 10, 11, 11)));

    public AppointmentTest()
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
     * Test of getSubject method, of class Appointment.
     */
    @Test
    public void testGetSubject()
    {
        System.out.println("getSubject");
        Appointment instance = new Appointment("Create Unit Tests", new TimeSpan(new Time(2015, 10, 10, 10, 10), new Time(2015, 10, 10, 11, 11)));
        String expResult = "Create Unit Tests";
        String result = instance.getSubject();
        assertEquals(expResult+" != "+result, expResult, result);
        assertEquals(new TimeSpan(new Time(2015, 10, 10, 10, 10), new Time(2015, 10, 10, 11, 11)), instance.getTimeSpan());
        boolean nullTimeSpan = false;
        Appointment catchAppointment;
        try
        {
            catchAppointment = new Appointment("Create Unit Tests", null);
        } catch (Exception e)
        {
            nullTimeSpan = true;
        }
        assertTrue(nullTimeSpan);
    }

    /**
     * Test of getTimeSpan method, of class Appointment.
     */
    @Test
    public void testGetTimeSpan()
    {
        System.out.println("getTimeSpan");
        TimeSpan expectedTimeSpan = new TimeSpan(new Time(2015, 10, 10, 10, 10), new Time(2015, 10, 10, 11, 11));
        Appointment instance = new Appointment("Create Unit Tests", new TimeSpan(new Time(2015, 10, 10, 10, 10), new Time(2015, 10, 10, 11, 11)));
        assertEquals("Wrong timespan returned, expected " + expectedTimeSpan + ", actual " + instance.getTimeSpan(), expectedTimeSpan, instance.getTimeSpan());
    }

    /**
     * Test of addContact method, of class Appointment.
     */
    @Test
    public void testAddContact()
    {
        System.out.println("addContact");
        contactAppointment = new Appointment("Bobbigheid", new TimeSpan(new Time(2015, 10, 10, 10, 10), new Time(2015, 10, 10, 11, 11)));
        Contact Bob = new Contact("Bob");
        assertTrue("Adding contact failed, which it shouldnt.", contactAppointment.addContact(Bob));
        assertFalse("Adding contact succeeded, which it shouldnt.", contactAppointment.addContact(Bob));
    }

    /**
     * Test of invitees method, of class Appointment.
     */
    @Test
    public void testInvitees()
    {
        Iterator<Contact> invitees = contactAppointment.invitees();
        contactAppointment = new Appointment("Bobbigheid", new TimeSpan(new Time(2015, 10, 10, 10, 10), new Time(2015, 10, 10, 11, 11)));
        System.out.println("invitees");
        contactAppointment.addContact(new Contact("Bob"));
        contactAppointment.addContact(new Contact("Henk"));
        while (invitees.hasNext())
        {
            Contact invitee = invitees.next();
            assertEquals("No Bob. Only " + invitee.getName(), "Bob", invitee.getName());
            System.out.println(invitee.getName());
        }
    }

    /**
     * Test of removeContact method, of class Appointment.
     */
    @Test
    public void testRemoveContact()
    {
        contactAppointment = new Appointment("Bobbigheid", new TimeSpan(new Time(2015, 10, 10, 10, 10), new Time(2015, 10, 10, 11, 11)));
        Contact Henk = new Contact("Henk");
        Appointment a = new Appointment("Test", new TimeSpan(new Time(2015, 1, 1, 1, 0), new Time(2015, 1, 1, 2, 0)));
        a.addContact(Henk);
        a.removeContact(Henk);
        boolean result = false;
        while (a.invitees().hasNext())
        {
            Contact contact = a.invitees().next();

            if (contact.equals(Henk))
            {
                result = true;
                break;
            }
        }

        assertFalse("Het contact is niet verwijderd", result);
    }

}
