package peanut.medicine.patient2doctor;

import peanut.medicine.newSurvey.SurveyResultPatient;
import java.time.LocalDate;

/**
 * Created by bartman3000 on 2017-03-12.
 */
public class Appointment {

    private SurveyResultPatient surveyResultPatient;
    private Doctor doctor;
    private LocalDate term;

    public Appointment(SurveyResultPatient surveyResultPatient, Doctor doctor, LocalDate term) {
        this.surveyResultPatient = surveyResultPatient;
        this.doctor = doctor;
        this.term = term;
    }

    public SurveyResultPatient getSurveyResultPatient() {
        return surveyResultPatient;
    }

    public void setSurveyResultPatient(SurveyResultPatient surveyResultPatient) {
        this.surveyResultPatient = surveyResultPatient;
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
}
