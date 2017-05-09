package peanut.medicine.survey;

import peanut.medicine.mainMenu.InputReader;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by moody on 23.02.17.
 */
public class SurveyPrinter {
    //public final String[][] allAnswers = new String[10][10] ;

    public ArrayList<String>findByLastName(ArrayList<ArrayList<String>> allAnswers, String lastName){
        for (ArrayList<String> surveyAnswer: allAnswers) {
            if(surveyAnswer.get(1).equals(lastName)) {
                return surveyAnswer;
            }

        }
        return null;
    }

    public ArrayList<ArrayList<String>> getAllAnswers(){
        return allAnswers;
    }

    private ArrayList<ArrayList<String>> allAnswers = new ArrayList<>();
    private SurveyShowQuestions surveyShowQuestions;
    private InputReader inputReader;
    private ArrayList patientAnswers = new ArrayList();

    public SurveyPrinter() {
        surveyShowQuestions = new SurveyShowQuestions();
        inputReader = new InputReader();
    }

    public void controlLoop() {

        SurveyEnumOption option;

        printOptions();

        while ((option = SurveyEnumOption.createFromInt(inputReader.getValueInt())) != SurveyEnumOption.EXIT) {

            switch (option) {
                case ADD_SURVEY_PATIENT:
                    SurveyJsonReader surveyJsonReader = new SurveyJsonReader();
                    SurveyQuestionList surveyQuestionList = surveyJsonReader.readfromJson();
                    ArrayList<String> answers = surveyPrint(surveyQuestionList);
                    allAnswers.add(answers);
                    break;
                case PRINT_SURVEY_PATIENT:
                    surveyAnswerPrint();
                    break;
                case PRINT_FIND_PATIENT_BY_LASTNAME:

                    String lastName = inputReader.getValueString();

                  ArrayList<String> writeAnswers =  findByLastName(allAnswers, lastName);
                    System.out.println(writeAnswers);
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, wprowadz ponownie ");

            }
            printOptions();

        }
        //   inputReader.close();
    }


    private void printOptions() {
        System.out.println("Wybierz opcję: ");
        for (SurveyEnumOption o : SurveyEnumOption.values()) {
            System.out.println(o);
        }
    }




    public ArrayList<String> surveyPrint(SurveyQuestionList surveyQuestionList) {
        ArrayList<String> answers = new ArrayList<>() ;
        int index = 0;
        for (SurveyPatient surveyPatient : surveyQuestionList.getSurveyPatients()) {
            try {


                surveyShowQuestions.printOneQuestions(surveyPatient);
                String answer = inputReader.getValueString();
                answers.add(answer);
                index++ ;


                if (Arrays.asList(surveyPatient.getAnswers()).contains(answer)) {
                    System.out.println("Dobra odpowiedź");
                } else {
                    System.out.println("Błedna odpowiedź");
                }

            } catch (IllegalArgumentException e) {
                System.err.println("Błedny parametr, wprowadz ponownie ");
            }
        }
        return answers;
    }

    public void surveyAnswerPrint(){
        try {


            allAnswers.get(0).get(0);
            for (ArrayList<String> answers : allAnswers) {
                for (String answer : answers) {
                    if (answer != null) {
                        System.out.println("Answer : " + answer);
                    }

                }
            }

            System.out.println(allAnswers);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Brak wprowadzonych pacjentów, proszę wprowadzić ");
        }
    }

}
