package infoshare.academy.peanut.medicine;

import java.time.LocalDate;

/**
 * Created by bartman3000 on 2017-03-11.
 */
public class Term {

    private LocalDate date;

    public Term(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
