package Survey;

import Survey.SurveyPatient;

/**
 * Created by moody on 20.02.17.
 */
public class SurveyQuestionList {

    SurveyPatient[] surveyPatients ;

    public SurveyPatient[] getSurveyPatients(){
        return surveyPatients ;
    }

    public void setSurveyPatients(SurveyPatient[] surveyPatients) {
        this.surveyPatients = surveyPatients;
    }
}
