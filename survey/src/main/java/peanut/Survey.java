package peanut;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mariusz Szymanski on 2017-01-31.
 * modified by bartman3000 on 2017-02-03.
 */

public class Survey {

    private List<Question> questions;

    public List<Question> getQuestions(){
       return questions;
    }

    public void addQuestion(Question question){

        this.questions.add(question);
    }

    public Question getQuestion(int num)
    {
        return this.questions.get(num);
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
            for(Answer answer : answers)
            {
                surveyDisplay = surveyDisplay + "\n" + answer.getName() + " : " + answer.getText();
            }
        }
        return surveyDisplay;
    }
}
