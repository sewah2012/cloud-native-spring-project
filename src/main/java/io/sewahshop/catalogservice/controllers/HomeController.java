package io.sewahshop.catalogservice.controllers;

import io.sewahshop.catalogservice.Config.Props.MyProps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    private final MyProps myProps;
    HomeController(MyProps myProps) {
        this.myProps =myProps;
    }
    String greeting;
    @GetMapping("/")
    String getGreeting(){
        return myProps.getGreeting();
    }
}
