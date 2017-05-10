package peanut.medicine.appointment;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peanut.medicine.iCalendar.IcalendarWriterICS;
import peanut.medicine.patient.Patient;

import java.io.File;
import java.net.SocketException;
import java.text.ParseException;
import java.time.LocalDate;

/**
 * Created by Mariusz Szymanski on 2017-05-10.
 */
public class Invitation {

    private final static Logger LOGGER = LoggerFactory.getLogger(Invitation.class);

    public void generateInvitation(Appointment appointment) throws ParseException, SocketException, NullPointerException {

        LOGGER.info("generateInvitation()");
        LOGGER.debug("generateInvitation:appointmnet:" + appointment.toString());

        //Creating a new calendar
        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("/Peanut Medicine/"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

        LocalDate term = appointment.getTerm();
        Patient patient = appointment.getPatient();

        java.util.Calendar calendar2 = java.util.Calendar.getInstance();
        calendar2.set(java.util.Calendar.MONTH, term.getMonthValue() - 1);
        calendar2.set(java.util.Calendar.DAY_OF_MONTH, term.getDayOfMonth());

        // initialise as an all-day event..
        String summary = "Appointment with doctor " + appointment.getDoctor().getName() + " " + appointment.getDoctor().getSurname();
        VEvent visit = new VEvent(new net.fortuna.ical4j.model.Date(calendar2.getTime()), summary);

        // Generate a UID for the event..
        UidGenerator ug = new UidGenerator("1");
        visit.getProperties().add(ug.generateUid());

        calendar.getComponents().add(visit);

        //save file
        ClassLoader classLoader = this.getClass().getClassLoader();
        String invitationsPath = classLoader.getResource("invitations").getPath();
        LOGGER.debug("generateInvitation:invitationsPath: " + invitationsPath);

        File icsFile = new File(invitationsPath + "/" + patient.getName() + "" + patient.getSurname() + "-" + term.toString() + ".ics");
        LOGGER.debug("generateInvitation:icsFile:" + icsFile.getPath());

        IcalendarWriterICS IcalendarWriterICS = new IcalendarWriterICS();
        IcalendarWriterICS.writeCalendar(calendar, icsFile);

        LOGGER.info("Invitation saved in:" + icsFile.getPath());
    }
}
