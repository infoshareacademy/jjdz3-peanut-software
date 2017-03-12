/**
 * Created by bartman3000 on 2017-02-03.
 */

package infoshare.academy.peanut.medicine.survey;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;

public class JsonFileMap {

    /**
     * @return Survey
     * @throws Exception
     */
    public Survey makeSurveyFromJson(String resource) throws Exception
    {
        System.out.println("Let's start survey");
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("survey.json").getFile());
        byte[] jsonData = Files.readAllBytes(file.toPath());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, Survey.class);
    }
}
