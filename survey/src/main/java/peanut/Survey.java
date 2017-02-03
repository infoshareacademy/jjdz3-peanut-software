package peanut;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mariusz Szymanski on 2017-01-31.
 * modified by bartman3000 on 2017-02-03.
 */

public class Survey {

    private List questions;

    public List getQuestions(){
       return questions;
    }

    public void addQuestion(Question question){
        questions.add(question);
    }

    // return a string representation of the object.
    //todo rewrite this method using loop on questions Iterator
    public String toString() {
//        return "{\"question\":\"" + question + "\",\"number\":\"" + number + "\",\"answerA\":\"" + answerA + "\",\"answerB\":\"" + answerB + "\",\"answerC\":\"" + answerC + "\"}";
        return "";
    }


}
