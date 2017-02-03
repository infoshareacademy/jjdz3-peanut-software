package peanut;

/**
 * Modified by Mariusz Szymanski on 2017-02-02.
 */

public class App 
{
    public static void main( String[] args) {

        JsonFileMap jsonReader = new JsonFileMap();
        Survey survey = jsonReader.getQuestions();
        System.out.print(survey);
    }
}
