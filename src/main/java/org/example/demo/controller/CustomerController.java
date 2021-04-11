package org.example.demo.controller;

import org.example.demo.models.Customer;
import org.example.demo.repository.AddressRepository;
import org.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {
        return customerRepository.findOne(id);
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        customerRepository.insert(customer);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public List<Customer> searchForCustomer(@RequestParam(name = "query", value = "query", required = false, defaultValue = "") String query) {
        return customerRepository.findByFirstnameAndLastname(query);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        Customer customerFromDb = customerRepository.findOne(id);
        if (customerFromDb == null) {
            return ResponseEntity.badRequest().build();
        }

        customer.setId(id);
        customerRepository.update(customer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        addressRepository.deleteCustomersAddresses(id);
        customerRepository.delete(id);
        return ResponseEntity.ok().build();
    }
}
