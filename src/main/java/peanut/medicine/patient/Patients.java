package peanut.medicine.patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peanut.medicine.mainMenu.InputReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mariusz Szymanski on 2017-05-09.
 */
public class Patients {

    private final static Logger LOGGER = LoggerFactory.getLogger(Patients.class);

    private List<Patient> patients;

    public Patients() {
        this.patients = new ArrayList<>();
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void add(Patient patient) {
        this.patients.add(patient);
        LOGGER.info("Patient added: " + patient.displayPatientName());
    }

    public void showAllQuestionnaires() {
        LOGGER.info("showAllQuestionnaires()");
        if (!this.patients.isEmpty()) {
            System.out.println("\n--------------------------------");
            System.out.println("Liczba kwestionariuszy: " + patients.size());
            for (Patient patient : patients) {
                System.out.println("--------------------------------");
                System.out.println("Id: [" + patients.indexOf(patient) + "]");
                System.out.println(patient.displayPatient());
            }
            System.out.println("--------------------------------");
        } else {
            System.out.println("Nie wprowadzono jeszcze żadnych kwestionariuszy.");
        }
    }

    public Patient choosePatient() {

        Patient patient = new Patient();
        boolean isPatientChosen = false;
        while (!isPatientChosen) {
            System.out.println("\nPodaj id kwestionariusza:");
            InputReader inputReader = new InputReader();
            int patientId = inputReader.getValueInt();

            try {
                patient = patients.get(patientId);
                isPatientChosen = true;
                return patient;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nNie ma kwestionariusza o takim id!");
                isPatientChosen = false;
            }
        }
        return patient;
    }
}
