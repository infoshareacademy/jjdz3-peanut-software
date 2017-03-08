/**
 * Created by bartman3000 on 2017-02-03.
 */

package peanut;

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
            for(Answer answer : answers)
            {
                surveyDisplay = surveyDisplay + "\n" + answer.getName() + " : " + answer.getText();
            }
        }
        return surveyDisplay;
    }
}
