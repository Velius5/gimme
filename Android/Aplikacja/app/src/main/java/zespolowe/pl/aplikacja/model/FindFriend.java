package zespolowe.pl.aplikacja.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Rafał on 2016-03-31.
 */

public class FindFriend implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("surname")
    private String surname;

    @SerializedName("email")
    private String email;


    @SerializedName("active")
    private boolean active;

    @SerializedName("role")
    private int role;

    public FindFriend() {
    }

    public FindFriend(long id, String name,String surname, int role, boolean active, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.active = active;
        this.email = email;
    }
    //pobieranie listy znajomych
    public FindFriend(long id,String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;


    }
    //przesyłąnie paragonu
    public FindFriend( long id) {
        this.id = id;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "FindFriend{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", role=" + role +
                '}';
    }
}