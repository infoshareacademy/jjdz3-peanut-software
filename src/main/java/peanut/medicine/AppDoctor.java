package peanut.medicine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by moody on 16.02.17.
 */
public class AppDoctor {

    private final static Logger LOGGER = LoggerFactory.getLogger(AppDoctor.class);

    public static void main(String[] args) throws Exception {

        final String appName = "Medicine V 1.08" ;
        System.out.printf(appName+"\n");

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
