package Survey;

import Survey.SurveyAnswerReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by moody on 23.02.17.
 */
public class SurveyPrinter {

    private SurveyShowQuestions surveyShowQuestions;
    private SurveyAnswerReader surveyAnswerReader;
    private ArrayList patientAnswers = new ArrayList();

    public SurveyPrinter(){
        surveyShowQuestions =  new SurveyShowQuestions();
        surveyAnswerReader = new SurveyAnswerReader();
    }

    public void surveyPrint(SurveyQuestionList surveyQuestionList){

    for(SurveyPatient surveyPatient : surveyQuestionList.getSurveyPatients()) {
        try {


            surveyShowQuestions.printOneQuestions(surveyPatient);
            String answer = surveyAnswerReader.getValueString();
           // SurveyPatientAnswer surveyPatientAnswer = new SurveyPatientAnswer(surveyPatient.getQuestion(), answer);
           // patientAnswers.add(surveyPatientAnswer);
         //   patientAnswers.get

            if(Arrays.asList(surveyPatient.getAnswers()).contains(answer)){
                System.out.println("Dobra odpowiedź");
            }else {
                System.out.println("Błedna odpowiedź");
            }

        }catch (IllegalArgumentException e){
            System.err.println("Błedny parametr, wprowadz ponownie ");
        }
        }

    }

}
