/**
 * Created by bartman3000 on 2017-02-03.
 */

package infoshare.academy.peanut.medicine.survey;

public class Answer {
    private Integer number;
    private String text;

    public String getText(){
        return text;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
