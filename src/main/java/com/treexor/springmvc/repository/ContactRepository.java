package com.treexor.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.treexor.springmvc.model.Contact;


public interface ContactRepository extends JpaRepository<Contact, Long>{

}
