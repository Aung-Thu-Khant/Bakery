package com.example.butterbloom;

public class UserDataClass {
    private String Name;
    private String Email;
    private String PhoneNumber;
    private String Address;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public UserDataClass(String name, String email, String phoneNumber, String address) {
        this.Name = name;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.Address = address;
    }

    public UserDataClass(){

    }
}
