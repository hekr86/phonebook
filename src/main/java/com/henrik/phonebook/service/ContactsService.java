package com.henrik.phonebook.service;

import com.henrik.phonebook.hibernate.Contact;
import com.henrik.phonebook.hibernate.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsService {

    private final ContactsRepository contactsRepository;

    @Autowired
    public ContactsService(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public List<Contact> findAll() {
        return contactsRepository.findAll();
    }

    public void addContact(Contact contact) {
        contactsRepository.save(contact);
    }

    public Contact findById(Integer contactId) {
        return contactsRepository.getById(contactId);
    }

    public boolean deleteById(Integer contactId) {
        if (contactId == null) {
            return false;
        } else {
            try {
                contactsRepository.deleteById(contactId);
                return true;
            } catch (EmptyResultDataAccessException ex) {
                return false;
            }
        }
    }
}
