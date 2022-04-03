package com.henrik.phonebook.model;

import com.henrik.phonebook.hibernate.PhoneNumber;

public class PhoneDetails {
    private String number;
    private String typeOfNumber;

    public PhoneDetails() {
    }

    public PhoneDetails(PhoneNumber phoneNumber) {
        this.number = phoneNumber.getNumber();
        this.typeOfNumber = phoneNumber.getType();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTypeOfNumber() {
        return typeOfNumber;
    }

    public void setTypeOfNumber(String typeOfNumber) {
        this.typeOfNumber = typeOfNumber;
    }
}
