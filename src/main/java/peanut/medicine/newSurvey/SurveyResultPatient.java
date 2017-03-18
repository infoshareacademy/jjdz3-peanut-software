package peanut.medicine.newSurvey;

/**
 * Created by bartman3000 on 2017-03-12.
 */
public class SurveyResultPatient {

    private String name;
    private String surname;
    private String sex;
    private String pesel;
    private String preferedSpecialization;
    private String preferedDay;

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

            case "preferedSpecialization":
                this.setPreferedSpecialization(value.toString());
                break;

            case "preferedDay":
                this.setPreferedDay(value.toString());
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

    public String displayPatient() {
        return "Imię: "+ this.getName()+"\nNazwisko: "+this.getSurname()+ "\nPłeć: "+this.getSex()+"\nPesel: "+this.getPesel()+ "\nPotrzebny lekarz: "+this.getPreferedSpecialization()+ "\nPreferowany dzień tygodnia na wizytę: "+this.getPreferedDay();
    }

    @Override
    public String toString() {

        return this.getName()+" | "+this.getSurname()+ " | "+this.getSex()+" | "+this.getPesel()+ " | "+this.getPreferedSpecialization()+ " | "+this.getPreferedDay();
    }

    public String displayPatientName() {
        return this.getName()+" "+this.getSurname();
    }
}
