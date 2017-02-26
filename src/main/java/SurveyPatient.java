import com.jayway.restassured.path.json.JsonPath;

import java.util.List;

/**
 * Created by moody on 20.02.17.
 */
public class SurveyPatient {


 private String question;
 private String[] answers ;

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


}
