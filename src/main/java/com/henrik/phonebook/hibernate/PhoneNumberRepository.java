package com.henrik.phonebook.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, String> {

    @Query("SELECT pn FROM PhoneNumber pn WHERE pn.number LIKE ?1%")
    List<PhoneNumber> findMatchingNumber(String numberMatch);
}
