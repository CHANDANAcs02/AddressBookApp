package com.bridgelabz.model;

public class Address {

    private int id;
    private int contactId;
    private String street;
    private String city;
    private String state;
    private String pincode;

    public Address() {
    }

    public Address(int id, int contactId, String street,
                   String city, String state, String pincode) {

        this.id = id;
        this.contactId = contactId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}