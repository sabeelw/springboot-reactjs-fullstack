package com.koshur.springboot.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> selectAllCustomers();
    Optional<Customer> selectCustomerbyId(Long id);
    Optional<Customer> getCustomerByEmail(String email);
    Boolean userExistsByEmail(String email);
    Boolean userExistsById(Long id);

    Optional<Customer> deleteUser(String email);

    Boolean updateUser(Customer customer);
    void createUser(Customer toCreate);
}
