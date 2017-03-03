package iCalendar;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by moody on 16.02.17.
 */
public class CalendarReaderICS {

    public Calendar readCalendar(){

        //Now Parsing an iCalendar file
        //FileInputStream fin = new FileInputStream(calFile);

        try {


            FileInputStream fin = new FileInputStream(new File("./mycalendar3.ics"));

            CalendarBuilder builder = new CalendarBuilder();

            Calendar calendar = builder.build(fin);
            return calendar ;
        }catch (IOException | ParserException e){
            System.out.println(" ");

        }
      return null;
    }

}
