package com.example.servicelocatorloginscreeen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CompanySignUp extends AppCompatActivity {

    Button btnCallComSignUp;
    Button login_btn;
    TextInputLayout comFullName, companyusername, companyemail, companyphone, companyaddress, companyID, companypassword, comservices, SignUpUsername, SignUpPassword;

    private FirebaseAuth emailAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_sign_up);

        emailAuth = FirebaseAuth.getInstance();

        btnCallComSignUp = findViewById(R.id.btnCallComSignUp);
        login_btn = findViewById(R.id.login_btn);
        comFullName = findViewById(R.id.com_pro_name);
        companyusername = findViewById(R.id.username);
        companyemail = findViewById(R.id.email);
        companyphone = findViewById(R.id.phonenumber);
        companyaddress = findViewById(R.id.address);
        companyID = findViewById(R.id.identification_registration_number);
        companypassword = findViewById(R.id.password);
        comservices = findViewById(R.id.services);
        //companyretypepassword = findViewById(R.id.com_repassword);
        SignUpPassword = findViewById(R.id.password);
        SignUpUsername = findViewById(R.id.username);
        

        login_btn.setOnClickListener((view) -> {
            Intent intent = new Intent(CompanySignUp.this, Login.class);
            startActivity(intent);
        });
    }

    private Boolean validateName() {
        String val = comFullName.getEditText().getText().toString();

        if (val.isEmpty()) {
            comFullName.setError("Field empty");
            return false;
        } else {
            comFullName.setError(null);
            comFullName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = companyusername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            companyusername.setError("Field empty");
            return false;
        } else if (val.length() >= 15) {
            companyusername.setError("Too Long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            companyusername.setError("No White Spaces");
            return false;
        } else {
            companyusername.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = companyemail.getEditText().getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            companyemail.setError("Field empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            companyemail.setError("Invalid email");
            return false;
        } else {
            companyemail.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNum() {
        String val = companyphone.getEditText().getText().toString();

        if (val.isEmpty()) {
            companyphone.setError("Field empty");
            return false;
        } else {
            companyphone.setError(null);
            return true;
        }
    }

    private Boolean validateAddress() {
        String val = companyaddress.getEditText().getText().toString();

        if (val.isEmpty()) {
            companyaddress.setError("Field empty");
            return false;
        } else {
            companyaddress.setError(null);
            return true;
        }
    }

    private Boolean validateID() {
        String val = companyID.getEditText().getText().toString();

        if (val.isEmpty()) {
            companyID.setError("Field empty");
            return false;
        } else {
            companyID.setError(null);
            return true;
        }
    }

    private Boolean validateServices() {
        String val = comservices.getEditText().getText().toString();

        if (val.isEmpty()) {
            comservices.setError("Field empty");
            return false;
        } else {
            comservices.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = companypassword.getEditText().getText().toString();

        if (val.isEmpty()) {
            companypassword.setError("Field empty");
            return false;
        } else {
            companypassword.setError(null);
            return true;
        }
    }

    private Boolean validateRetypePswrd () {
        EditText comRetypePswrd;

        comRetypePswrd = findViewById(R.id.com_repassword);

        String val = companypassword.getEditText().getText().toString();
        String val1 = comRetypePswrd.getText().toString();

        if (!val.equals(val1)) {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            comRetypePswrd.setError(null);
            return true;
        }
    }

    public void ComReg(View view) {

        String companyemailaddress = companyemail.getEditText().getText().toString();
        String companypasswword = companypassword.getEditText().getText().toString();

        if (!validateName() | !validateUsername() | !validateEmail() | !validatePhoneNum() | !validateAddress() | !validateID() | !validatePassword() | !validateServices() | !validateRetypePswrd()) {
            return;
        }
        else {
            emailAuth.createUserWithEmailAndPassword(companyemailaddress, companypasswword);
            isCompany();
        }


        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Company");

        String companyname = comFullName.getEditText().getText().toString();
        String companyusern = companyusername.getEditText().getText().toString();
        //String companyemailaddress = companyemail.getEditText().getText().toString();
        String companyphonenumber = companyphone.getEditText().getText().toString();
        String companyphysicaladdress = companyaddress.getEditText().getText().toString();
        String companyservices = comservices.getEditText().getText().toString();
        //String companypasswword = companypassword.getEditText().getText().toString();
        String companyregistrationnumber = companyID.getEditText().getText().toString();
        CompanyHandlerClass companyhandlerClass = new CompanyHandlerClass(companyname, companyusern, companyemailaddress, companyphonenumber, companyphysicaladdress, companypasswword, companyregistrationnumber, companyservices);
        reference.child(companyusern).setValue(companyhandlerClass);


        //Intent intent1 = new Intent(CompanySignUp.this,ComUpdateProfile.class);
        //startActivity(intent1);
    }
    private void isCompany() {

        String companyEnteredUsername = SignUpUsername.getEditText().getText().toString().trim();
        String companyEnteredPassword = SignUpPassword.getEditText().getText().toString().trim();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Company");

        Query checkUser = reference.orderByChild("companyusern").equalTo(companyEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {

                    SignUpUsername.setError(null);
                    SignUpUsername.setErrorEnabled(false);

                    String passwordFromDB = dataSnapshot.child(companyEnteredUsername).child("companypassword").getValue(String.class);

                    if(passwordFromDB.equals(companyEnteredPassword)) {

                        SignUpUsername.setError(null);
                        SignUpUsername.setErrorEnabled(false);

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
                    }
                    else {
                        SignUpPassword.setError("Wrong Password");
                        SignUpPassword.requestFocus();
                    }
                }
                else {
                    SignUpUsername.setError("No such user exist");
                    SignUpUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}