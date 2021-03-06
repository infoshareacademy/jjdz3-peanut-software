package peanut.medicine.ICDclassification;

import peanut.medicine.mainMenu.InputReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Matheo on 15.03.2017.
 */
public class ICDreader {

    public static void readICDclassification() {

        String fileName = "src/main/resources/icd/icd.txt";
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            list = stream
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        // each line of icd.txt file shown on console one by one
        list.forEach(System.out::println);

        //  ICD value input
        System.out.println("ICD code: ");
        InputReader inputReader = new InputReader();
        String inputString = inputReader.getValueString();

        System.out.println("ICD code : " + inputString);

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            list = stream
                    .filter(line -> line.contains(inputString)) //1. filter line by inputString
                    .map(String::toUpperCase)               //2. convert all content to upper case
                    .collect(Collectors.toList());          //3. convert it into a List

        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::println);

        // save ICD to file
        try {
            Files.write(Paths.get("ICDcodehistory.txt"), list);
            System.out.println("Visit code has been saved to ICDhistoryfile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
