package peanut.medicine.doctor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mariusz Szymanski on 2017-05-09.
 */
public class Doctors {
    private List<Doctor> doctors;

    public Doctors(){
        doctors = new ArrayList<>();
        DoctorCalendars doctorCalendars = new DoctorCalendars();
        doctors = doctorCalendars.getDoctorsCalendars();
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
