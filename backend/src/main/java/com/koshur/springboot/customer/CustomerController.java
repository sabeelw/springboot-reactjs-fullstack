package com.koshur.springboot.customer;

import com.koshur.springboot.Main;
import com.koshur.springboot.exception.ResourceNotFound;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin( origins = "http://localhost:8080")
public class CustomerController {

    public record user(String name, String email, Integer age){};
    public record Email(String email){};
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/users")
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/user/{id}")
    public Optional<Customer> getCustomers(@PathVariable Long id){
        return Optional.of(this.customerService.getCustomerById(id).orElse(new Customer()));
    }
    @GetMapping("/email")
    public Optional<Customer> getCustomerByEmail(@RequestParam(value = "email",required = false) String email){
        return this.customerService.getCustomerByEmail(email);
    }
    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public user createCustomer(@RequestBody user customer){
        System.out.println(customer);
        this.customerService.createUser(customer);
        return customer;
    }
    @DeleteMapping("/deleteUser/{email}")
    public Optional<Customer> deleteCustomer(@PathVariable Email email){
        System.out.println(email);
        return this.customerService.deleteUser(email.email());
    }
    @PutMapping("/updateUser")
    public Boolean updateCustomer(@RequestBody Customer updatedUser){
        System.out.println(updatedUser);

        return this.customerService.updateUser(updatedUser);
    }

}
