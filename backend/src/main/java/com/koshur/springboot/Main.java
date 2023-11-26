package com.koshur.springboot;

import com.koshur.springboot.customer.CustomerController;
import com.koshur.springboot.customer.CustomerDAO;
import com.koshur.springboot.customer.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.Objects;



@SpringBootApplication
public class Main {
    public static void main(String[] args){
       SpringApplication.run(Main.class, args);
    }

}
