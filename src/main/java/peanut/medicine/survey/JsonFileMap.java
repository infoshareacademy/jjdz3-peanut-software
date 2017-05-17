package peanut.medicine.survey;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.nio.file.Files;

public class JsonFileMap {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonFileMap.class);
    /**
     * @return Survey
     * @throws Exception
     */
    public Survey makeSurveyFromJson(String resource) throws Exception
    {
        LOGGER.info("makeSurveyFromJson");
        LOGGER.debug("makeSurveyFromJson for file: "+resource);

        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource(resource).getFile());
        byte[] jsonData = Files.readAllBytes(file.toPath());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, Survey.class);
    }
}
