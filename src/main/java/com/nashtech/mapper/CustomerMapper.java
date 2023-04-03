package com.nashtech.mapper;

import com.nashtech.models.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {
    public Customer mapRow(ResultSet resultSet, int numRow) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setCustomerName(resultSet.getString("name"));
        customer.setCustomerAddress(resultSet.getString("address"));
        customer.setCustomerEmail(resultSet.getString("email"));
        customer.setCustomerPhone(resultSet.getString("phone"));
        return customer;
    }
}
