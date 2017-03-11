package bartek.medicine;

import java.io.File;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by bartman3000 on 2017-03-11.
 */
public class Doctor {

    private String name;
    private String surname;
    private String specialization;
    private List<Term> terms;

    public Doctor(String name, String surname, String specialization) {
        this.name = name;
        this.surname = surname;
        this.specialization = specialization;
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

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public void addTerm(LocalDate date)
    {
        this.terms.add(new Term(date));
    }
}
