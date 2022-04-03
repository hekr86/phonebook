package com.henrik.phonebook.factory;

import com.henrik.phonebook.hibernate.Contact;
import com.henrik.phonebook.hibernate.PhoneNumber;
import com.henrik.phonebook.model.ContactDetails;
import com.henrik.phonebook.model.PhoneDetails;
import com.henrik.phonebook.model.PhoneSearchResult;

import java.util.Collections;
import java.util.List;

public class ContactFactory {

    private ContactFactory(){}

    public static Contact createContact(ContactDetails contactDetails) {
        var contact = new Contact();
        contact.setName(contactDetails.getName());
        return contact;
    }

    public static List<ContactDetails> createContactDetails(List<Contact> contacts) {
        if (contacts == null || contacts.isEmpty()) {
            return Collections.emptyList();
        } else {
            return contacts.stream().map(ContactDetails::new).toList();
        }
    }

    public static List<PhoneDetails> createPhoneDetails(List<PhoneNumber> phoneNumbers) {
        if (phoneNumbers == null || phoneNumbers.isEmpty()) {
            return Collections.emptyList();
        } else {
            return phoneNumbers.stream().map(PhoneDetails::new).toList();
        }
    }

    public static PhoneNumber createPhoneNumber(PhoneDetails phoneDetails, Contact contact) {
        var phone = new PhoneNumber();
        phone.setNumber(phoneDetails.getNumber());
        phone.setType(phoneDetails.getTypeOfNumber());
        phone.setContact(contact);
        return phone;
    }

    public static List<PhoneSearchResult> createSearchResult(List<PhoneNumber> phoneNumbers) {
        if (phoneNumbers == null || phoneNumbers.isEmpty()) {
            return Collections.emptyList();
        } else {
            return phoneNumbers.stream().map(PhoneSearchResult::new).toList();
        }
    }
}
