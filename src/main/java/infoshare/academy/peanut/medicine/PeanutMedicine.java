package infoshare.academy.peanut.medicine;

import iCalendar.IcalendarReaderICS;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bartman3000 on 2017-03-11.
 */
public class PeanutMedicine {

    private IcalendarReaderICS IcalendarReader;
    private List<Doctor> doctors;

    public PeanutMedicine()
    {
        this.doctors = new ArrayList<Doctor>();
    }

    public static void main(String[] args) {

        PeanutMedicine peanutMedicine = new PeanutMedicine();
        peanutMedicine.getDoctorsEvents();
        peanutMedicine.printDoctors();
    }

    protected void printDoctors()
    {
        for(Doctor d : this.doctors)
        {
//            System.out.println(d.toString());
        }
    }

    protected void getDoctorsEvents()
    {
        this.IcalendarReader = new IcalendarReaderICS();
        File[] listOfDirs = this.getElementsInDir("calendars");
        for (File d : listOfDirs)
        {
            String doctorSpecialization = d.getName();
//            System.out.println(doctorSpecialization);

            File[] listOfFiles = this.getElementsInDir("calendars/"+doctorSpecialization);

            for (File f : listOfFiles)
            {
                String doctorIdenityString = f.getName();
                String[] doctorIdenitySplitted = doctorIdenityString.split("\\.");
                String doctorName = doctorIdenitySplitted[0];
                String doctorSurname = doctorIdenitySplitted[1];

                Doctor doc = new Doctor(doctorName,doctorSurname, doctorSpecialization);

                Calendar calendar = this.IcalendarReader.readCalendar(f);
                List<Component> vevents = calendar.getComponents("VEVENT");

                for(Component event : vevents)
                {
                    String dtStart = event.getProperty("DTSTART").getValue();
                    LocalDate term = this.getDateTimeFromICalParam(dtStart);
                    doc.addTerm(term);
                    System.out.println(doc.toString());
                }

                this.doctors.add(doc);
            }
        }
    }

    protected LocalDate getDateTimeFromICalParam(String dtstamp)
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


    protected File[] getElementsInDir(String resource) throws NullPointerException
    {
        ClassLoader classLoader = this.getClass().getClassLoader();
        String elementsPath = classLoader.getResource(resource).getPath();
//        System.out.println(resource + ":" + elementsPath);
        File elementsDir = new File(elementsPath);
        return elementsDir.listFiles();
    }


}
