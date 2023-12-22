package com.koshur.springboot.test;


import com.koshur.springboot.customer.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @GetMapping("/hi")
    public String getCustomers(){
        return "Hello World";
    }

}
