package domain;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Mariusz Szymanski
 */

@Entity
public class Survey implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String surname;
    private String sex;
    private String email;
    private String pesel;
    private String preferedSpecialization;
    private String preferedDay;
    private LocalDateTime creationTime;

    public Survey() {
        super();
    }

    @PrePersist
    void create() {
        creationTime = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPreferedSpecialization() {
        return preferedSpecialization;
    }

    public void setPreferedSpecialization(String preferedSpecialization) {
        this.preferedSpecialization = preferedSpecialization;
    }

    public String getPreferedDay() {
        return preferedDay;
    }

    public void setPreferedDay(String preferedDay) {
        this.preferedDay = preferedDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public JsonObject toJsonObject() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("id", this.getId());
        builder.add("name", this.getName());
        builder.add("surname", this.getSurname());
        builder.add("sex", this.getSex());
        builder.add("email", this.getEmail());
        builder.add("pesel", this.getPesel());
        builder.add("preferedSpecialization", this.getPreferedSpecialization());
        builder.add("preferedDay", this.getPreferedDay());

        return builder.build();
    }
}
