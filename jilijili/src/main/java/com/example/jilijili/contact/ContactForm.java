package com.example.jilijili.contact;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class ContactForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please enter your name!")
    private String name;
    @NotBlank(message = "Please enter your subject!")
    private String subject;
    @NotBlank(message = "Please enter your email!")
    private String email;
    @NotBlank(message = "Please enter your phone number!")
    private String phoneNumber;
    @NotBlank(message = "Please enter your query!")
    private String query;

    public ContactForm() {
    }

    public ContactForm(Long id, String name, String subject, String email, String phoneNumber, String query) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.query = query;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "ContactForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}
