package com.example.aimedical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth; // importing Firebase
    Button login_btn,signup_btn;
    Spinner eType;
    private FirebaseFirestore db;
    EditText eEmail,ePassword; // variables that are given the value of whatever the user will input
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);// variables that are given the value of whatever the user will input
        eEmail=(EditText) findViewById(R.id.editTextTextEmailAddress);
        ePassword=(EditText) findViewById(R.id.editTextTextPassword);
        db = FirebaseFirestore.getInstance();
        eType=(Spinner)findViewById(R.id.spinner2);
        String[] arraySpinner = new String[] {
                "Doctor","Patient","Admin"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eType.setAdapter(adapter);
        login_btn=(Button) findViewById(R.id.login_btn); //assigning to the variables the data that user enters
        signup_btn=(Button) findViewById(R.id.Signup_btn);
        mAuth=FirebaseAuth.getInstance(); //importing Firebase functions into the program
        login_btn.setOnClickListener(new View.OnClickListener() { //setting an on-click listener for the login button
            @Override
            public void onClick(View view) {
                String uEmail=eEmail.getText().toString().trim();
                String uPassword=ePassword.getText().toString().trim();
                String uType=eType.getSelectedItem().toString().trim();
                if(uEmail.isEmpty() || uPassword.isEmpty() || eType.getSelectedItem()==null){
                    eEmail.setError("Please enter all of the information in a valid format!");
                }
                else{
                    switch(uType){
                        case "Doctor":
                            db.collection("Doctors")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if(task.isSuccessful()){
                                                for(QueryDocumentSnapshot doc : task.getResult()){
                                                    String a=doc.getString("Email");
                                                    String b=doc.getString("Password");
                                                    if(a.equalsIgnoreCase(uEmail) & b.equalsIgnoreCase(uPassword)) {
                                                        Intent home = new Intent(LoginActivity.this, MainActivity.class);
                                                        startActivity(home);
                                                        Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                                                        break;
                                                    }else
                                                        Toast.makeText(LoginActivity.this, "Cannot login,incorrect Email and Password", Toast.LENGTH_SHORT).show();

                                                }

                                            }
                                        }
                                    });
                        case "Patient":
                            db.collection("Patients")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if(task.isSuccessful()){
                                                for(QueryDocumentSnapshot doc : task.getResult()){
                                                    String a=doc.getString("Email");
                                                    String b=doc.getString("Password");
                                                    if(a.equalsIgnoreCase(uEmail) & b.equalsIgnoreCase(uPassword)) {
                                                        Intent home = new Intent(LoginActivity.this, MainActivity.class);
                                                        startActivity(home);
                                                        Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                                                        break;
                                                    }else
                                                        Toast.makeText(LoginActivity.this, "Cannot login,incorrect Email and Password", Toast.LENGTH_SHORT).show();

                                                }

                                            }
                                        }
                                    });



                    }

                }
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}