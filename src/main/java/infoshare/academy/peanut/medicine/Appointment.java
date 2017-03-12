package infoshare.academy.peanut.medicine;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by bartman3000 on 2017-03-12.
 */
public class Appointment {

    private Patient patient;
    private Doctor doctor;
    private List<LocalDate> terms;

    public Appointment(Patient patient, Doctor doctor, List<LocalDate> terms) {
        this.patient = patient;
        this.doctor = doctor;
        this.terms = terms;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<LocalDate> getTerms() {
        return terms;
    }

    public void setTerms(List<LocalDate> terms) {
        this.terms = terms;
    }
}
