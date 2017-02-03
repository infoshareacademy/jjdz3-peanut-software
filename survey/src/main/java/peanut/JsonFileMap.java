package peanut;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by Mariusz Szymanski on 2017-02-01.
 */

public class JsonFileMap {

    private Survey survey;

    // getQuestion method creating Survey object with question and all answers from JSON file

    public Survey getQuestions()
    {
        try
        {
            System.out.println("let start survey");
            byte[] jsonData = Files.readAllBytes(Paths.get("survey/src/main/resources/survey2.json"));
            ObjectMapper mapper = new ObjectMapper();
            Survey survey = mapper.readValue(jsonData, Survey.class);
            System.out.print(survey);
        }

        catch (JsonParseException e) { e.printStackTrace();}
        catch (JsonMappingException e) { e.printStackTrace();}
        catch (IOException e) { e.printStackTrace();}

        return survey;
    }
}
