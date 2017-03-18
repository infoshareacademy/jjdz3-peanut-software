package peanut.medicine.survey;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by moody on 20.02.17.
 */
public class SurveyJsonReader {

    private ObjectMapper mapper = new ObjectMapper(); //<-- jacson

    public SurveyQuestionList readfromJson() {
        try {
            SurveyQuestionList surveyQuestionList = mapper.readValue(new File("./SurveyQuestions.json"), SurveyQuestionList.class);
            return surveyQuestionList;
            //List<String> questions = JsonPath.from("./SurveyQuestions.json").get("surveyPatients.question1");
            // String question1 = JsonPath.from("./SurveyQuestions.json").get("surveyPatients[].question1");
        } catch (IOException e) {
            System.out.println("brak pliku  " + e.getMessage());
            return null;
        }
    }
/*
    public SurveyPatient surveyOneQuestionFromPatch(SurveyPatient question) {

        String question1 = JsonPath.from("./SurveyQuestions.json").get("surveyPatients[0].question1");
        return question;


    }

    public SurveyPatient surveyAllQuestionsfromParch(SurveyPatient questions){

        List<String> questionsAll = JsonPath.from("./SurveyQuestions.json").get("surveyPatients[0].question1");
        return questions;
    }

*/

    private Scanner scanner;

    public SurveyJsonReader() {
        scanner = new Scanner(System.in);
    }

    public void close() {
        scanner.close();
    }

    public int getValueInt() {
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }


}