package com.koshur.springboot.customer;

import com.github.javafaker.Faker;
import come.koshur.springboot.AbstractTestContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest extends AbstractTestContainer {
    @Autowired
    private CustomerRepository test;
    @Autowired
    private ApplicationContext applicationContext;
    private final Faker faker = getFakeData();
    @BeforeEach
    void setUp() {

    }

    @Test
    void existsCustomerByEmail() {
        String name = faker.name().firstName() + faker.name().lastName();
        Customer customer = new Customer(
                name, name + "@gmail.com",faker.number().numberBetween(15,90)
        );
        test.save(customer);
        //When
        var actual = test.existsCustomerByEmail(name+"@gmail.com");
        //Then
        assertThat(actual).isNotNull();
    }

    @Test
    void existsCustomerById() {
        String name = faker.name().firstName() + faker.name().lastName();
        Customer customer = new Customer(
                name, name + "@gmail.com",faker.number().numberBetween(15,90)
        );
        test.save(customer);
        //When
        var actual = test.existsCustomerById(test.getCustomerByEmail(name+"@gmail.com").getId());
        //Then
        assertThat(actual).isNotNull();
    }

    @Test
    void getCustomerByEmail() {
        String name = faker.name().firstName() + faker.name().lastName();
        Customer customer = new Customer(
                name, name + "@gmail.com",faker.number().numberBetween(15,90)
        );
        test.save(customer);
        //When
        var actual = test.getCustomerByEmail(name+"@gmail.com");
        //Then
        assertThat(actual).isNotNull();
    }
}