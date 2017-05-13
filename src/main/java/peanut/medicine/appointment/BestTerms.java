package peanut.medicine.appointment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peanut.medicine.doctor.Doctor;
import peanut.medicine.doctor.Doctors;
import peanut.medicine.patient.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

/**
 * Created by bartman3000 on 2017-03-12.
 * Edited by Mariusz Szymanski on 2017-05-10.
 */
public class BestTerms {

    private final static Logger LOGGER = LoggerFactory.getLogger(BestTerms.class);

    public List<Appointment> findBestTerms(Patient patient, Doctors allDoctors) {

        LOGGER.info("findBestTerms()");
        LOGGER.debug("findBestTerms:patient:" + patient.toString());
        LOGGER.debug("findBestTerms:doctors:" + allDoctors.toString());

        List<Appointment> appointments = new ArrayList<>();
        String specialization = patient.getPreferredSpecialization();
        String preferredDay = patient.getPreferredDay();
        LOGGER.debug("findBestTerms:specialization:" + specialization);

        //take only doctors with chosen specialization
        List<Doctor> doctors = allDoctors.getDoctors().stream()
                .filter(d -> d.getSpecialization().equals(specialization))
                .collect(Collectors.toList());

        LOGGER.debug("findBestTerms:doctors with preferred specialization:" + doctors);
        List<LocalDate> terms = new ArrayList<>();

        //prepare list of 2 available terms for every doctor
        for (Doctor doctor : doctors) {
            //step1: prepare list of free terms for the next 10 days
            LocalDate today = LocalDate.now();
            for (int i = 1; i <= 10; i++) {
                terms.add(today.plusDays(i));
            }
            //step2: remove days where doctor(s) already have appointment
            Predicate<LocalDate> filterBusyDays = localDate -> !doctor.getTerms().contains(localDate);
            //step3: remove Saturday and Sundays
            Predicate<LocalDate> filterWeekendDays = localDate -> localDate.getDayOfWeek() != SATURDAY && localDate.getDayOfWeek() != SUNDAY;

            List<LocalDate> selectedTerms = terms.stream()
                    .filter(filterBusyDays)
                    .filter(filterWeekendDays)
                    .collect(Collectors.toList());

            //step4: move preferable days to top of the list
            terms = this.forcePreferredDays(selectedTerms, preferredDay);

            //step 5: return 2 terms for this doctor from top of the list
            terms = terms.subList(0, 2);

            for (LocalDate term : terms) {
                Appointment appointment = new Appointment(patient, doctor, term);
                appointments.add(appointment);
            }
        }
        return appointments;
    }

    private List<LocalDate> forcePreferredDays(List<LocalDate> terms, String preferredDay) {

        LOGGER.info("forcePreferredDays()");
        List<LocalDate> newTerms = new ArrayList<>(terms);
        for (LocalDate term : terms) {
            LOGGER.debug("forcePreferredDays:term.getDayOfWeek:" + term.getDayOfWeek().toString());
            LOGGER.debug("forcePreferredDays:term.preferredDay.toUpperCase():" + preferredDay.toUpperCase());

            if (term.getDayOfWeek().toString().equals(preferredDay.toUpperCase())) {
                moveElementToTop(newTerms, term);
            }
        }
        return newTerms;
    }

    private static <T> void moveElementToTop(List<T> items, T input) {
        int i = items.indexOf(input);
        if (i >= 0) {
            items.add(0, items.remove(i));
        }
    }
}
