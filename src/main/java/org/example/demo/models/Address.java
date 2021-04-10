package org.example.demo.models;

public class Address {
    private Long id;
    private String address;
    private String documentName;
    private Long customerId;

    public Address(Long id, String address, String documentName, Long customerId) {
        this.id = id;
        this.address = address;
        this.documentName = documentName;
        this.customerId = customerId;
    }

    public Address(String address, String documentName, Long customerId) {
        this.address = address;
        this.documentName = documentName;
        this.customerId = customerId;
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
