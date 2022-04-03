package com.henrik.phonebook.controllers;

import com.henrik.phonebook.factory.ContactFactory;
import com.henrik.phonebook.model.ContactDetails;
import com.henrik.phonebook.model.PhoneDetails;
import com.henrik.phonebook.service.ContactsService;
import com.henrik.phonebook.service.PhoneNumbersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/contacts")
public class ContactsController {

    private final ContactsService contactsService;
    private final PhoneNumbersService phoneNumbersService;

    public ContactsController(ContactsService contactsService, PhoneNumbersService phoneNumbersService) {
        this.contactsService = contactsService;
        this.phoneNumbersService = phoneNumbersService;
    }

    @GetMapping("")
    public ResponseEntity<List<ContactDetails>> getAllContacts() {
        var contacts = contactsService.findAll();
        var contactDetails = ContactFactory.createContactDetails(contacts);
        return ResponseEntity.ok().body(contactDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDetails> getContactById(@PathVariable("id") Integer contactId) {
        var contact = contactsService.findById(contactId);
        if (contact == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(new ContactDetails(contact));
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> addContact(@RequestBody ContactDetails contactDetails) {
        var contactDetail = ContactFactory.createContact(contactDetails);
        contactsService.addContact(contactDetail);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/phonenumber")
    public ResponseEntity<Void> addPhoneNumber(@PathVariable("id") Integer id, @RequestBody PhoneDetails phoneDetails) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        var contact = contactsService.findById(id);
        var phoneNumber = ContactFactory.createPhoneNumber(phoneDetails, contact);
        phoneNumbersService.addPhoneNumber(phoneNumber);
        return ResponseEntity.ok().build();
    }


}
