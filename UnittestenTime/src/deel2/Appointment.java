/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deel2;

import fontys.time.ITimeSpan;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author dennis
 */
public class Appointment {

    private ITimeSpan timeSpan;
    private String subject;
    private List<Contact> contacts = new ArrayList();

    /**
     * maakt een nieuwe appointment aan
     *
     * @param subject
     * @param timeSpan
     */
    public Appointment(String subject, ITimeSpan timeSpan)
    {
        if (timeSpan == null)
        {
            throw new IllegalArgumentException("Tijd mag niet null zijn");
        }
        this.subject = subject;
        this.timeSpan = timeSpan;
    }

    /**
     * haalt de subject op uit het geheugen
     *
     * @return String subject
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * geeft aan van wanneer tot wanneer een vergadering is
     *
     * @return timeSpan ITimepan
     */
    public ITimeSpan getTimeSpan()
    {
        return timeSpan;
    }

    /**
     * geeft een lijst van uitgenodigden contacten
     *
     * @return
     */
    public Iterator<Contact> invitees()
    {
        return contacts.iterator();
    }

    /**
     * voegt een contact toe
     *
     * @param c
     * @return een boolean die aangeeft of het toevoegen gelukt is
     */
    public boolean addContact(Contact c)
    {
        boolean result = false;
        if (c != null)
        {
            boolean already = false;
            for (Contact contact : contacts)
            {
                if (contact.equals(c))
                {
                    already = true;
                    break;
                }
            }
            if (!already)
            {
                boolean conflict = false;

                while (c.appointments().hasNext())
                {
                    Appointment appointment = c.appointments().next();

                    if (appointment.timeSpan.intersectionWith(this.getTimeSpan()) != null)
                    {
                        conflict = true;
                        break;
                    }
                }

                if (!conflict)
                {
                    contacts.add(c);
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * haalt het gespecifieerde contact weg
     *
     * @param c
     */
    public void removeContact(Contact c)
    {
        if (c != null)
        {
            for (Contact contact : contacts)
            {
                if (contact.equals(c))
                {
                    contacts.remove(contact);
                    break;
                }
            }
        }
    }
}
