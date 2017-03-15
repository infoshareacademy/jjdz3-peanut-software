package MainApp;

import Exceptions.WrongOptionsExeption;
import infoshare.academy.peanut.medicine.PeanutMedicine;
import infoshare.academy.peanut.medicine.SurveyResultPatient;
import infoshare.academy.peanut.medicine.survey.JsonFileMap;
import infoshare.academy.peanut.medicine.survey.Survey;
import infoshare.academy.peanut.medicine.survey.SurveyPrinter;

/**
 * Created by moody on 24.02.17.
 */
public class MainOptions {
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
                    System.out.println(e.getMessage());
                }
            }

            // MainMenuEnum selectedOption = MainMenuEnum.createFromInt(answerReader.getValueInt());
            switch (selectedOption) {
                case EXIT:
                    break;
                case ADD_SURVEY_PATIENT:
                    SurveyResultPatient patient = survey.runSurvey();
                    peanutMedicine.addSurveyResult(patient);
                    break;
                case PRINT_SURVEY_PATIENT:
                    peanutMedicine.showAllPatientResults();
                    break;
                default:
                    System.out.println("Błędne parametry");
            }
             // readMainOptions();

        }
        answerReader.close();
    }
}
