package MainApp;

import Survey.SurveyJsonReader;
import Survey.SurveyPrinter;
import Survey.SurveyQuestionList;

import java.io.File;
import java.io.IOException;

/**
 * Created by moody on 16.02.17.
 */
public class AppDoctor {

    public static void main(String[] args) {

        final String appName = "Medicine V 1.08" ;
        System.out.printf(appName);


       MainOptions mainOptions = new MainOptions();
       mainOptions.mainLoop();
       //mainOptions.readMainOptions();

       // SurveyJsonReader surveyJsonReader = new SurveyJsonReader() ;
       // SurveyQuestionList surveyQuestionList = surveyJsonReader.readfromJson();

        /*
        SurveyPrinter surveyPrinter = new SurveyPrinter();
        surveyPrinter.surveyPrint(surveyQuestionList);
        //surveyShowQuestions.printQuestions(surveyQuestionList);
*/
    }




}
