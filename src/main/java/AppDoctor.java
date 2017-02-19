import com.fasterxml.jackson.databind.ObjectMapper;
import net.fortuna.ical4j.model.Calendar;

import java.io.File;
import java.io.IOException;

/**
 * Created by moody on 16.02.17.
 */
public class AppDoctor {

    public static void main(String[] args) {




        DoctorJsonReader doctorJsonReader = new DoctorJsonReader(); //tworzymy reader
        DocotrList docotrList = doctorJsonReader.readfromJson();  //zapisujemy wynik czytaj plik
        DoctorWyswietl doctorWyswietl = new DoctorWyswietl() ;  // tworzymy wyswitlacza
        doctorWyswietl.printDoctors(docotrList);  //wyswietl wynik
        SearchDoctor searchDoctor = new SearchDoctor();
        Doctor doctor = searchDoctor.searchByLastname(docotrList, "Kowalski"); //do czego zapisujemy = wynik wyszukiwania
        doctorWyswietl.printOneDoctor(doctor);
        doctor = searchDoctor.searchByLastname(docotrList, "Kowalski2");
        doctorWyswietl.printOneDoctor(doctor);

        Doctor doctorSpecjalizcja = searchDoctor.searchBySpecjalizacja(docotrList, "Ginekolog");
        doctorWyswietl.printOneDoctor(doctorSpecjalizcja);

        Doctor doctorSpecjalizcja2 = searchDoctor.searchBySpecjalizacja(docotrList, "Pediatra");
        doctorWyswietl.printOneDoctor(doctorSpecjalizcja2);


        CalendarReaderICS calendarReaderICS = new CalendarReaderICS();  //tworzymy to co czyta cal
        Calendar calendar = calendarReaderICS.readCalendar(); // wczytujmy z pliku i zapisujemy do zmiennej calendar
        ShowFileICS showFileICS = new ShowFileICS() ; //tworze obiekt ktory wyswietli
        showFileICS.printIcsData(calendar); // wyswiewtlamy



    }




}
