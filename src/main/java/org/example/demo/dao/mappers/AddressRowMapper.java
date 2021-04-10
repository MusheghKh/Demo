package org.example.demo.dao.mappers;

import org.example.demo.models.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address> {
    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Address(
                rs.getLong("id"),
                rs.getString("address_text"),
                rs.getString("document_name"),
                rs.getLong("customer_id")
        );
    }
}
