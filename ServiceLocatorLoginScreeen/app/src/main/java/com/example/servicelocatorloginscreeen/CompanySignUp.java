package com.example.servicelocatorloginscreeen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CompanySignUp extends AppCompatActivity {

    Button btnCallComSignUp;
    Button login_btn;
    TextInputLayout comFullName, companyusername, companyemail, companyphone, companyaddress, companyID, companypassword;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_sign_up);

        btnCallComSignUp = findViewById(R.id.btnCallComSignUp);
        login_btn = findViewById(R.id.login_btn);
        comFullName = findViewById(R.id.com_pro_name);
        companyusername = findViewById(R.id.username);
        companyemail = findViewById(R.id.email);
        companyphone = findViewById(R.id.phonenumber);
        companyaddress = findViewById(R.id.address);
        companyID = findViewById(R.id.identification_registration_number);
        companypassword = findViewById(R.id.password);

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

    public void ComReg(View view) {

        if (!validateName() | !validateUsername() | !validateEmail() | !validatePhoneNum() | !validateAddress() | !validateID() | !validatePassword()) {
            return;
        }

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Company");

        String name = comFullName.getEditText().getText().toString();
        String usern = companyusername.getEditText().getText().toString();
        String useremail = companyemail.getEditText().getText().toString();
        String phone = companyphone.getEditText().getText().toString();
        String physicaladdress = companyaddress.getEditText().getText().toString();
        String userpassword = companypassword.getEditText().getText().toString();
        String idenNumber = companyID.getEditText().getText().toString();
        UserHandlerClass handlerClass = new UserHandlerClass(name, usern, useremail, phone, physicaladdress, userpassword, idenNumber);
        reference.child(idenNumber).setValue(handlerClass);
    }
}