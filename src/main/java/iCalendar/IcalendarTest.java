package iCalendar;

import net.fortuna.ical4j.model.Calendar;

import java.io.File;

/**
 * Created by Mariusz on 2017-03-03.
 */
public class IcalendarTest {

    public static void main(String[] args) {

        // Pathname to .ics calendar file
        File icsFile = new File("./mycalendar3.ics");

        // Parsing an iCalendar .ics file
        IcalendarReaderICS icalr = new IcalendarReaderICS();
        Calendar calendar = icalr.readCalendar(icsFile);



    }
}
