package infoshare.academy.peanut.medicine;

/**
 * Created by bartman3000 on 2017-03-12.
 */
public class Patient {

    private String name;
    private String surname;
    private String sex;
    private Integer pesel;
    private String preferedSpecialization;
    private String preferedDay;

    public Patient(String name, String surname, String sex) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getPesel() {
        return pesel;
    }

    public void setPesel(Integer pesel) {
        this.pesel = pesel;
    }

    public String getPreferedSpecialization() {
        return preferedSpecialization;
    }

    public void setPreferedSpecialization(String preferedSpecialization) {
        this.preferedSpecialization = preferedSpecialization;
    }

    public String getPreferedDay() {
        return preferedDay;
    }

    public void setPreferedDay(String preferedDay) {
        this.preferedDay = preferedDay;
    }
}
