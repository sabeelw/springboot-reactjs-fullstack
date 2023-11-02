package com.koshur.springboot.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Boolean existsCustomerByEmail(String email);
    Boolean existsCustomerById(Long id);
    Customer getCustomerByEmail(String email);

}
