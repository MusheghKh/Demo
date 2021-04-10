package org.example.demo.dao;

import org.example.demo.dao.mappers.AddressRowMapper;
import org.example.demo.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AddressDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Address> findAll() {
        String sql = "SELECT * FROM address";

        return jdbcTemplate.query(sql, new AddressRowMapper());
    }

    public Address findOne(Long id) {
        String sql = "SELECT * FROM address WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new AddressRowMapper(), id);
    }

    public List<Address> findCustomerAddresses(Long customerId) {
        String sql = "SELECT * FROM address WHERE customer_id = ?";

        return jdbcTemplate.query(sql, new AddressRowMapper(), customerId);
    }

    public void insert(Address address) {
        String sql = "INSERT INTO address(address_text, document_name, customer_id) VALUES(?, ?, ?)";

        jdbcTemplate.update(sql, address.getAddress(), address.getDocumentName(), address.getCustomerId());
    }

    public void update(Address address) {
        String sql = "UPDATE address set address_text = ?, document_name = ? WHERE id = ?";

        jdbcTemplate.update(sql, address.getAddress(), address.getDocumentName(), address.getId());
    }

    public void updateWithoutFile(Address address) {
        String sql = "UPDATE address set address_text = ? WHERE id = ?";

        jdbcTemplate.update(sql, address.getAddress(), address.getId());
    }

    public void delete(Long addressId) {
        String sql = "DELETE FROM address WHERE id = ?";

        jdbcTemplate.update(sql, addressId);
    }
}
