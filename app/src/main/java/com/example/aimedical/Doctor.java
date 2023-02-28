package com.example.aimedical;

import java.util.ArrayList;

public class Doctor {
    private String Name, Email, Phone, Specialty,Password;
    private ArrayList<Patient> Patients;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public ArrayList getPatients() {
        return Patients;
    }

    public String getPhone() {
        return Phone;
    }

    public String getSpecialty() {
        return Specialty;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPatients(ArrayList patients) {
        Patients = patients;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setSpecialty(String specialty) {
        Specialty = specialty;
    }
    public Doctor(String name, String phone, String email,String password){
        this.Email=email;
        this.Name=name;
        this.Phone=phone;
        this.Password=password;
    }
}
