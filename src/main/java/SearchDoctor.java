import java.io.IOException;

/**
 * Created by moody on 16.02.17.
 */
public class SearchDoctor {
/*
Wyrzuca nulla
 */
    public Doctor searchByLastname(DocotrList docotrList, String lastName){  //musimy wiedzieć w czym szukamy, i czego doctorlist jest obiekt ktry trzyma dne

                for(Doctor szukanyDoctor : docotrList.getDoctors() ){
                    //warunek który chcemy znalezc, szukany dok miał lastnam

                    if(szukanyDoctor.getLastName().equals(lastName)){
                        return szukanyDoctor;
                    }



                }
        System.out.println("Doktor nieznaleziony");
                return null;
    }

    public Doctor searchBySpecjalizacja(DocotrList docotrList, String soejalizacja){

                for(Doctor szukanyDoctor : docotrList.getDoctors()){

                    if(szukanyDoctor.getSpecjalizacja().equals(soejalizacja)){
                        return szukanyDoctor;
                    }
                }

        System.out.println("Doktor o tej specjalizacji nie odnalzeziony");
                return null ;
    }

}
