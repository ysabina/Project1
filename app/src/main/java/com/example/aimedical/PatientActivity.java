package com.example.aimedical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class PatientActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private FirebaseAuth mAuth;
    DoctorsFragment doctorsFragment=new DoctorsFragment();
    PatientCalendar patientCalendar=new PatientCalendar();
    PatientProfile patientProfile=new PatientProfile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        mAuth= FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()==null){

        }
    }
}