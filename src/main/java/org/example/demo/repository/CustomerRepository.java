package org.example.demo.repository;

import org.example.demo.dao.CustomerDao;
import org.example.demo.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerRepository(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    public Customer findOne(Long customerId) {
        return customerDao.findOne(customerId);
    }

    public List<Customer> findByFirstnameAndLastname(String query) {
        return customerDao.findByFirstNameAndLastName(query);
    }

    public void insert(Customer customer) {
        customerDao.insert(customer);
    }

    public void update(Customer customer) {
        customerDao.update(customer);
    }

    public void delete(Long customerId) {
        customerDao.delete(customerId);
    }
}
