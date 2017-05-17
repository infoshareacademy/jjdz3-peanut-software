package peanut.medicine.patient2doctor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Doctor {

    private String name;
    private String surname;
    private String specialization;
    private Set<LocalDate> terms;
    private String calendarFile;

    public Doctor(String name, String surname, String specialization) {
        this.name = name;
        this.surname = surname;
        this.specialization = specialization;
        this.terms = new HashSet<LocalDate>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Set<LocalDate> getTerms() {
        return terms;
    }

    public void addTerm(LocalDate d)
    {
        terms.add(d);
    }

    public String toString()
    {
        return this.specialization + " " + this.name + " "+this.surname + " " +this.calendarFile+ " " + this.terms.toString();
    }

    public String getCalendarFile() {
        return calendarFile;
    }

    public void setCalendarFile(String calendarFile) {
        this.calendarFile = calendarFile;
    }
}
