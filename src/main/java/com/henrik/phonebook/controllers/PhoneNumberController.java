package com.henrik.phonebook.controllers;

import com.henrik.phonebook.factory.ContactFactory;
import com.henrik.phonebook.model.PhoneSearchResult;
import com.henrik.phonebook.service.PhoneNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phonenumber")
public class PhoneNumberController {

    private final PhoneNumbersService phoneNumbersService;

    @Autowired
    public PhoneNumberController(PhoneNumbersService phoneNumbersService) {
        this.phoneNumbersService = phoneNumbersService;
    }

    @GetMapping("/{number}")
    public ResponseEntity<List<PhoneSearchResult>> searchPhoneNumber(@PathVariable("number") String number) {
        var searchResult = phoneNumbersService.findMatchingNumbers(number);
        if (searchResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(ContactFactory.createSearchResult(searchResult));
        }
    }

    @DeleteMapping("/{number}")
    public ResponseEntity<Void> deletePhoneNumber(@PathVariable("number") String number) {
        var deleteResult = phoneNumbersService.deleteByNumber(number);
        if (deleteResult) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
