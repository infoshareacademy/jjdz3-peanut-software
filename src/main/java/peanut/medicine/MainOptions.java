package peanut.medicine;

import peanut.medicine.ICDReader.ICDreaderclass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peanut.medicine.Exceptions.WrongOptionsExeption;
import peanut.medicine.iCalendar.IcalendarVEvent;
import peanut.medicine.survey.JsonFileMap;
import peanut.medicine.survey.Survey;
import peanut.medicine.survey.SurveyResult;

import java.util.List;

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

        Agenda agenda = new Agenda();
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

            switch (selectedOption) {
                case EXIT:
                    break;
                case READ_DOCTORS_ICALS:
                    agenda.getDoctorsEvents();
                    agenda.printDoctors();
                    break;
                case ADD_SURVEY_PATIENT:
                    SurveyResult surveyResult = survey.runSurvey();
                    agenda.addSurveyResult(surveyResult);
                    break;
                case PRINT_SURVEY_PATIENT:
                    agenda.showAllPatientSurveys();
                    break;
                case FIND_BEST_TERM:
                    if(agenda.getSurveyResults().isEmpty())
                    {
                        System.out.println("\nNie wprowadzono jeszcze żadnych kwestionariuszy.");
                    }
                    else
                    {
                        SurveyResult surveyResultSurvey = agenda.chooseSurveyToFindTerms();
                        List<Appointment> bestTerms = agenda.findBestTerms(surveyResultSurvey, agenda.getDoctors());
                        Appointment visit = agenda.chooseOneTermFromProposed(bestTerms);
                        IcalendarVEvent.generateInvitation(visit);
                        IcalendarVEvent.addVisitForDoctor(visit);
                    }
                    break;
                case ICD_CLASSIFICATION:
                    ICDreaderclass icd = new ICDreaderclass();
                    icd.usingBufferedReader();
                    break;
                default:
                    System.out.println("Błędne parametry");
            }
        }
        answerReader.close();
    }
}
