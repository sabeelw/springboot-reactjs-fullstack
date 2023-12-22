package com.koshur.springboot.customer;

import jakarta.persistence.*;

import java.util.Objects;
/*
ORM
dfds
 */
@Entity
@Table(
        name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "email_unique",
                        columnNames = "email"
                )
        }
)
public class Customer{
    @Id
    @SequenceGenerator(
            name = "customer_id_seq",
            sequenceName = "customer_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "customer_id_seq"
    )
    private Long id;
    @Column(
            nullable = false
    )
    private String name;
    @Column(
            nullable = false
    )
    private String email;
    private Integer age;
    public enum Gender{
        MALE,
        FEMALE
    }


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    @Column(
            nullable = false
    )
    private Gender gender;


    public Customer(Long id, String name, String email, Integer age, Gender gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }
    public Customer(Long id, String name, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = Gender.MALE;
    }
    public Customer() {

    }

    public Customer(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = Gender.MALE;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(age, customer.age) && Objects.equals(gender, customer.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age, gender);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}