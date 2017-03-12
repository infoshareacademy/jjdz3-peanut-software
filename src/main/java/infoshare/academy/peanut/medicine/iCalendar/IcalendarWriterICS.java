package infoshare.academy.peanut.medicine.iCalendar;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.ValidationException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Mariusz on 2017-03-03.
 */
public class IcalendarWriterICS {

    public void writeCalendar(Calendar calendar, File icsFile){

        //Saving an iCalendar file
        try {
            FileOutputStream fout = new FileOutputStream(icsFile);
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.setValidating(false);
            outputter.output(calendar, fout);
        } catch (IOException | ValidationException e) {
            e.printStackTrace();
        }
    }
}
