package peanut.medicine.patient2doctor;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peanut.medicine.appointment.Appointment;
import peanut.medicine.doctor.Doctor;
import peanut.medicine.iCalendar.IcalendarWriterICS;
import peanut.medicine.mainMenu.InputReader;

import java.io.File;
import java.net.SocketException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by bartman3000 on 2017-03-11.
 * Refactoring by Mariusz Szymanski on 2017-05-09
 */
public class PeanutMedicine {

    private final static Logger LOGGER = LoggerFactory.getLogger(PeanutMedicine.class);

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
        LOGGER.debug("generateInvitation:invitationsPath:" + invitationsPath.toString());

        File icsFile = new File(invitationsPath + "/" + patient.getName() + "" + patient.getSurname() + "-" + term.toString() + ".ics");
        LOGGER.debug("generateInvitation:icsFile:" + icsFile.getPath());

        IcalendarWriterICS IcalendarWriterICS = new IcalendarWriterICS();
        IcalendarWriterICS.writeCalendar(calendar, icsFile);

        LOGGER.info("Invitation saved in:" + icsFile.getPath());
    }


    public Appointment chooseOneTermFromProposed(List<Appointment> appointments) {
        for (int i = 1; i <= appointments.size(); i++) {
            Appointment ap = appointments.get(i - 1);
            String doctorS = ap.getDoctor().getName() + " " + ap.getDoctor().getSurname();
            String termS = ap.getTerm().getYear() + "-" + ap.getTerm().getMonth() + "-" + ap.getTerm().getDayOfMonth();

            System.out.println(i + "." + doctorS + " : " + termS);
        }

        Appointment appointmentChosen = new Appointment(new Patient(), new Doctor("", "", ""), LocalDate.now());
        Boolean isTermChosen = false;
        while (!isTermChosen) {
            System.out.println("\nWybierz numer terminu dla którego chcesz wygenerować zaproszenie:");
            InputReader inputReader = new InputReader();
            int termId = inputReader.getValueInt();

            try {

                appointmentChosen = appointments.get(termId - 1);
                isTermChosen = true;
//                return appointmentChosen;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nWybierz jeden z podanych terminów!");
                isTermChosen = false;
            }
        }
        return appointmentChosen;
    }
}
