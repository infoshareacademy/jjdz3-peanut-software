/**
 * Created by bartman3000 on 2017-02-03.
 */

package peanut;

public class App
{
    public static void main( String[] args) throws Exception {

        JsonFileMap jsonReader = new JsonFileMap();
        Survey survey = jsonReader.makeSurveyFromJson();
        System.out.print(survey);
    }
}
