import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by moody on 16.02.17.
 */
public class DoctorJsonReader {

    private ObjectMapper mapper = new ObjectMapper(); //<-- jacson

    public DocotrList readfromJson() {
        try {
            DocotrList docotrList =   mapper.readValue(new File("./listaDoktorów.json"), DocotrList.class);
            return docotrList;
        } catch (IOException e) {
            System.out.println("brak pliku" + e.getMessage());
            return null;
        }
    }
}
