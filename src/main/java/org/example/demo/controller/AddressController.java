package org.example.demo.controller;

import org.apache.commons.io.FilenameUtils;
import org.example.demo.models.Address;
import org.example.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable("id") Long id) {
        return addressRepository.findOne(id);
    }

    @GetMapping("/customerAddresses")
    public List<Address> getCustomerAddresses(@RequestParam("customerId") Long customerId) {
        return addressRepository.findCustomersAddresses(customerId);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> addAddress(
            @RequestParam("file") MultipartFile file,
            @RequestParam("address") String address,
            @RequestParam("customerId") Long customerId
    ) {
        if (file == null) {
            return ResponseEntity.badRequest().build();
        }

        String newFilename = UUID.randomUUID().toString() + '.' + FilenameUtils.getExtension(file.getOriginalFilename());
        Address newAddress = new Address(address, newFilename, customerId);

        ControllerUtils.storeFile(file, newFilename);

        addressRepository.insert(newAddress);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> editAddress(
            @PathVariable("id") Long id,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("address") String address
    ) {
        Address newAddress = new Address();
        newAddress.setAddress(address);
        newAddress.setId(id);

        if (file != null) {
            Address addressInDb = addressRepository.findOne(id);
            if (addressInDb == null) {
                return ResponseEntity.badRequest().build();
            }

            ControllerUtils.deleteFile(addressInDb.getDocumentName());

            String newFilename = UUID.randomUUID().toString() + '.' + FilenameUtils.getExtension(file.getOriginalFilename());;
            newAddress.setDocumentName(newFilename);

            ControllerUtils.storeFile(file, newFilename);

            addressRepository.update(newAddress);
            return ResponseEntity.ok().build();
        }

        addressRepository.updateWithoutFile(newAddress);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") Long id) {

        Address addressInDB = addressRepository.findOne(id);
        if (addressInDB == null) {
            return ResponseEntity.badRequest().build();
        }

        ControllerUtils.deleteFile(addressInDB.getDocumentName());

        addressRepository.delete(id);
        return ResponseEntity.ok().build();
    }
}
