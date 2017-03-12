/**
 * Created by bartman3000 on 2017-02-03.
 */

package infoshare.academy.peanut.medicine.survey;

public class TestSurvey
{
    public static void main( String[] args) throws Exception {

        JsonFileMap jsonReader = new JsonFileMap();
        Survey survey = jsonReader.makeSurveyFromJson("survey.json");
        System.out.print(survey);
    }
}
