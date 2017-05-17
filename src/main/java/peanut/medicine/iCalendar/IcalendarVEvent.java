package peanut.medicine.iCalendar;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peanut.medicine.Appointment;
import peanut.medicine.Doctor;
import peanut.medicine.survey.SurveyResult;

import java.io.File;
import java.net.SocketException;
import java.text.ParseException;
import java.time.LocalDate;

/**
 * Created by Mariusz on 2017-03-18.
 */
public class IcalendarVEvent {

    private final static Logger LOGGER = LoggerFactory.getLogger(IcalendarVEvent.class);

    public static void addVisitForDoctor(Appointment appointment) {

//        Reading doctor calendar .ics file
        Doctor doctor = appointment.getDoctor();
        String filename = doctor.getCalendarFile();

        ClassLoader classLoader = doctor.getClass().getClassLoader();
        String calendarsPath = classLoader.getResource("calendars").getPath();

        File icsFile = new File(calendarsPath+"/"+filename);
        IcalendarReaderICS iReader = new IcalendarReaderICS();
        Calendar doctorCalendar = iReader.readCalendar(icsFile);

        // Create the event
        String patientName = appointment.getSurveyResult().getName() + " " + appointment.getSurveyResult().getSurname();
        String eventName = "Appointment with patient " + patientName;

        LocalDate term = appointment.getTerm();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(java.util.Calendar.MONTH, term.getMonthValue()-1);
        calendar.set(java.util.Calendar.DAY_OF_MONTH, term.getDayOfMonth());

        Date eventDate = new Date(calendar.getTime());
        VEvent patientAppointment = new VEvent(eventDate, eventName);

        // generate unique identifier
        UidGenerator ug = null;
        try {
            ug = new UidGenerator("uidGen");
        } catch (SocketException e) {
            e.printStackTrace();
        }
        Uid uid = ug.generateUid();
        patientAppointment.getProperties().add(uid);

//        add the event to the doctor calendar
        doctorCalendar.getComponents().add(patientAppointment);

//         Saving calendar -> .ics file
        IcalendarWriterICS icalendarWriterICS = new IcalendarWriterICS();
        icalendarWriterICS.writeCalendar(doctorCalendar,icsFile);
        LOGGER.info("Appointment added to Doctor " + doctor.getName() + " " + doctor.getSurname() + " calendar.");
    }

    public static void generateInvitation(Appointment appointment) throws ParseException, SocketException, NullPointerException {

        LOGGER.info("generateInvitation()");
        LOGGER.debug("generateInvitation:appointmnet:"+appointment.toString());

        //Creating a new calendar
        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("/Peanut Medicine/"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

        LocalDate term = appointment.getTerm();
        SurveyResult surveyResult = appointment.getSurveyResult();

        java.util.Calendar calendar2 = java.util.Calendar.getInstance();
        calendar2.set(java.util.Calendar.MONTH, term.getMonthValue()-1);
        calendar2.set(java.util.Calendar.DAY_OF_MONTH, term.getDayOfMonth());

        // initialise as an all-day event..
        String summary = "Appointment with doctor "+ appointment.getDoctor().getName()+" "+appointment.getDoctor().getSurname();
        VEvent visit = new VEvent(new net.fortuna.ical4j.model.Date(calendar2.getTime()), summary);

        // Generate a UID for the event..
        UidGenerator ug = new UidGenerator("1");
        visit.getProperties().add(ug.generateUid());

        calendar.getComponents().add(visit);

        //save file
        ClassLoader classLoader = appointment.getClass().getClassLoader();
        String invitationsPath = classLoader.getResource("invitations").getPath();
        LOGGER.debug("generateInvitation:invitationsPath:"+invitationsPath.toString());

        File icsFile = new File(invitationsPath+"/"+ surveyResult.getName()+""+ surveyResult.getSurname()+"-"+term.toString()+".ics");
        LOGGER.debug("generateInvitation:icsFile:"+icsFile.getPath());

        IcalendarWriterICS IcalendarWriterICS = new IcalendarWriterICS();
        IcalendarWriterICS.writeCalendar(calendar,icsFile);

        LOGGER.info("Invitation saved in:"+icsFile.getPath());
    }
}
