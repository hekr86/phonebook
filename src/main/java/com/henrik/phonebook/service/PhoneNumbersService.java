package com.henrik.phonebook.service;

import com.henrik.phonebook.hibernate.PhoneNumber;
import com.henrik.phonebook.hibernate.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PhoneNumbersService {
    private final PhoneNumberRepository phoneNumberRepository;

    @Autowired
    public PhoneNumbersService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    public List<PhoneNumber> findAll() {
        return phoneNumberRepository.findAll();
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        phoneNumberRepository.save(phoneNumber);
    }

    public List<PhoneNumber> findMatchingNumbers(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return Collections.emptyList();
        } else if (phoneNumber.isBlank()) {
            return findAll();
        } else {
            return phoneNumberRepository.findMatchingNumber(phoneNumber);
        }
    }

    public boolean deleteByNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        } else {
            try {
                phoneNumberRepository.deleteById(phoneNumber);
                return true;
            } catch (EmptyResultDataAccessException ex) {
                return false;
            }
        }
    }
}
