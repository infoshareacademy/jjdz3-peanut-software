package peanut.medicine.mainMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peanut.medicine.appointment.BestTerms;
import peanut.medicine.doctor.Doctors;
import peanut.medicine.exceptions.WrongOptionException;
import peanut.medicine.ICDReader.ICDreaderclass;
import peanut.medicine.iCalendar.IcalendarVEvent;
import peanut.medicine.doctor.DoctorCalendars;
import peanut.medicine.patient2doctor.Patients;
import peanut.medicine.survey.JsonFileMap;
import peanut.medicine.patient2doctor.Patient;
import peanut.medicine.survey.Survey;
import peanut.medicine.appointment.Appointment;
import peanut.medicine.patient2doctor.PeanutMedicine;

import java.util.List;

/**
 * Created by moody on 24.02.17.
 * Refactoring by Mariusz Szymanski on 09.05.17.
 */
public class MainMenu {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainMenu.class);
    private InputReader inputReader;

    public MainMenu() {
        inputReader = new InputReader();
    }

    public void runMainMenu() throws Exception {

        PeanutMedicine peanutMedicine = new PeanutMedicine();
        DoctorCalendars doctorCalendars = new DoctorCalendars();
        Doctors doctors = new Doctors();
        Patients patients = new Patients();
        BestTerms appointments = new BestTerms();
        JsonFileMap jsonReader = new JsonFileMap();
        String jsonFile = "survey.json";
        Survey survey = jsonReader.makeSurveyFromJson(jsonFile);
        MainMenuOption selectedOption = null;

        while (selectedOption == null || selectedOption != MainMenuOption.EXIT) {
            readMainOptions();
            selectedOption = getSelectedOption();

            switch (selectedOption) {
                case EXIT:
                    break;
                case READ_DOCTORS_ICALS:
                    doctorCalendars.getDoctorsCalendars();
                    doctorCalendars.printDoctorsWithEvents();
                    break;
                case ADD_PATIENT_SURVEY:
                    Patient patient = survey.runSurvey();
                    patients.add(patient);
                    break;
                case PRINT_PATIENT_SURVEY:
                    patients.showAllQuestionnaires();
                    break;
                case FIND_BEST_TERM:
                    if (patients.getPatients().isEmpty()) {
                        System.out.println("\nNie wprowadzono jeszcze żadnych kwestionariuszy.");
                    } else {
                        Patient patientChosen = patients.choosePatient();
                        List<Appointment> appointmentsBestTerms = appointments.findBestTerms(patientChosen, doctors);
                        Appointment visit = peanutMedicine.chooseOneTermFromProposed(appointmentsBestTerms);
                        peanutMedicine.generateInvitation(visit);
                        IcalendarVEvent.addVisitForDoctor(visit);
                    }
                    break;
                case ICD_CLASSIFICATION:
                    ICDreaderclass icd = new ICDreaderclass();
                    icd.usingBufferedReader();
                    break;
                default: break;
            }
        }
        inputReader.close();
    }

    private MainMenuOption getSelectedOption() {
        MainMenuOption selectedOption = null;
        while (selectedOption == null) {
            try {
                int option = inputReader.getValueInt();
                selectedOption = MainMenuOption.createFromInt(option);
            } catch (WrongOptionException e) {
                LOGGER.warn(e.getMessage());
                System.out.println(e.getMessage());
            }
        }
        return selectedOption;
    }

    private void readMainOptions() {
        System.out.println("\nMENU GŁÓWNE");
        for (MainMenuOption option : MainMenuOption.values()) {
            System.out.println(option);
        }
    }
}