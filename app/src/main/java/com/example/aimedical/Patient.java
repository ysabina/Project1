package com.example.aimedical;

import java.util.ArrayList;

public class Patient {
    private String name,phone,email;
    private ArrayList<Doctor> Doctors;

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Doctor> getDoctors() {
        return Doctors;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        Doctors = doctors;
    }
    public Patient(String name, String email, String phone){
        this.name=name;
        this.email=email;
        this.phone=phone;
    }

}
