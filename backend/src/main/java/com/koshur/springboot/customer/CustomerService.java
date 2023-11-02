package com.koshur.springboot.customer;

import com.koshur.springboot.exception.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;

    
    public CustomerService(@Qualifier("jdbc") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
    public List<Customer> getAllCustomers(){
        return customerDAO.selectAllCustomers();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerDAO.selectCustomerbyId(id);
    }
    public void createUser(CustomerController.user user){
        if(customerDAO.userExistsByEmail(user.email())){
            throw new DuplicateResourceException("User exists");
        }else {
            customerDAO.createUser(new Customer(user.name(),user.email(), user.age()));
        }
    }
    public Optional<Customer> deleteUser(String email){
        if(customerDAO.userExistsByEmail(email)){
            return customerDAO.deleteUser(email);
        }
        return Optional.empty();
    }
    public Boolean updateUser(Customer user){
        if(this.customerDAO.userExistsById(user.getId())){
            return this.customerDAO.updateUser(user);
        }
        return false;

    }
    public Optional<Customer> getCustomerByEmail(String email){
        if(email.isEmpty() || !this.customerDAO.userExistsByEmail(email)){
            return Optional.empty();
        }
        return this.customerDAO.getCustomerByEmail(email);
    }
}
