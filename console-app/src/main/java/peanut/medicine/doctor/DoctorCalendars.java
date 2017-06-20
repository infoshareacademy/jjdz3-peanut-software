package peanut.medicine.doctor;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peanut.medicine.iCalendar.IcalendarReaderICS;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bartman3000 on 2017-03-12.
 * Edited by Mariusz Szymanski on 2017-05-09.
 */
public class DoctorCalendars {

    private final static Logger LOGGER = LoggerFactory.getLogger(DoctorCalendars.class);

    private List<Doctor> doctors = new ArrayList<>();

    public List<Doctor> getDoctorsCalendars() {
        IcalendarReaderICS icalendarReader = new IcalendarReaderICS();
        File[] listOfDirs = this.getElementsInDir("calendars");
        for (File d : listOfDirs)
        {
            String doctorSpecialization = d.getName();
            File[] listOfFiles = this.getElementsInDir("calendars/"+doctorSpecialization);

            for (File f : listOfFiles)
            {
                String doctorIdenityString = f.getName();
                String[] doctorIdenitySplitted = doctorIdenityString.split("\\.");
                String doctorName = doctorIdenitySplitted[0];
                String doctorSurname = doctorIdenitySplitted[1];
                Doctor doc = new Doctor(doctorName,doctorSurname, doctorSpecialization);
                doc.setCalendarFile(doctorSpecialization+"/"+doctorIdenityString);

                Calendar calendar = icalendarReader.readCalendar(f);
                List<Component> vevents = calendar.getComponents("VEVENT");

                for(Component event : vevents)
                {
                    String dtStart = event.getProperty("DTSTART").getValue();
                    LocalDate term = this.getDateTimeFromICalParam(dtStart);
                    doc.addTerm(term);
                }
                doctors.add(doc);
            }
        }
        return doctors;
    }

    private File[] getElementsInDir(String resource) throws NullPointerException
    {
        ClassLoader classLoader = this.getClass().getClassLoader();
        String elementsPath = classLoader.getResource(resource).getPath();
        File elementsDir = new File(elementsPath);
        return elementsDir.listFiles();
    }

    private LocalDate getDateTimeFromICalParam(String dtstamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
            return LocalDate.parse(dtstamp,formatter);
        } catch (DateTimeParseException e) {
            formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
            return LocalDate.parse(dtstamp,formatter);
        }
    }

    public void printDoctorsWithEvents() {
        LOGGER.info("printDoctorsWithEvents()");
        System.out.println("Wczytuję lekarzy...");
        for(Doctor d : this.doctors) {
            System.out.println(d.toString());
        }
    }
}