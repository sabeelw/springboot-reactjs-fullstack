package com.koshur.springboot.customer;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("JPA")
public class CustomerJPADataService implements CustomerDAO {
    private final CustomerRepository customerRepo;

    public CustomerJPADataService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return this.customerRepo.findAll();
    }


    @Override
    public Optional<Customer> selectCustomerbyId(Long id) {
        return this.customerRepo.findById(id);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return Optional.ofNullable(this.customerRepo.getCustomerByEmail(email));
    }

    @Override
    public Boolean userExistsByEmail(String email) {
        return this.customerRepo.existsCustomerByEmail(email);
    }

    @Override
    public Boolean userExistsById(Long id) {
        return this.customerRepo.existsCustomerById(id);
    }


    @Override
    public Optional<Customer> deleteUser(String email) {
        Customer temp = this.customerRepo.getCustomerByEmail(email);
        this.customerRepo.delete(temp);
        return Optional.ofNullable(temp);
    }

    @Override
    public Boolean updateUser(Customer customer) {
        try {
            this.customerRepo.save(customer);
        }catch (Exception ignored){
            return false;
        }
        return true;
    }

    @Override
    public void createUser(Customer toCreate) {
        this.customerRepo.save(toCreate);
    }

}
