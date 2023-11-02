package com.koshur.springboot.customer;

import com.github.javafaker.Faker;
import come.koshur.springboot.AbstractTestContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerJDBCDataAccessServiceTest extends AbstractTestContainer {
    private CustomerJDBCDataAccessService test;
    private final Faker faker = getFakeData();
    @BeforeEach
    void setUp() {
        test = new CustomerJDBCDataAccessService(
                new JdbcTemplate(
                        getDataSource()
                )
        );
    }

    @Test
    void selectAllCustomers() {
        //Given
        String name = faker.name().firstName() + faker.name().lastName();
        Customer customer = new Customer(
                name, name + "@gmail.com",faker.number().numberBetween(15,90)
        );
        test.createUser(customer);
        //When
        List<Customer> customers = test.selectAllCustomers();

        //Then
        assertThat(customers).isNotEmpty();
    }

    @Test
    void willReturnEmptyWhenSelectCustomerById() {
        //Given
        Long id = -1L;
        //When
        var actual = test.selectCustomerbyId(id);
        //Then
        assertThat(actual).isEmpty();
    }


    @Test
    void selectCustomerbyId() {
        //Given
        String name = faker.name().firstName() + faker.name().lastName();
        Customer customer = new Customer(
                name, name + "@gmail.com",faker.number().numberBetween(15,90)
        );
        test.createUser(customer);
        //When
        var res = test.selectCustomerbyId(1L);
        //Then
        assertThat(res).isNotEmpty();
    }

    @Test
    void getCustomerByEmail() {
        //Given
        String name = faker.name().firstName() + faker.name().lastName();
        Customer customer = new Customer(
                name, name + "@gmail.com",faker.number().numberBetween(15,90)
        );
        test.createUser(customer);
        //When
        var actual = test.getCustomerByEmail(name+"@gmail.com");
        //Then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void userExistsByEmail() {
        //Given
        String name = faker.name().firstName() + faker.name().lastName();
        Customer customer = new Customer(
                name, name + "@gmail.com",faker.number().numberBetween(15,90)
        );
        test.createUser(customer);
        //When
        var actual = test.getCustomerByEmail(name+"@gmail.com");

        //Then
        assertThat(actual).isNotNull();
    }

    @Test
    void userExistsById() {
        //Given
        String name = faker.name().firstName() + faker.name().lastName();
        Customer customer = new Customer(
                name, name + "@gmail.com",faker.number().numberBetween(15,90)
        );
        test.createUser(customer);
        Optional<Customer> user = test.getCustomerByEmail(name + "@gmail.com");
        //When
        var actual = test.userExistsById(user.orElseThrow().getId());

        //Then
        assertThat(actual).isNotNull();
    }

    @Test
    void deleteUser() {
        String name = faker.name().firstName() + faker.name().lastName();
        Customer customer = new Customer(
                name, name + "@gmail.com",faker.number().numberBetween(15,90)
        );
        test.createUser(customer);

        test.deleteUser(name + "@gmail.com");
        //When
        var actual = test.userExistsByEmail(name + "@gmail.com");

        //Then
        assertThat(actual).isFalse();
    }

    @Test
    void updateUser() {
        String name = faker.name().firstName() + faker.name().lastName();
        Customer customer = new Customer(
                name, name + "@gmail.com",faker.number().numberBetween(15,90)
        );
        test.createUser(customer);
        Customer user = test.getCustomerByEmail(name + "@gmail.com").get();
        user.setAge(99);
        customer.setId(user.getId());
        test.updateUser(user);
        //When
        var actual = test.getCustomerByEmail(name + "@gmail.com").get();

        //Then
        assertThat(actual).isNotEqualTo(customer);
    }

    @Test
    void createUser() {
        String name = faker.name().firstName() + faker.name().lastName();
        Customer customer = new Customer(
                name, name + "@gmail.com",faker.number().numberBetween(15,90)
        );
        test.createUser(customer);
        //When
        var actual = test.userExistsByEmail(name + "@gmail.com");

        //Then
        assertThat(actual).isNotNull();
    }




}