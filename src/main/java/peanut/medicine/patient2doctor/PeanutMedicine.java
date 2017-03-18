package peanut.medicine.patient2doctor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peanut.medicine.AnswerReader;
import peanut.medicine.iCalendar.IcalendarMeeting;
import peanut.medicine.newSurvey.SurveyResultPatient;
import peanut.medicine.iCalendar.IcalendarReaderICS;
import peanut.medicine.iCalendar.IcalendarWriterICS;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.util.UidGenerator;

import java.io.File;
import java.net.SocketException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

/**
 * Created by bartman3000 on 2017-03-11.
 */
public class PeanutMedicine {

    private final static Logger LOGGER = LoggerFactory.getLogger(PeanutMedicine.class);

    private IcalendarReaderICS IcalendarReader;
    private List<Doctor> doctors;
    private List<SurveyResultPatient> surveyResultPatients;

    public PeanutMedicine()
    {
        this.doctors = new ArrayList<Doctor>();
        this.surveyResultPatients = new ArrayList<SurveyResultPatient>();
    }

    public List<SurveyResultPatient> getSurveyResultPatients()
    {
        return this.surveyResultPatients;
    }

    public void printDoctors()
    {
        LOGGER.info("printDoctors()");
        System.out.println("Imported doctors");
        for(Doctor d : this.doctors)
        {
            System.out.println(d.toString());
        }
    }

    public List<Doctor> getDoctors()
    {
        return this.doctors;
    }


    public List<Doctor> getDoctorsEvents()
    {
        this.IcalendarReader = new IcalendarReaderICS();
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

                Calendar calendar = this.IcalendarReader.readCalendar(f);
                List<Component> vevents = calendar.getComponents("VEVENT");

                for(Component event : vevents)
                {
                    String dtStart = event.getProperty("DTSTART").getValue();
                    LocalDate term = this.getDateTimeFromICalParam(dtStart);
                    doc.addTerm(term);
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
        File elementsDir = new File(elementsPath);
        return elementsDir.listFiles();
    }

    public List<Appointment> findBestTerms (SurveyResultPatient surveyResultPatient, List<Doctor> Alldoctors)
    {
        LOGGER.info("findBestTerms()");
        LOGGER.debug("findBestTerms:surveyResultPatient:"+surveyResultPatient.toString());
        LOGGER.debug("findBestTerms:Alldoctors:"+Alldoctors);

        List<Appointment> appointments = new ArrayList<>();
        String specialization = surveyResultPatient.getPreferedSpecialization();
        String preferedDay = surveyResultPatient.getPreferedDay();
        LOGGER.debug("findBestTerms:specialization:"+specialization);

        //take only doctor with specialization
        List<Doctor> doctors = Alldoctors.stream()
                .filter(d -> d.getSpecialization().equals(specialization))
                .collect(Collectors.toList());

        LOGGER.debug("findBestTerms:Alldoctors with preffered specialization:"+doctors);
        List<LocalDate> terms = new ArrayList<>();

        //prepare list of 2 available terms for every doctor
        for(Doctor doctor : doctors)
        {
            //step1: prepare list of days for next 10 days
            LocalDate today = LocalDate.now();
            for (int i = 1; i <= 10; i++)
            {
                terms.add(today.plusDays(i));
            }

            //step2: remove days where doctor(s) already have appointment
            //step3: remove Saturday and Sundays
            Predicate<LocalDate> filterBusyDays = localDate -> !doctor.getTerms().contains(localDate);
            Predicate<LocalDate> filterWeekendDays = localDate -> localDate.getDayOfWeek() != SATURDAY && localDate.getDayOfWeek() != SUNDAY;

            List<LocalDate> selectedTerms = terms.stream()
                    .filter(filterBusyDays)
                    .filter(filterWeekendDays)
                    .collect(Collectors.toList());

            //step4: move preferable days to top of the list
            terms = this.forcePreferredDays(selectedTerms,preferedDay);

            //step 5: return 2 terms for this doctor from top of the list
            terms = terms.subList(0, 2);
            System.out.println("Odpowiedni lekarz i 2 najlepsze terminy:\n");
            System.out.println(doctor.getName()+ " " + doctor.getSurname()+":");
            System.out.println(terms);

            for (LocalDate term : terms)
            {
                Appointment appointment = new Appointment(surveyResultPatient, doctor, term);
                appointments.add(appointment);
            }
        }
        return appointments;
    }


    protected static <T> void moveElementToTop(List<T> items, T input){
        int i = items.indexOf(input);
        if(i>=0){
            items.add(0, items.remove(i));
        }
    }

    protected List<LocalDate> forcePreferredDays(List<LocalDate> terms, String preferedDay)
    {
        LOGGER.info("forcePreferredDays()");
        List<LocalDate> newTerms = new ArrayList<>(terms);
        for(LocalDate term : terms)
        {
            LOGGER.debug("forcePreferredDays:term.getDayOfWeek:"+term.getDayOfWeek().toString());
            LOGGER.debug("forcePreferredDays:term.preferedDay.toUpperCase():"+preferedDay.toUpperCase());

            if(term.getDayOfWeek().toString().equals(preferedDay.toUpperCase()))
            {
                moveElementToTop(newTerms,term);
            }
        }
        return newTerms;
    }

    public void generateInvitation(Appointment appointment) throws ParseException, SocketException, NullPointerException {

        LOGGER.info("generateInvitation()");
        LOGGER.debug("generateInvitation:appointmnet:"+appointment.toString());

        //Creating a new calendar
        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("/Peanut Medicine/"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

        LocalDate term = appointment.getTerm();
        SurveyResultPatient surveyResultPatient = appointment.getSurveyResultPatient();

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
        ClassLoader classLoader = this.getClass().getClassLoader();
        String invitationsPath = classLoader.getResource("invitations").getPath();
        LOGGER.debug("generateInvitation:invitationsPath:"+invitationsPath.toString());

        File icsFile = new File(invitationsPath+"/"+ surveyResultPatient.getName()+""+ surveyResultPatient.getSurname()+"-"+term.toString()+".ics");
        LOGGER.debug("generateInvitation:icsFile:"+icsFile.getPath());

        IcalendarWriterICS IcalendarWriterICS = new IcalendarWriterICS();
        IcalendarWriterICS.writeCalendar(calendar,icsFile);

        LOGGER.info("Invitation saved in:"+icsFile.getPath());
    }

    public void addSurveyResult(SurveyResultPatient patient)
    {
        this.surveyResultPatients.add(patient);
    }

    public void showAllPatientResults()
    {
        LOGGER.info("showAllPatientResults()");
        if(!this.surveyResultPatients.isEmpty())
        {
            int surveysCnt = this.surveyResultPatients.size();
            System.out.println("\n --------------------------------");
            System.out.println("\n Liczba kwestionariuszy: "+surveysCnt);

            for (int i = 0; i < surveysCnt; i++)
            {
                SurveyResultPatient survey = surveyResultPatients.get(i);
                System.out.println("\nId:"+i+"____________");
                System.out.println(survey.displayPatient());
            }
            System.out.println("\n --------------------------------");
        }
        else
        {
            System.out.println("Nie wprowadzono jeszcze żadnyck kwestionariuszy.");
        }
    }

    public SurveyResultPatient chooseSurveyToFindTerms() throws ParseException, SocketException {

        SurveyResultPatient survey = new SurveyResultPatient();
        Boolean isSurveyChosen = false;
        while (!isSurveyChosen)
        {
            System.out.println("\nPodaj id kwestionariusza:");
            AnswerReader answerReader = new AnswerReader();
            int surveyId = answerReader.getValueInt();

            try {

                survey = surveyResultPatients.get(surveyId);
//                List<Appointment> appointments = this.findBestTerms(survey, this.doctors);
//                for (Appointment visit : appointments)
//                {
//                    this.generateInvitation(visit);
//                }
                isSurveyChosen = true;
                return survey;
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("\nNie ma kwestionariusza o taki id!");
                isSurveyChosen = false;
            }
        }
        return survey;
    }


    public Appointment chooseOneTermFromProposed(List<Appointment> appointments)
    {
        for (int i = 1; i <= appointments.size(); i++)
        {
            Appointment ap = appointments.get(i-1);
            String doctorS = ap.getDoctor().getName()+" "+ap.getDoctor().getSurname();
            String termS = ap.getTerm().getYear()+"-"+ap.getTerm().getMonth()+"-"+ap.getTerm().getDayOfMonth();

            System.out.println(i+"."+doctorS+" : "+ termS);
        }

        Appointment appointmentChosen = new Appointment(new SurveyResultPatient(),new Doctor("","",""),LocalDate.now());
        Boolean isTermChosen = false;
        while (!isTermChosen)
        {
            System.out.println("\nWybierz numer terminu dla którego chcesz wygenerować zaproszenie:");
            AnswerReader answerReader = new AnswerReader();
            int termId = answerReader.getValueInt();

            try {

                appointmentChosen = appointments.get(termId-1);
                isTermChosen = true;
//                return appointmentChosen;
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("\nWybierz jeden z podanych terminów!");
                isTermChosen = false;
            }
        }
        return appointmentChosen;
    }

    //todo: uncomment after implementation of IcalendarMeeting's makeVeventFromApp() and addEventToCalendar
    public void addVisitForDoctor(Appointment appointment, Doctor doctor)
    {
        IcalendarMeeting icalendarMeeting = new IcalendarMeeting();
//        VEvent event = icalendarMeeting.makeVeventFromApp(appointment);
//        Calendar calendar = this.getCalendarForDoctor(doctor);
//        icalendarMeeting.addEventToCalendar(event, calendar);
    }

    public Calendar getCalendarForDoctor(Doctor doctor)
    {
        String calendarPath = doctor.getCalendarFile();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File icsFile = new File(classLoader.getResource("calendars/"+calendarPath).getFile());
        IcalendarReaderICS iReader = new IcalendarReaderICS();
        return this.IcalendarReader.readCalendar(icsFile);
    }

}
