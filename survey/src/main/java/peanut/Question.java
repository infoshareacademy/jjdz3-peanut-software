package peanut;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by bartman3000 on 2017-02-03.
 */
public class Question {

    private int number;
    private String question;
    private Iterator answers;
    private Answer selectedAnswer;

    public int getNumber() { return number; }
    public String getQuestion() { return question; }

    public Iterator getAnswers() {
        return answers;
    }

    public void setSectedAnswer(Answer answer) {
        this.selectedAnswer = answer;
    }

    public Answer getSectedAnswer() {
        return selectedAnswer;
    }

}
