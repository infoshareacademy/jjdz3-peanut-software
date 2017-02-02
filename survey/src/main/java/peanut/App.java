package peanut;

/**
 * Modified by Mariusz Szymanski on 2017-02-02.
 */

public class App 
{
    public static void main( String[] args) {

        JsonFileMap jsonReader = new JsonFileMap();
        Survey question[] = new Survey[3];

        // 'for loop' with all questions and answers for testing creating and mapping objects

        for (int i = 0; i<3; i++) {
            question[i] = jsonReader.getQuestion(i + 1);
            System.out.println("Pytanie nr " + question[i].getNumber() + ":  " + question[i].getQuestion());
            System.out.println(("A. " + question[i].getAnswerA() + "   B. " + question[i].getAnswerB() + "   C. " + question[i].getAnswerC()));
        }
    }
}
