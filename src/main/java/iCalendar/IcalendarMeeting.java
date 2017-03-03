package iCalendar;


import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.util.UidGenerator;

import java.net.SocketException;
import java.util.GregorianCalendar;

/**
 * Created by Mariusz on 2017-03-03.
 */
public class IcalendarMeeting {

    public Calendar setMeeting(Calendar calendar) {

        // Create a TimeZone
        TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
        TimeZone timezone = registry.getTimeZone( "Europe/Warsaw");
        VTimeZone tz = timezone.getVTimeZone();

        // Start Date is on: April 1, 2017, 9:00 am
        java.util.Calendar startDate = new GregorianCalendar();
        startDate.setTimeZone(timezone);
        startDate.set(java.util.Calendar.MONTH, java.util.Calendar.APRIL);
        startDate.set(java.util.Calendar.DAY_OF_MONTH, 1);
        startDate.set(java.util.Calendar.YEAR, 2017);
        startDate.set(java.util.Calendar.HOUR_OF_DAY, 9);
        startDate.set(java.util.Calendar.MINUTE, 0);
        startDate.set(java.util.Calendar.SECOND, 0);

        // End Date is on: April 1, 2017, 10:00 am
        java.util.Calendar endDate = new GregorianCalendar();
        endDate.setTimeZone(timezone);
        endDate.set(java.util.Calendar.MONTH, java.util.Calendar.APRIL);
        endDate.set(java.util.Calendar.DAY_OF_MONTH, 1);
        endDate.set(java.util.Calendar.YEAR, 2017);
        endDate.set(java.util.Calendar.HOUR_OF_DAY, 10);
        endDate.set(java.util.Calendar.MINUTE, 0);
        endDate.set(java.util.Calendar.SECOND, 0);

        // Create the event
        String eventName = "Appointment for patient A";
        DateTime start = new DateTime(startDate.getTime());
        DateTime end = new DateTime(endDate.getTime());
        VEvent meeting = new VEvent(start, end, eventName);

        // add timezone info..
        meeting.getProperties().add(tz.getTimeZoneId());

        // generate unique identifier..
        UidGenerator ug = null;
        try {
            ug = new UidGenerator("uidGen");
        } catch (SocketException e) {
            e.printStackTrace();
        }
        Uid uid = ug.generateUid();
        meeting.getProperties().add(uid);
/*
        // add attendees..
        Attendee dev1 = new Attendee(URI.create("mailto:dev1@mycompany.com"));
        dev1.getParameters().add(Role.REQ_PARTICIPANT);
        dev1.getParameters().add(new Cn("Developer 1"));
        meeting.getProperties().add(dev1);

        Attendee dev2 = new Attendee(URI.create("mailto:dev2@mycompany.com"));
        dev2.getParameters().add(Role.OPT_PARTICIPANT);
        dev2.getParameters().add(new Cn("Developer 2"));
        meeting.getProperties().add(dev2);
*/
        // Add the event and print
        calendar.getComponents().add(meeting);

        return calendar;
    }
}
