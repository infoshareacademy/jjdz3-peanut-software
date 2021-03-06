package peanut.medicine.iCalendar;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.util.UidGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peanut.medicine.appointment.Appointment;
import peanut.medicine.doctor.Doctor;

import java.io.File;
import java.net.SocketException;
import java.time.LocalDate;

/**
 * Created by Mariusz on 2017-03-18.
 */
public class IcalendarVisitEvent {

    private final static Logger LOGGER = LoggerFactory.getLogger(IcalendarVisitEvent.class);

    public static void addVisitToDoctorCalendar(Appointment appointment) {

//        Reading doctor calendar .ics file
        Doctor doctor = appointment.getDoctor();
        String filename = doctor.getCalendarFile();

        ClassLoader classLoader = doctor.getClass().getClassLoader();
        String calendarsPath = classLoader.getResource("calendars").getPath();

        File icsFile = new File(calendarsPath+"/"+filename);
        IcalendarReaderICS iReader = new IcalendarReaderICS();
        Calendar doctorCalendar = iReader.readCalendar(icsFile);

        // Create the event
        String patientName = appointment.getPatient().getName() + " " + appointment.getPatient().getSurname();
        String eventName = "Appointment for patient " + patientName;

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
}
