package peanut.medicine;

import peanut.medicine.survey.SurveyResult;

import java.time.LocalDate;

public class Appointment {

    private SurveyResult surveyResult;
    private Doctor doctor;
    private LocalDate term;

    public Appointment(SurveyResult surveyResult, Doctor doctor, LocalDate term) {
        this.surveyResult = surveyResult;
        this.doctor = doctor;
        this.term = term;
    }

    public SurveyResult getSurveyResult() {
        return surveyResult;
    }

    public void setSurveyResult(SurveyResult surveyResult) {
        this.surveyResult = surveyResult;
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
        return this.surveyResult.displayPatient() + " " + this.doctor.toString() + " "+this.term.getYear()+"-"+this.term.getMonth()+"-"+term.getDayOfMonth();
    }
}
