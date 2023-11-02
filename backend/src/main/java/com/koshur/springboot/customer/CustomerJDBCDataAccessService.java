package com.koshur.springboot.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jdbc")
public class CustomerJDBCDataAccessService implements CustomerDAO {
    private final JdbcTemplate jdbcTemplate;

    public CustomerJDBCDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> selectAllCustomers() {
        var sql = """
                SELECT * from customer;
                """;
        RowMapper rowMapper;
        return this.jdbcTemplate.query(sql, (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getInt("age")));
    }

    @Override
    public Optional<Customer> selectCustomerbyId(Long id) {
        var sql = """
                SELECT * from customer
                WHERE id = ?
                """;
        Optional<Customer> obj;
        try{
            obj = this.jdbcTemplate.queryForObject(sql,((rs, rowNum) -> Optional.of(new Customer(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getInt("age")))) ,id);

        }catch (Exception ignored){
            return Optional.empty();
        }
        return obj;
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        var sql = """
                SELECT * from customer
                WHERE email = ?
                """;
        return this.jdbcTemplate.queryForObject(sql,((rs, rowNum) -> Optional.of(new Customer(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getInt("age")))) ,email);
    }

    @Override
    public Boolean userExistsByEmail(String email) {
        var sql = """
                SELECT count(email) from customer
                WHERE email = ?
                """;
        return this.jdbcTemplate.queryForObject(sql, Boolean.class, email);
    }

    @Override
    public Boolean userExistsById(Long id) {
        var sql = """
                SELECT count('id') from customer
                WHERE id = ?
                """;
        return this.jdbcTemplate.queryForObject(sql, Boolean.class, id);
    }

    @Override
    public Optional<Customer> deleteUser(String email) {
        var sql = """
                DELETE FROM customer
                WHERE email = ?;
                """;
        this.jdbcTemplate.update(sql, email);
        return Optional.ofNullable(new Customer());
    }

    @Override
    public Boolean updateUser(Customer customer) {
        var sql = """
                UPDATE customer set name = ?, age = ?
                 WHERE id = ?
                """;
        return jdbcTemplate.update(sql, customer.getName(), customer.getAge(), customer.getId()) > 0;
    }

    @Override
    public void createUser(Customer toCreate) {
        var sql = """
                INSERT INTO customer(name, email, age)
                VALUES (?, ?, ?)
                """;
        int update = jdbcTemplate.update(sql, toCreate.getName(),
                toCreate.getEmail(),
                toCreate.getAge());
        System.out.println("Query result :" + update);
    }
}
