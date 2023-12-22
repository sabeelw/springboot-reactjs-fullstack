package com.koshur.springboot.customer;




import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.koshur.springboot.customer.CustomerController.user;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerIT {
    @Autowired
    private WebTestClient webClient;

    @Test
    void canRegisterACustomer(){
        //create register request
        Faker faker = new Faker();
        Name fakerName = faker.name();
        String name = fakerName.fullName();
        String email = fakerName.lastName() + UUID.randomUUID() + "@gmail.com";
        int age = new Random().nextInt(1,100);
        Customer test = new Customer(
                name, email, age
        );
        //send a post request
        webClient.post()
                .uri("/createUser")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(test), user.class)
                .exchange()
                .expectStatus()
                .isCreated();
        //get all cutomers
        List<Customer> allCustomers = webClient.get()
                .uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Customer>() {
                }).returnResult()
                .getResponseBody();
        Customer expected = new Customer(
                name, email, age
        );
        assertThat(allCustomers)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .contains(expected);
        //make sure that customer is present
        long id = allCustomers.stream().filter(customer -> customer.getEmail().equals(email)).map(Customer::getId).findFirst().orElseThrow();
        expected.setId(id);
        // get the id of customer
        webClient.get()
                .uri("/user/{id}",id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<Customer>() {
                })
                .isEqualTo(expected);
    }
    @Test
    void canDeleteACustomer(){
        //create register request
        Faker faker = new Faker();
        Name fakerName = faker.name();
        String name = fakerName.fullName();
        String email = fakerName.lastName() + UUID.randomUUID() + "@gmail.com";
        int age = new Random().nextInt(1,100);
        Customer test = new Customer(
                name, email, age
        );
        //send a post request
        webClient.post()
                .uri("/createUser")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(test), user.class)
                .exchange()
                .expectStatus()
                .isCreated();
        //get all cutomers
        List<Customer> allCustomers = webClient.get()
                .uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Customer>() {
                }).returnResult()
                .getResponseBody();
        Customer expected = new Customer(
                name, email, age
        );
        webClient.delete()
                        .uri("/deleteUser/{email}", email)
                                .accept(MediaType.APPLICATION_JSON)
                                        .exchange()
                                                .expectStatus()
                                                        .isOk();
        assertThat(allCustomers)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .contains(expected);
        //make sure that customer is present
        long id = allCustomers.stream().filter(customer -> customer.getEmail().equals(email)).map(Customer::getId).findFirst().orElseThrow();
        // get the id of customer
        webClient.get()
                .uri("/user/{id}",id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody(Customer.class).equals(new Customer(null, null, null , null, Customer.Gender.MALE));
    }

}
