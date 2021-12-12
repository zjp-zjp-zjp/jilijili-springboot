package com.example.jilijili.contact;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/contact")
@Controller
public class ContactFormController {
    private final ContactFormService contactFormService;

    public ContactFormController(ContactFormService contactFormService) {
        this.contactFormService = contactFormService;
    }

    @PostMapping("/submit")
    public String submitForm(@Validated ContactForm contactForm){
        contactFormService.saveForm(contactForm);
        return "index";
    }

    @GetMapping("/messages")
    public String listMessages(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", contactFormService.listBlog(pageable));
        return "adminContact";
    }
}
