package peanut.medicine.appointment;

import peanut.medicine.doctor.Doctor;
import peanut.medicine.mainMenu.InputReader;
import peanut.medicine.patient.Patient;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by bartman3000 on 2017-03-12.
 * Edited by Mariusz Szymanski on 2017-05-10.
 */
public class ProposedTerms {

    public Appointment chooseOneTerm(List<Appointment> appointments) {
        System.out.println("Odpowiedni lekarz i 2 najlepsze terminy: \n");
        for (Appointment appointment : appointments) {
            int apId = appointments.indexOf(appointment) + 1;
            String doctorFullName = appointment.getDoctor().getFullName();
            String term = appointment.getTerm().toString();
            System.out.println("[" + apId + "] " + doctorFullName + " " + term);
        }

        Appointment appointmentChosen = new Appointment(new Patient(), new Doctor("", "", ""), LocalDate.now());
        Boolean isTermChosen = false;
        while (!isTermChosen) {
            System.out.println("\nWybierz termin wizyty:");
            InputReader inputReader = new InputReader();
            int termId = inputReader.getValueInt();

            try {
                appointmentChosen = appointments.get(termId - 1);
                isTermChosen = true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nWybierz jeden z podanych terminów!");
                isTermChosen = false;
            }
        }
        return appointmentChosen;
    }
}
