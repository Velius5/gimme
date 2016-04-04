package zespolowe.pl.aplikacja;

/**
 * Created by Rafał on 2016-03-31.
 */

public class User {


    private long id;


    private String name;

    private String surname;


    private String email;


    private byte[] image;


    private boolean active;

    private int role;




    public User(String name, String surname, String email, boolean active, int role) {
        this.name = name;
        this.surname = surname;
        this.email = email;

        this.active = active;
        this.role = role;
    }


    public long getId() {
        return id;
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


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }


}