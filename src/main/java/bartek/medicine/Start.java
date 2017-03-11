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

        try {
            IcalendarReaderICS icalr = new IcalendarReaderICS();
            ClassLoader classLoader = icalr.getClass().getClassLoader();
            String calendarsPath = classLoader.getResource("calendars").getPath();

//            System.out.println(p);

            File calendarsDir = new File(calendarsPath );
            File[] listOfDirs = calendarsDir.listFiles();

            for (File f : listOfDirs)
            {
                String specialization = f.getName();

//                System.out.println(specialization);

                String doctorsPathPath = classLoader.getResource("calendars/"+specialization).getPath();
                System.out.println(doctorsPathPath);



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
