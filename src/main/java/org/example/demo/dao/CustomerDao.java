package org.example.demo.dao;

import org.example.demo.dao.mappers.CustomerRowMapper;
import org.example.demo.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

public class CustomerDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";

        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }

    public Customer findOne(Long customerId) {
        String sql = "SELECT * FROM customer WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), customerId);
    }

    public List<Customer> findByFirstNameAndLastName(String query) {
        String sql = "SELECT * FROM customer WHERE firstname LIKE ? OR lastname LIKE ?";

        return jdbcTemplate.query(sql, new CustomerRowMapper(), '%' + query + '%', '%' + query + '%');
    }

    public void insert(Customer customer) {
        String sql = "INSERT INTO customer(firstname, lastname) VALUES(?, ?)";

        jdbcTemplate.update(sql, customer.getFirstname(), customer.getLastname());
    }

    public void update(Customer customer) {
        String sql = "UPDATE customer set firstname = ?, lastname = ? WHERE id = ?";

        jdbcTemplate.update(sql, customer.getFirstname(), customer.getLastname(), customer.getId());
    }

    public void delete(Long customerId) {
        String sql = "DELETE FROM customer WHERE id = ?";

        jdbcTemplate.update(sql, customerId);
    }
}
