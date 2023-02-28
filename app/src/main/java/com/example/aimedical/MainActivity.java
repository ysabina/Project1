package com.example.aimedical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private FirebaseAuth mAuth;
    Doctor_profile doctorProfile = new Doctor_profile();
    Doctor_Calendar doctorCalendar=new Doctor_Calendar();
    PatientsFragment patientsFragment=new PatientsFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null){
            bottomNavigationView=findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch(item.getItemId()) {
                        case R.id.profile_doctor:
                            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, doctorProfile).commit();
                            return true;
                        case R.id.calendarDoctor:
                            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,doctorCalendar).commit();
                            return true;
                        case R.id.listPatients:
                            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,patientsFragment).commit();
                            return true;
                    }
                    return false;
                }
            });
        } else {
            Intent intent =new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }




}


