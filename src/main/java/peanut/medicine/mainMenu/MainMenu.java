package peanut.medicine.mainMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peanut.medicine.exceptions.WrongOptionException;
import peanut.medicine.ICDReader.ICDreaderclass;
import peanut.medicine.iCalendar.IcalendarVEvent;
import peanut.medicine.doctor.DoctorCalendars;
import peanut.medicine.survey.JsonFileMap;
import peanut.medicine.survey.Patient;
import peanut.medicine.survey.Survey;
import peanut.medicine.patient2doctor.Appointment;
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
                case ADD_SURVEY_PATIENT:
                    Patient patient = survey.runSurvey();
                    peanutMedicine.addSurveyResult(patient);
                    break;
                case PRINT_SURVEY_PATIENT:
                    peanutMedicine.showAllPatientResults();
                    break;
                case FIND_BEST_TERM:
                    if (peanutMedicine.getPatients().isEmpty()) {
                        System.out.println("\nNie wprowadzono jeszcze żadnych kwestionariuszy.");
                    } else {
                        Patient patientSurvey = peanutMedicine.chooseSurveyToFindTerms();
                        List<Appointment> bestTerms = peanutMedicine.findBestTerms(patientSurvey, peanutMedicine.getDoctors());
                        Appointment visit = peanutMedicine.chooseOneTermFromProposed(bestTerms);
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
        System.out.println("MENU GŁÓWNE");
        for (MainMenuOption option : MainMenuOption.values()) {
            System.out.println(option);
        }
    }
}
