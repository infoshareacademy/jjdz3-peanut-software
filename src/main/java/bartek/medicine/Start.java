package bartek.medicine;

import iCalendar.IcalendarReaderICS;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

/**
 * Created by bartman3000 on 2017-03-11.
 */
public class Start {

    public static void main(String[] args) throws NullPointerException, IOException {

//        IcalendarReaderICS icalr = new IcalendarReaderICS();
        Doctor doctor = new Doctor("dsfs","dfsg","");


//
        try {

            ClassLoader classLoader = doctor.getClass().getClassLoader();
            String p = classLoader.getResource("calendars").getPath();

            System.out.println(p);

            File folder = new File(p);
            File[] listOfFiles = folder.listFiles();

            for (File f : listOfFiles)
            {
                System.out.println((f.getName()));
            }

        } catch (NullPointerException e)
        {
            e.printStackTrace();
        }


//        Files.walk(Paths.get("/path/to/folder"))
//                .filter(Files::isRegularFile)
//                .forEach(System.out::println);

//
//        IcalendarReaderICS icalr = new IcalendarReaderICS();
//
//        Calendar calendar = icalr.readCalendar(icsFile);

    }

}
