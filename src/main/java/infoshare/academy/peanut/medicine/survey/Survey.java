/**
 * Created by bartman3000 on 2017-02-03.
 */

package infoshare.academy.peanut.medicine.survey;

import MainApp.AnswerReader;
import infoshare.academy.peanut.medicine.SurveyResultPatient;

import java.util.List;

public class Survey {

    private List<Question> questions;

    public List<Question> getQuestions(){
       return questions;
    }

    public void addQuestion(Question question){

        this.questions.add(question);
    }

    // return a string representation of the object.
    public String toString() {

        List<Question> questions = this.getQuestions();
        String surveyDisplay = "";
        for (Question question: questions)
        {
            surveyDisplay = surveyDisplay + "\n\n question:" + question.getNumber() + " : " + question.getText();
            surveyDisplay = surveyDisplay + "\n answers:";

            List<Answer> answers = question.getAnswers();
            if(answers != null)
            {
                for(Answer answer : answers)
                {
                    surveyDisplay = surveyDisplay + "\n" + answer.getNumber() + " : " + answer.getText();
                }
            }

        }
        return surveyDisplay;
    }

    public SurveyResultPatient runSurvey()
    {
        AnswerReader answerReader = new AnswerReader();
        SurveyResultPatient surveyResultPatient = new SurveyResultPatient();

        for(Question q : this.getQuestions())
        {
            Boolean isValidAnswer = false;
            String answerToSave = "";
            while (!isValidAnswer)
            {
                System.out.println(q.getText());

                if(q.getQuestionType().equals("closed"))
                {
                    q.displayAnswers();
                    Integer answer = answerReader.getValueInt();
                    isValidAnswer = q.isValidClosedAnswer(answer);
                    answerToSave = q.getAnswerValue(answer);
                }
                else
                {
                    String answer = answerReader.getValueString();
                    isValidAnswer = q.isValidOpenAnswer(answer);
                    answerToSave = answer;
                }
            }

            surveyResultPatient.setParam(q.getName(),answerToSave);
        }

        return surveyResultPatient;
    }
}
