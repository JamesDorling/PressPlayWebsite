package com.sparta.jd.pressplay.controllers;

import com.sparta.jd.pressplay.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
    private final CustomerRepository CUSTOMER_REPOSITORY;

    @Autowired
    public CustomerController(CustomerRepository repository) { this.CUSTOMER_REPOSITORY = repository; }

    @GetMapping("/login")
    public String goToLoginPage() {return "login";}

    @GetMapping("/error")
    public String goToErrorPage() {return "error";}

}
