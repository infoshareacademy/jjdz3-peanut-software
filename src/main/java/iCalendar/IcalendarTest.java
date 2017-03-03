package iCalendar;

import net.fortuna.ical4j.model.Calendar;

import java.io.File;

/**
 * Created by Mariusz on 2017-03-03.
 */
public class IcalendarTest {

    public static void main(String[] args) {

        // Pathname to .ics calendar file
        File icsFile = new File("./mycalendar2.ics");

        // Parsing an iCalendar .ics file
        IcalendarReaderICS icalr = new IcalendarReaderICS();
        Calendar calendar = icalr.readCalendar(icsFile);

        // Showing calendar file
        ShowFileICS showFileICS = new ShowFileICS();
        showFileICS.printIcsData(calendar);

        // Saving calendar -> .ics file

        IcalendarWriterICS icalendarWriterICS = new IcalendarWriterICS();
        icalendarWriterICS.writeCalendar(calendar,icsFile);



    }
}
