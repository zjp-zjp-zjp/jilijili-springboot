package com.example.jilijili.service;

import com.example.jilijili.entity.ContactForm;
import com.example.jilijili.repository.ContactFormRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactFormService {
    private final ContactFormRepository contactFormRepository;

    public ContactFormService(ContactFormRepository contactFormRepository) {
        this.contactFormRepository = contactFormRepository;
    }

    public void saveForm(ContactForm contactForm){
        contactFormRepository.save(contactForm);
    }

    public Page<ContactForm> listBlog(Pageable pageable) {
        return contactFormRepository.findAll(pageable);
    }
}
