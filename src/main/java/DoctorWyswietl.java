/**
 * Created by moody on 16.02.17.
 */
public class DoctorWyswietl {


    public void printDoctors(DocotrList docotrList) {


        for (Doctor oneDoctor : docotrList.getDoctors()) {

            System.out.println(oneDoctor);
        }
    }

    public void printOneDoctor(Doctor doctor) {
        System.out.println(doctor);
    }

}
