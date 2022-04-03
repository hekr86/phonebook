package com.henrik.phonebook.model;

import com.henrik.phonebook.factory.ContactFactory;
import com.henrik.phonebook.hibernate.Contact;

import java.util.List;

public class ContactDetails {
    private Integer id;
    private String name;
    private List<PhoneDetails> numbers;

    public ContactDetails() {
    }

    public ContactDetails(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        numbers = ContactFactory.createPhoneDetails(contact.getPhoneNumbers());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PhoneDetails> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<PhoneDetails> numbers) {
        this.numbers = numbers;
    }

    public Integer getId() {
        return id;
    }
}
