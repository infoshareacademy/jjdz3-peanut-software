package peanut.medicine.iCalendar;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;

import java.util.Iterator;

/**
 * Created by moody on 16.02.17.
 */
public class ShowFileICS {

    public void printIcsData(Calendar calendar){

        //Iterating over a Calendar
        for (Iterator i = calendar.getComponents().iterator(); i.hasNext(); ) {
            Component component = (Component) i.next();
            System.out.println("Component [" + component.getName() + "]");

            for (Iterator j = component.getProperties().iterator(); j.hasNext(); ) {
                Property property = (Property) j.next();
                System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
            }
        }//for

    }

}
