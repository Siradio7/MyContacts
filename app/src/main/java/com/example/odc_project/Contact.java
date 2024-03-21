package com.example.odc_project;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Contact {
    private String name;
    private String phone;
    private String email;
    private String address;
    private boolean isFavorite;
    private String addedDate;

    public Contact() {

    }

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        this.addedDate = dateFormat.format(calendar.getTime());
    }

    public Contact(String name, String phone, String email, String address, boolean isFavorite) {
        this(name, phone, email);
        this.address = address;
        this.isFavorite = isFavorite;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsFavorite(boolean status) {
        isFavorite = status;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }
}
