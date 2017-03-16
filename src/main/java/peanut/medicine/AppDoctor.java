package peanut.medicine;

/**
 * Created by moody on 16.02.17.
 */
public class AppDoctor {

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
