package peanut.medicine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peanut.medicine.Exceptions.WrongOptionsExeption;
import peanut.medicine.patient2doctor.PeanutMedicine;
import peanut.medicine.newSurvey.SurveyResultPatient;
import peanut.medicine.newSurvey.JsonFileMap;
import peanut.medicine.newSurvey.Survey;

/**
 * Created by moody on 24.02.17.
 */
public class MainOptions {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainOptions.class);

    AnswerReader answerReader;

    public MainOptions() {
        answerReader = new AnswerReader();
    }

    MainMenuEnum[] options = MainMenuEnum.values();

    public void readMainOptions() {
        for (MainMenuEnum option : options) {
            System.out.println(option);
        }
    }

    public void mainLoop() throws Exception {

        PeanutMedicine peanutMedicine = new PeanutMedicine();
        JsonFileMap jsonReader = new JsonFileMap();
        Survey survey = jsonReader.makeSurveyFromJson("survey.json");

        MainMenuEnum selectedOption = null;
        while (selectedOption == null || selectedOption != MainMenuEnum.EXIT) {
            readMainOptions();
            selectedOption = null;
            while (selectedOption == null ) {
                try {
                    int option = answerReader.getValueInt();
                    selectedOption = MainMenuEnum.createFromInt(option);

                } catch (WrongOptionsExeption e) {
                    LOGGER.warn(e.getMessage());
                    System.out.println(e.getMessage());
                }
            }

            // MainMenuEnum selectedOption = MainMenuEnum.createFromInt(answerReader.getValueInt());
            switch (selectedOption) {
                case EXIT:
                    break;
                case READ_DOCTORS_ICALS:
                    peanutMedicine.getDoctorsEvents();
                    peanutMedicine.printDoctors();
                    break;
                case ADD_SURVEY_PATIENT:
                    SurveyResultPatient patient = survey.runSurvey();
                    peanutMedicine.addSurveyResult(patient);
                    break;
                case PRINT_SURVEY_PATIENT:
                    peanutMedicine.showAllPatientResults();
                    break;
                case FIND_BEST_TERM:
                    if(peanutMedicine.getSurveyResultPatients().isEmpty())
                    {
                        System.out.println("\nNie wprowadzono jeszcze żadnych kwestionariuszy.");
                    }
                    else
                    {
                        peanutMedicine.chooseSurveyToFindTerms();
                    }
                    break;

                default:
                    System.out.println("Błędne parametry");
            }
             // readMainOptions();

        }
        answerReader.close();
    }
}