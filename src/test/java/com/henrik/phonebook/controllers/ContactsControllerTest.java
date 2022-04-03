package com.henrik.phonebook.controllers;

import com.henrik.phonebook.PhonebookApplication;
import com.henrik.phonebook.model.ContactDetails;
import com.henrik.phonebook.model.PhoneDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PhonebookApplication.class)
class ContactsControllerTest {

    @Autowired
    private ContactsController contactsController;
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
    }

    @AfterEach
    void tearDown() {
        session.clearAttributes();
    }

    @Test
    void getAllContacts() {
        var contacts = contactsController.getAllContacts();
        assertNotNull(contacts);
    }

    @Test
    @Transactional
    void addContact() {
        var contactDetails = new ContactDetails();
        contactDetails.setName("Tester Number");
        contactsController.addContact(contactDetails);
        var responseEntity = contactsController.getAllContacts();
        var contacts = responseEntity.getBody();
        assertNotNull(contacts);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertFalse(contacts.isEmpty());
        var contactDetailResponse = contacts.get(0);
        assertEquals(contactDetails.getName(), contactDetailResponse.getName(), "name did not match input");
    }

    @Test
    @Transactional
    void addPhoneNumber() {
        var contactDetails = new ContactDetails();
        contactDetails.setName("Tester Number");
        contactsController.addContact(contactDetails);
        var responseEntity = contactsController.getAllContacts();
        var contacts = responseEntity.getBody();
        assertNotNull(contacts);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertFalse(contacts.isEmpty());
        var contactDetailResponse = contacts.get(0);
        assertEquals(contactDetails.getName(), contactDetailResponse.getName(), "name did not match input");
        assertEquals(0, contactDetailResponse.getNumbers().size());
        var phoneDetails = new PhoneDetails();
        phoneDetails.setNumber("0231test");
        phoneDetails.setTypeOfNumber("Work");
        contactsController.addPhoneNumber(contactDetailResponse.getId(),phoneDetails);
        var responseUpdated = contactsController.getContactById(contactDetailResponse.getId());
        assertEquals(HttpStatus.OK, responseUpdated.getStatusCode());
    }


}