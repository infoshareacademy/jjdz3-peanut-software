package peanut.medicine.patient2doctor;

/**
 * Created by bartman3000 on 2017-03-12.
 */
public class Patient {

    private String name;
    private String surname;
    private String sex;
    private String pesel;
    private String preferredSpecialization;
    private String preferredDay;

    public <T> void setParam(String param, T value)
    {
        switch(param)
        {
            case "name":
                this.setName(value.toString());
                break;

            case "surname":
                this.setSurname(value.toString());
                break;

            case "sex":
                this.setSex(value.toString());
                break;

            case "pesel":
                this.setPesel(value.toString());
                break;

            case "preferredSpecialization":
                this.setPreferredSpecialization(value.toString());
                break;

            case "preferredDay":
                this.setPreferredDay(value.toString());
                break;
        }
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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPreferredSpecialization() {
        return preferredSpecialization;
    }

    public void setPreferredSpecialization(String preferredSpecialization) {
        this.preferredSpecialization = preferredSpecialization;
    }

    public String getPreferredDay() {
        return preferredDay;
    }

    public void setPreferredDay(String preferredDay) {
        this.preferredDay = preferredDay;
    }

    public String displayPatient() {
        return "Imię: "+ this.getName()+"\nNazwisko: "+this.getSurname()+ "\nPłeć: "+this.getSex()+"\nPesel: "+this.getPesel()+ "\nPotrzebny lekarz: "+this.getPreferredSpecialization()+ "\nPreferowany dzień tygodnia na wizytę: "+this.getPreferredDay();
    }

    @Override
    public String toString() {
        return this.getName()+" | "+this.getSurname()+ " | "+this.getSex()+" | "+this.getPesel()+ " | "+this.getPreferredSpecialization()+ " | "+this.getPreferredDay();
    }

    public String displayPatientName() {
        return this.getName()+" "+this.getSurname();
    }
}
