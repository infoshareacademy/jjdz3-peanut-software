package peanut.medicine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by moody on 16.02.17.
 */
public class AppDoctor {

    private final static Logger LOGGER = LoggerFactory.getLogger(AppDoctor.class);

    public static void main(String[] args) throws Exception {

        final String appName = "Peanut Medicine V 1.1" ;
        LOGGER.info(appName + "starts" );
        System.out.println(appName+"\n");
        MainOptions mainOptions = new MainOptions();
        mainOptions.mainLoop();
    }
}
