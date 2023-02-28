package com.example.aimedical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText eUserName,eEmail,ePassword,eNumber,eConfirmPassword;
    Button btnOK;
    ImageButton showpassword1,showpassword2;
    Spinner choose;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        showpassword1=(ImageButton) findViewById(R.id.imageButton);
        showpassword2=(ImageButton) findViewById(R.id.imageButton2);
        db = FirebaseFirestore.getInstance();
        eUserName=(EditText) findViewById(R.id.editTextTextPersonName);//taking data from the interface units/xml attributes
        eEmail=(EditText) findViewById(R.id.editTextTextEmailAddress2);
        ePassword=(EditText) findViewById(R.id.editTextTextPassword2);
        eConfirmPassword=(EditText) findViewById(R.id.editTextTextPassword3);
        btnOK=(Button) findViewById(R.id.button);
        choose=(Spinner) findViewById(R.id.spinner);
        String[] arraySpinner = new String[] {
                "Doctor","Patient","Admin"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choose.setAdapter(adapter);
        eNumber=(EditText) findViewById(R.id.editTextPhone);
        btnOK.setOnClickListener(new View.OnClickListener() {//setting an on click listener for the ok button that is supposed to redirect the user
            @Override                                           //to the main page
            public void onClick(View view) {
                RegisterUser();
            }
        });
        showpassword1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (ePassword.getTransformationMethod() instanceof PasswordTransformationMethod) {
                    // Show the password
                    ePassword.setTransformationMethod(null);
            }
        }});
        showpassword2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (eConfirmPassword.getTransformationMethod() instanceof PasswordTransformationMethod) {
                    // Show the password
                    eConfirmPassword.setTransformationMethod(null);
                }
            }});
    }
    private void RegisterUser(){
        String uname=eUserName.getText().toString().trim();//assigning the variables the data that user enters in those interface units
        String uemail=eEmail.getText().toString().trim();
        String upassword=ePassword.getText().toString().trim();
        String unumber=eNumber.getText().toString().trim();
        String utype=choose.getSelectedItem().toString().trim();
        String uconfirm=eConfirmPassword.getText().toString().trim();
        if(uname.isEmpty() || uemail.isEmpty()|| upassword.isEmpty() || unumber.isEmpty() ){ //check for null error
            eUserName.setError("Enter all information");
            eUserName.requestFocus();
            return;
        }
        if(uemail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(uemail).matches()) { //checking for validity/validation
            eEmail.setError("Enter your email in a correct format!");
        }
        if(!isValidPassword(upassword)) {
            ePassword.setError("Your password should contain both uppercase and lowercase letters, at least one symbol and minimum 8 characters");
        }
        if(isValidPassword(upassword) && !uemail.isEmpty() && !uname.isEmpty() && !unumber.isEmpty() && !upassword.isEmpty() && !uconfirm.isEmpty()){
            if(utype=="Doctor"){
                CollectionReference dbDoctors=db.collection("Doctors");
                Doctor doctor=new Doctor(uname,unumber,uemail,upassword);
                dbDoctors.add(doctor).addOnSuccessListener(new OnSuccessListener<DocumentReference>(){
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegisterActivity.this, "You have successfully registered!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                }
            });
            }
            else if(utype=="Patient"){
                CollectionReference dbPatients=db.collection("Patients");
                Patient patient=new Patient(uname,unumber,uemail);
                dbPatients.add(patient).addOnSuccessListener(new OnSuccessListener<DocumentReference>(){
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegisterActivity.this, "You have successfully registered!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegisterActivity.this,PatientActivity.class);
                        startActivity(intent);
                    }
                });
            }}


    }



    public static boolean isValidPassword(String upassword) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(upassword);
        return matcher.matches();
    }}
