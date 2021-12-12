package com.example.jilijili.contact_form;

import javax.persistence.*;

@Entity
@Table
public class ContactForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String name;
    private String phone_number;
    private String query;
    private String subject;

    public ContactForm() {
    }

    public ContactForm(String email, String name, String phone_number, String query, String subject) {
        this.email = email;
        this.name = name;
        this.phone_number = phone_number;
        this.query = query;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "ContactForm{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", query='" + query + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
