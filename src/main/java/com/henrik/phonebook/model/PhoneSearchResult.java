package com.henrik.phonebook.model;

import com.henrik.phonebook.hibernate.PhoneNumber;

public class PhoneSearchResult {

    private final Integer contactId;
    private String contactName;
    private String phoneNumber;
    private String typeOfNumber;

    public PhoneSearchResult(PhoneNumber number) {
        phoneNumber = number.getNumber();
        typeOfNumber = number.getType();
        var contact = number.getContact();
        contactName = contact.getName();
        contactId = contact.getId();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTypeOfNumber() {
        return typeOfNumber;
    }

    public void setTypeOfNumber(String typeOfNumber) {
        this.typeOfNumber = typeOfNumber;
    }

    public Integer getContactId() {
        return contactId;
    }
}
