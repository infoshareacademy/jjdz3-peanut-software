package peanut.medicine.patient2doctor;

import peanut.medicine.newSurvey.JsonFileMap;
import peanut.medicine.newSurvey.Survey;
import peanut.medicine.newSurvey.SurveyResultPatient;

import java.util.List;

/**
 * Created by bartman3000 on 2017-03-16.
 */
public class TestPeanutMedicine {

    public static void main(String[] args) throws Exception {

        PeanutMedicine peanutMedicine = new PeanutMedicine();
        List<Doctor> doctors = peanutMedicine.getDoctorsEvents();
        peanutMedicine.printDoctors();

        JsonFileMap jsonReader = new JsonFileMap();
        Survey survey = jsonReader.makeSurveyFromJson("survey.json");
        SurveyResultPatient patient = survey.runSurvey();

        /**
         * to skip Survey below is testPatient
         */
//        SurveyResultPatient testSurveyResultPatient = new SurveyResultPatient();
//        testSurveyResultPatient.setName("Jan");
//        testSurveyResultPatient.setSurname("Nowak");
//        testSurveyResultPatient.setSex("man");
//        testSurveyResultPatient.setPesel("12344");
//        testSurveyResultPatient.setPreferedSpecialization("dentysta");
//        testSurveyResultPatient.setPreferedDay("friday");
//        patient = testSurveyResultPatient;

        List<Appointment> appointments = peanutMedicine.findBestTerms(patient,doctors);
        peanutMedicine.generateInvitation(appointments.get(0));
    }
}
