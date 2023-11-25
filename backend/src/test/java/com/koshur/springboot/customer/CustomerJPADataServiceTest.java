package com.koshur.springboot.customer;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CustomerJPADataServiceTest {
    @Autowired
    private CustomerJPADataService test;
    private AutoCloseable autoCloseable;
    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        test = new CustomerJPADataService(customerRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void selectAllCustomers() {
        //Given
        test.selectAllCustomers();
        //When
        Mockito.verify(customerRepository).findAll();
        //Then
    }

    @Test
    void selectCustomerbyId() {
        Long id = 1L;
        //Given
        test.selectCustomerbyId(id);
        //When
        Mockito.verify(customerRepository).findById(id);
        //Then
    }

    @Test
    void getCustomerByEmail() {
        String email = "sabeelw@gmail.com";
        //Given
        test.getCustomerByEmail(email);
        //When
        Mockito.verify(customerRepository).getCustomerByEmail(email);
        //Then
    }

    @Test
    void userExistsByEmail() {
        String email = "sabeelw@gmail.com";
        //Given
        test.userExistsByEmail(email);
        //When
        Mockito.verify(customerRepository).existsCustomerByEmail(email);
    }

    @Test
    void userExistsById() {
        Long id = 1L;
        //Given
        test.userExistsById(id);
        //When
        Mockito.verify(customerRepository).existsCustomerById(id);
        //Then
    }

    @Test
    void deleteUser() {
        String email = "sabeelw@gmail.com";
        //Given
        test.deleteUser(email);
        Customer temp = this.customerRepository.getCustomerByEmail(email);
        //When
        Mockito.verify(customerRepository).delete(temp);
    }

    @Test
    void updateUser() {
        Customer user = new Customer();
        //Given
        test.updateUser(user);
        //When
        Mockito.verify(customerRepository).save(user);
        //Then
        //Then
    }

    @Test
    void createUser() {
        Customer user = new Customer();
        //Given
        test.updateUser(user);
        //When
        Mockito.verify(customerRepository).save(user);
        //Then
    }
}