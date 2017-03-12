package infoshare.academy.peanut.medicine;

import infoshare.academy.peanut.medicine.iCalendar.IcalendarReaderICS;
import infoshare.academy.peanut.medicine.survey.JsonFileMap;
import infoshare.academy.peanut.medicine.survey.Survey;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

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

    public static void main(String[] args) throws Exception {

        PeanutMedicine peanutMedicine = new PeanutMedicine();
        List<Doctor> doctors = peanutMedicine.getDoctorsEvents();
//        peanutMedicine.printDoctors();

        JsonFileMap jsonReader = new JsonFileMap();
        Survey survey = jsonReader.makeSurveyFromJson("survey.json");
//        survey.runSurvey();

        Patient testPatient = new Patient();
        testPatient.setName("Jan");
        testPatient.setSurname("Nowak");
        testPatient.setSex("man");
        testPatient.setPesel(12344);
        testPatient.setPreferedSpecialization("dentysta");
        testPatient.setPreferedDay("friday");

                System.out.println(testPatient.toString());

        peanutMedicine.findBestTerms(testPatient,doctors);

    }

    protected void printDoctors()
    {
        for(Doctor d : this.doctors)
        {
//            System.out.println(d.toString());
        }
    }

    protected List<Doctor> getDoctorsEvents()
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
        return this.doctors;
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

    public List<Appointment> findBestTerms (Patient patient, List<Doctor> Alldoctors)
    {
        List<Appointment> appointments = new ArrayList<>();
        String specialization = patient.getPreferedSpecialization();
        String preferedDay = patient.getPreferedDay();
        List<Doctor> doctors = new ArrayList<>(Alldoctors);

        System.out.println(doctors);

        //take only doctor with specialization
        for(Doctor d : Alldoctors)
        {
            if(!d.getSpecialization().equals(specialization))
            {
                doctors.remove(d);
            }
        }

        System.out.println(doctors);
        List<LocalDate> terms = new ArrayList<>();

        //prepare list of 2 available terms for every doctor
        for(Doctor d : doctors)
        {
            //step1: prepare list of days for next 10 days
            LocalDate today = LocalDate.now();
            for (int i = 1; i <= 10; i++)
            {
                terms.add(today.plusDays(i));
            }

            //step2: remove days where doctor(s) already have appointment
            terms = this.filterBusyDays(terms,d.getTerms());

            //step3: remove Saturday and Sundays
            terms = this.filterWeekendDays(terms);

            //step4: move preferable days to top of the list
            terms = this.forcePreferredDays(terms,preferedDay);

            //step 5: return 2 terms for this doctor from top of the list
            terms = terms.subList(0, 2);
            System.out.println(d.getName()+ " " + d.getSurname()+":\n");
            System.out.println(terms);

            Appointment appointment = new Appointment(patient, d, terms);
            appointments.add(appointment);
        }
        return appointments;
    }

    protected static <T> void moveElementToTop(List<T> items, T input){
        int i = items.indexOf(input);
        if(i>=0){
            items.add(0, items.remove(i));
        }
    }

    protected List<LocalDate> filterBusyDays(List<LocalDate> terms, Set<LocalDate> doctorBusyDays)
    {
        List<LocalDate> newTerms = new ArrayList<>(terms);
        for(LocalDate term : terms)
        {
            if(doctorBusyDays.contains(term))
            {
                newTerms.remove(term);
            }
        }
        return newTerms;
    }

    protected List<LocalDate> filterWeekendDays(List<LocalDate> terms)
    {
        List<LocalDate> newTerms = new ArrayList<>(terms);
        for(LocalDate term : terms)
        {
            if(term.getDayOfWeek() == SATURDAY || term.getDayOfWeek() == SUNDAY)
            {
                newTerms.remove(term);
            }
        }
        return newTerms;
    }

    protected List<LocalDate> forcePreferredDays(List<LocalDate> terms, String preferedDay)
    {
        List<LocalDate> newTerms = new ArrayList<>(terms);
        for(LocalDate term : terms)
        {
            System.out.println(term.getDayOfWeek().toString());
            System.out.println(preferedDay.toUpperCase());
            if(term.getDayOfWeek().toString().equals(preferedDay.toUpperCase()))
            {
                moveElementToTop(newTerms,term);
            }
        }
        return newTerms;
    }

}
