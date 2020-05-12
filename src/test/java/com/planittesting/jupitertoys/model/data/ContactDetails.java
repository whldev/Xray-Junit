package com.planittesting.jupitertoys.model.data;

public class ContactDetails {

    private String forename;
    private String surname;
    private String email;
    private String telephone;
    private String message;

    public ContactDetails setForename(String forename) {
        this.forename = forename;
        return this;
    }

    public ContactDetails setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public ContactDetails setEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactDetails setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public ContactDetails setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getForename() { return forename; }

    public String getSurname() { return surname; }

    public String getEmail() { return email; }

    public String getTelephone() { return telephone; }

    public String getMessage() { return message; }
}
