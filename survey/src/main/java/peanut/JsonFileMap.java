package peanut;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Mariusz Szymanski on 2017-02-01.
 */

public class JsonFileMap {

    private Survey survey[] = new Survey[3];
    private int i = 0;

    // getQuestion method creating Survey object with question and all answers from JSON file

    public Survey getQuestion(int i) {

        this.i = i;

        try
        {
            ObjectMapper mapper = new ObjectMapper();

            Map<String,Survey> surveyDataMap;

            // reading json file

            TypeReference ref = new TypeReference<Map<String,Survey>>() { };
            File fileJson = new File("survey1.json");
            surveyDataMap = mapper.readValue(fileJson, ref);

            // mapping json string to survey object

            String jsonInString2 = String.valueOf(surveyDataMap.get("question"+i));
            survey[i-1] = mapper.readValue(jsonInString2, Survey.class);
        }

        catch (JsonParseException e) { e.printStackTrace();}
        catch (JsonMappingException e) { e.printStackTrace();}
        catch (IOException e) { e.printStackTrace();}

        return survey[i-1];
    }
}
