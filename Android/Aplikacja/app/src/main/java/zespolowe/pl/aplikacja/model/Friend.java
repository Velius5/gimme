package zespolowe.pl.aplikacja.model;

import java.math.BigDecimal;

/**
 * Created by Rafał on 2016-04-21.
 */
public class Friend {
    String name;
    String surname;
    BigDecimal bilans;
    Long id;


    public Friend() {
    }

    public Friend(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }



    public Friend(Long id, String name, String surname, BigDecimal bilans) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.bilans = bilans;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getBilans() {
        return bilans;
    }

    public void setBilans(BigDecimal bilans) {
        this.bilans = bilans;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", bilans=" + bilans +
                ", id=" + id +
                '}';
    }
}
