/**
 * Created by bartman3000 on 2017-02-03.
 */

package peanut.medicine.survey;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JsonFileMap {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonFileMap.class);

    public Survey makeSurveyFromJson(String resource) {
        LOGGER.info("makeSurveyFromJson");
        LOGGER.debug("makeSurveyFromJson for file: " + resource);
        Survey survey = new Survey();
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = this.getClass().getClassLoader();
        String jsonFilePath = classLoader.getResource(resource).getFile();
        File file = new File(jsonFilePath);
        try {
            byte[] jsonData = Files.readAllBytes(file.toPath());
            survey = mapper.readValue(jsonData, Survey.class);
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas wczytywania pliku z ankietą.");
            LOGGER.warn(e.getMessage());
        }
        LOGGER.info("Survey created successfully");
        return survey;
    }
}
