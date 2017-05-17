package peanut.medicine.iCalendar;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by moody on 16.02.17.
 * Updated by Mariusz on 03.03.17.
 */

public class IcalendarReaderICS {

    public Calendar readCalendar(File icsFile){

        //Now Parsing an iCalendar file
        try {
            FileInputStream fin = new FileInputStream(icsFile);
            CalendarBuilder builder = new CalendarBuilder();
            return builder.build(fin);
        }
        catch (IOException | ParserException e){
            System.out.println(e);
        }
      return null;
    }

    public static LocalDate getDateTimeFromICalParam(String dtstamp)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
            return LocalDate.parse(dtstamp,formatter);
        } catch (DateTimeParseException e)
        {
            formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
            return LocalDate.parse(dtstamp,formatter);
        }
    }
}
