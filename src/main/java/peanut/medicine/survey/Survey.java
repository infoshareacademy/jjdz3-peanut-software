package peanut.medicine.survey;

import peanut.medicine.AnswerReader;

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

    public SurveyResult runSurvey()
    {
        AnswerReader answerReader = new AnswerReader();
        SurveyResult surveyResult = new SurveyResult();

        for(Question q : this.getQuestions())
        {
            Boolean isValidAnswer = false;
            String answerToSave = "";
            while (!isValidAnswer)
            {
                System.out.println("\n"+q.getText()+":");

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

            surveyResult.setParam(q.getName(),answerToSave);
        }

        return surveyResult;
    }
}
