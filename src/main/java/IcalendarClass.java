
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;


/**
 * Created by moody on 16.02.17.
 */
public class IcalendarClass {

    public static void main(String[] args) throws IOException, ParserException, ValidationException {

            String calFile = "mycalendar2.ics";

            //Creating a new calendar
            net.fortuna.ical4j.model.Calendar calendar = new net.fortuna.ical4j.model.Calendar();
            calendar.getProperties().add(new ProdId("/Medicine/"));
            calendar.getProperties().add(Version.VERSION_2_0);
            calendar.getProperties().add(CalScale.GREGORIAN);

            //Creating an event
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(java.util.Calendar.MONTH, java.util.Calendar.DECEMBER);
            cal.set(java.util.Calendar.DAY_OF_MONTH, 25);

            VEvent christmas = new VEvent(new Date(cal.getTime()), "Christmas Day");
            // initialise as an all-day event..
            christmas.getProperties().getProperty(Property.DTSTART).getParameters().add(Value.DATE);

            UidGenerator uidGenerator = new UidGenerator("1");
            christmas.getProperties().add(uidGenerator.generateUid());

            calendar.getComponents().add(christmas);

            //Saving an iCalendar file
            FileOutputStream fout = new FileOutputStream(calFile);

            CalendarOutputter outputter = new CalendarOutputter();
            outputter.setValidating(false);
            outputter.output(calendar, fout);




        }
    }



