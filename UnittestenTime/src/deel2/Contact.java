/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deel2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author laure
 */
public class Contact {

    private String name;
    private List<Appointment> appointments = new ArrayList();

    /**
     * Maakt een nieuw contact aan.
     *
     * @param name
     */
    public Contact(String name)
    {
        this.name = name;
    }

    /**
     * Geeft de naam van het contact terug.
     *
     * @return de naam
     */
    public String getName()
    {
        return name;
    }

    /**
     * Maakt een nieuwe afpsraak
     *
     * @param Appointment
     * @return of het gelukt is
     */
    boolean addAppointment(Appointment a)
    {
        if (!appointments.stream().noneMatch((appointment) -> (appointment.getTimeSpan().intersectionWith(a.getTimeSpan()) != null)))
        {
            return false;
        }
        appointments.add(a);
        a.addContact(this);
        return true;
    }

    /**
     * Verwijderd een afspaak
     *
     * @param Appointment
     */
    void removeAppointment(Appointment a)
    {
        if (appointments.contains(a))
        {
            appointments.remove(a);
        }
    }

    /**
     * Geeft de afsparken terug
     *
     * @return Lijst met afsparken
     */
    public Iterator<Appointment> appointments()
    {
        return appointments.iterator();
    }
}
