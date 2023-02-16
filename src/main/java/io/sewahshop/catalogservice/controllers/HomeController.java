package io.sewahshop.catalogservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Value("${my.greeting}")
    String greeting;
    @GetMapping("/")
    String getGreeting(){
        return greeting;
    }
}
