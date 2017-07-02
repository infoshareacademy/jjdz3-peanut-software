package peanut.medicine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peanut.medicine.mainMenu.MainMenu;

/**
 * Created by moody on 16.02.17
 */
public class AppDoctor {

    private final static Logger LOGGER = LoggerFactory.getLogger(AppDoctor.class);

    public static void main(String[] args) throws Exception {

        final String appName = "Medicine ver. 1.0";
        System.out.printf(appName + "\n");

        MainMenu mainMenu = new MainMenu();
        mainMenu.runMainMenu();
    }
}
