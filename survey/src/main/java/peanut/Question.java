package peanut;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

/**
 * Created by bartman3000 on 2017-02-03.
 */
public class Question {

    private int number;
    private String text;
    private List<Answer> answers;
    private Answer selectedAnswer;

    public int getNumber() { return number; }
    public String getText() { return text; }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answer)
    {
        this.answers.add(answer);
    }

    public void setSelectedAnswer(Answer answer) {
        this.selectedAnswer = answer;
    }

    public Answer getSectedAnswer() {
        return selectedAnswer;
    }

}
