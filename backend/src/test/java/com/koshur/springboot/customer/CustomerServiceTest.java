package com.koshur.springboot.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerDAO customerDAO;
    private CustomerService underTest;
    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerDAO);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllCustomers() {
        //Given
        underTest.getAllCustomers();
        //When
        verify(customerDAO).selectAllCustomers();
        //Then
    }

    @Test
    void getCustomerById() {
        //Given


        //When

        //Then
    }

    @Test
    void createUser() {
        //Given

        //When

        //Then
    }

    @Test
    void deleteUser() {
        //Given

        //When

        //Then
    }

    @Test
    void updateUser() {
        //Given

        //When

        //Then
    }

    @Test
    void getCustomerByEmail() {
        //Given

        //When

        //Then
    }
}