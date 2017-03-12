package infoshare.academy.peanut.medicine.survey;

/**
 * Created by moody on 22.02.17.
 */
public class SurveyShowQuestions {

        public void printOneQuestions(SurveyPatient question) {
            System.out.println(question.getQuestion());
            for (String answer : question.getAnswers()) {
                System.out.println(answer);
            }
        }

    }









    /*

                                    //(muszed pow co ma pkazać )

        public void printQuestions(SurveyQuestionList surveyQuestionList ){


            for (SurveyPatient oneQuestions : surveyQuestionList.getSurveyPatients()) {

                System.out.println(oneQuestions.getQuestion());
                //System.out.println(oneQuestions.getQuestion2());
            }
        }
*/