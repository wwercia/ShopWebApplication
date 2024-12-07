package com.werka.shopwebapplication.domain.api;

public class ClientAddressDataInfo {

    private int id;
    private int clientId;
    private String phoneNumber;
    private String address;
    private String town;
    private String postcode;

    public ClientAddressDataInfo(int id, int clientId, String phoneNumber, String address, String town, String postcode) {
        this.id = id;
        this.clientId = clientId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.town = town;
        this.postcode = postcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


}
