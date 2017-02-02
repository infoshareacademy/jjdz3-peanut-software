package peanut;

/**
 * Created by Mariusz Szymanski on 2017-01-31.
 */

public class Survey {

    private String question;
    private int number;
    private String answerA;
    private String answerB;
    private String answerC;

    // sets and gets for all variables

    public void setQuestion(String question){
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber(){
        return number;
    }

    public void setAnswerA(String answerA){
        this.answerA = answerA;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerB(String answerB){
        this.answerB = answerB;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerC(String answerC){
        this.answerC = answerC;
    }

    public String getAnswerC() {
        return answerC;
    }

    // return a string representation of the object.

    public String toString() {
        return "{\"question\":\"" + question + "\",\"number\":\"" + number + "\",\"answerA\":\"" + answerA + "\",\"answerB\":\"" + answerB + "\",\"answerC\":\"" + answerC + "\"}";
    }


}
