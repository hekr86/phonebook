package com.henrik.phonebook.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository extends JpaRepository<Contact, Integer> {
}
