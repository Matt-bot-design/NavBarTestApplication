package com.example.servicelocatorloginscreeen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button callSignUp;
    Button ComSignUp;
    Button btnLogin;
    TextInputLayout LoginUsername, LoginPassword;
    Button btnForgotPswrd;
    ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        callSignUp = findViewById(R.id.callSignUp);
        ComSignUp = findViewById(R.id.ComSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        LoginUsername = findViewById(R.id.username);
        LoginPassword = findViewById(R.id.password);
        btnForgotPswrd = findViewById(R.id.btnForgot);

        loading = new ProgressDialog(this);

        callSignUp.setOnClickListener((view) -> {
            Intent intent = new Intent(Login.this,UserSignUp.class);
            startActivity(intent);
            finish();
        } );

        ComSignUp.setOnClickListener((view) -> {
            Intent intent2 = new Intent(Login.this,CompanySignUp.class);
            startActivity(intent2);
            finish();
        } );

        btnForgotPswrd.setOnClickListener((view) -> {
            Intent intent3 = new Intent (Login.this, ResetPassword.class);
            startActivity(intent3);
            finish();
        } );


    }

    private Boolean validateUsername() {
        String val = LoginUsername.getEditText().getText().toString();

        if(val.isEmpty()) {
            LoginUsername.setError("Cannot be Empty");
            return false;
        } else {
            LoginUsername.setError(null);
            LoginUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = LoginPassword.getEditText().getText().toString();

        if (val.isEmpty()) {
            LoginPassword.setError("Cannot be empty");
            return false;
        } else {
            LoginPassword.setError(null);
            LoginPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void LoginUser(View view) {

        if (!validateUsername() | !validatePassword()) {
            return;
        }
        else {
            //loading.setTitle("Logging In");
            //loading.setMessage("Please wait");
            //loading.show();
            //loading.setCanceledOnTouchOutside(true);
            isCompany();
            isUser();

        }
    }

    private void isCompany() {

        String companyEnteredUsername = LoginUsername.getEditText().getText().toString().trim();
        String companyEnteredPassword = LoginPassword.getEditText().getText().toString().trim();

        DatabaseReference reference =  FirebaseDatabase.getInstance().getReference("Company");

        Query checkUser = reference.orderByChild("companyusern").equalTo(companyEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {

                    LoginUsername.setError(null);
                    LoginUsername.setErrorEnabled(false);

                    String passwordFromDB = dataSnapshot.child(companyEnteredUsername).child("companypassword").getValue(String.class);

                    if(passwordFromDB.equals(companyEnteredPassword)) {

                        LoginUsername.setError(null);
                        LoginUsername.setErrorEnabled(false);

                        String nameFromDB = dataSnapshot.child(companyEnteredUsername).child("companyname").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(companyEnteredUsername).child("companyusern").getValue(String.class);
                        String phoneFromDB = dataSnapshot.child(companyEnteredUsername).child("companyphonenumber").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(companyEnteredUsername).child("companyemailaddress").getValue(String.class);
                        String idNumFromDB = dataSnapshot.child(companyEnteredUsername).child("companyregistrationnumber").getValue(String.class);
                        String physicaladdressFromDB = dataSnapshot.child(companyEnteredUsername).child("companyphysicaladdress").getValue(String.class);
                        String servicesFromDB = dataSnapshot.child(companyEnteredUsername).child("companyservices").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),ComUpdateProfile.class);

                        intent.putExtra("companyname",nameFromDB);
                        intent.putExtra("companyusern",usernameFromDB);
                        intent.putExtra("companyregistrationnumber",idNumFromDB);
                        intent.putExtra("companyemailaddress",emailFromDB);
                        intent.putExtra("companyphysicaladdress",physicaladdressFromDB);
                        intent.putExtra("companyphonenumber",phoneFromDB);
                        intent.putExtra("companypassword",passwordFromDB);
                        intent.putExtra("companyservices",servicesFromDB);

                        startActivity(intent);
                        finish();
                    }
                    else {
                        LoginPassword.setError("Wrong Password");
                        LoginPassword.requestFocus();
                    }
                }
                else {
                    LoginUsername.setError("No such user exist");
                    LoginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void isUser() {

        String userEnteredUsername = LoginUsername.getEditText().getText().toString().trim();
        String userEnteredPassword = LoginPassword.getEditText().getText().toString().trim();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("usern").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {

                    LoginUsername.setError(null);
                    LoginUsername.setErrorEnabled(false);

                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("userpassword").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPassword)) {

                        LoginUsername.setError(null);
                        LoginUsername.setErrorEnabled(false);

                        String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("usern").getValue(String.class);
                        String phoneFromDB = dataSnapshot.child(userEnteredUsername).child("phone").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("useremail").getValue(String.class);
                        String idNumFromDB = dataSnapshot.child(userEnteredUsername).child("idenNumber").getValue(String.class);
                        String physicaladdressFromDB = dataSnapshot.child(userEnteredUsername).child("physicaladdress").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),Users_Profile_Activity.class);

                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("usern",usernameFromDB);
                        intent.putExtra("idenNumber",idNumFromDB);
                        intent.putExtra("useremail",emailFromDB);
                        intent.putExtra("physicaladdress",physicaladdressFromDB);
                        intent.putExtra("phone",phoneFromDB);
                        intent.putExtra("userpassword",passwordFromDB);

                        startActivity(intent);
                        finish();
                    }
                    else {
                        LoginPassword.setError("Wrong Password");
                        LoginPassword.requestFocus();
                    }
                }
                else {
                    LoginUsername.setError("No such user exist");
                    LoginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}