package peanut.medicine.newSurvey;

import org.apache.commons.lang.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bartman3000 on 2017-02-03.
 */
public class Question {

    private int number;
    private String name;
    private String text;
    private String questionType;
    private List<Answer> answers;
    private Answer selectedAnswer;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answer getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(Answer selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public void displayAnswers()
    {
        for(Answer a : this.answers)
        {
            System.out.println(a.getNumber()+":"+a.getText());
        }
    }

    public String getAnswerValue(Integer number)
    {
        for(Answer a : this.answers)
        {
            if (a.getNumber().equals(number))
            {
                return a.getValue();
            }
        }
        return "";
    }

    public boolean isValidOpenAnswer(String answer)
    {
        String answerType = NumberUtils.isNumber(answer) ? "Integer" : answer.getClass().getSimpleName();

//        System.out.println("answerType:"+answerType);

        if(answer.isEmpty())
        {
            System.out.println("Podaj odpowiedź.");
            return false;
        }

        return true;
    }

    public boolean isValidClosedAnswer(Integer answer)
    {
        List<Integer> possibleNumbers = new ArrayList<>();
        for(Answer a : this.getAnswers())
        {
            possibleNumbers.add(a.getNumber());
        }

        if(possibleNumbers.contains(answer.intValue()))
        {
            return true;
        }
        else {
            System.out.println("Zły wybór. Wybierz jeden z numerów odpowiedzi");
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
