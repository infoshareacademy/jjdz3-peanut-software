package peanut.medicine.appointment;

import peanut.medicine.doctor.Doctor;
import peanut.medicine.patient2doctor.Patient;

import java.time.LocalDate;

/**
 * Created by bartman3000 on 2017-03-12.
 */
public class Appointment {

    private Patient patient;
    private Doctor doctor;
    private LocalDate term;

    public Appointment(Patient patient, Doctor doctor, LocalDate term) {
        this.patient = patient;
        this.doctor = doctor;
        this.term = term;
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

    public LocalDate getTerm() {
        return term;
    }

    public void setTerm(LocalDate term) {
        this.term = term;
    }

    public String toString()
    {
        return this.patient.displayPatient() + " " + this.doctor.toString() + " "+this.term.getYear()+"-"+this.term.getMonth()+"-"+term.getDayOfMonth();
    }
}
