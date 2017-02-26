import Survey.Option;

/**
 * Created by moody on 24.02.17.
 */
public class MainOptions {

    Option[] options = Option.values();

    public void readMainOptions(){
       for (Option option : options) {
           System.out.println(option);

       }
    }

}
