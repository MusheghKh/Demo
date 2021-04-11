package org.example.demo.repository;

import org.example.demo.dao.AddressDao;
import org.example.demo.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressRepository {

    private final AddressDao addressDao;

    @Autowired
    public AddressRepository(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public List<Address> findAll() {
        return addressDao.findAll();
    }

    public Address findOne(Long addressId) {
        return addressDao.findOne(addressId);
    }

    public List<Address> findCustomersAddresses(Long customerId) {
        return addressDao.findCustomerAddresses(customerId);
    }

    public void insert(Address address) {
        addressDao.insert(address);
    }

    public void update(Address address) {
        addressDao.update(address);
    }

    public void updateWithoutFile(Address address) {
        addressDao.updateWithoutFile(address);
    }

    public void delete(Long addressId) {
        addressDao.delete(addressId);
    }

    public void deleteCustomersAddresses(Long customerId) {
        addressDao.deleteCustomerAddresses(customerId);
    }
}
