package com.example.servicelocatorloginscreeen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComUpdateProfile extends AppCompatActivity {

    TextInputLayout comfullname, comemail, comphone, comaddress, comregnumber, comofferedservices, compassword;
    TextView companyusername;
    ImageView backarrow;

    private String companyuser, companyName, companyEmail, companyAddress, companyPhone, companyRegister, companyServices, companyPassword;

    DatabaseReference companyreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_update_profile);

        companyreference = FirebaseDatabase.getInstance().getReference("Company");

        comfullname = findViewById(R.id.companyUpdateName);
        comemail = findViewById(R.id.companyUpdateEmail);
        comphone = findViewById(R.id.companyUpdatePhone);
        comaddress = findViewById(R.id.companyUpdateAddress);
        comregnumber = findViewById(R.id.companyUpdateRegistration);
        companyusername = findViewById(R.id.CompanyName_Field);
        comofferedservices = findViewById(R.id.companyUpdateServices);
        compassword = findViewById(R.id.companyUpdatePassword);
        backarrow = findViewById(R.id.imageView5);

        companyData();

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ComUpdateProfile.this, ProfilesList.class);
                startActivity(intent2);
            }
        });

    }

    private void companyData() {
        Intent intent = getIntent();
        companyuser = intent.getStringExtra("companyusern");
        companyName = intent.getStringExtra("companyname");
        companyEmail = intent.getStringExtra("companyemailaddress");
        companyAddress = intent.getStringExtra("companyphysicaladdress");
        companyPhone = intent.getStringExtra("companyphonenumber");
        companyRegister = intent.getStringExtra("companyregistrationnumber");
        companyPassword = intent.getStringExtra("companypassword");
        companyServices = intent.getStringExtra("companyservices");

        companyusername.setText(companyuser);
        comfullname.getEditText().setText(companyName);
        comemail.getEditText().setText(companyEmail);
        comphone.getEditText().setText(companyPhone);
        comaddress.getEditText().setText(companyAddress);
        comregnumber.getEditText().setText(companyRegister);
        compassword.getEditText().setText(companyPassword);
        comofferedservices.getEditText().setText(companyServices);



    }

    public void saveData(View view) {

        if (isNameChanged() || isServicesChanged() || isEmailChanged() || isAddressChanged() || isPhoneChanged() || isRegisterNumChanged() || isPasswordChanged()) {
            Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Data not saved", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isServicesChanged() {

        if (!companyServices.equals(comofferedservices.getEditText().getText().toString())) {

            companyreference.child(companyuser).child("companyservices").setValue(comofferedservices.getEditText().getText().toString());
            companyServices = comofferedservices.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isNameChanged() {

        if (!companyName.equals(comfullname.getEditText().getText().toString())) {

            companyreference.child(companyuser).child("companyname").setValue(comfullname.getEditText().getText().toString());
            companyName = comfullname.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }

    private boolean isEmailChanged() {

        if (!companyEmail.equals(comemail.getEditText().getText().toString())) {

            companyreference.child(companyuser).child("companyemailaddress").setValue(comemail.getEditText().getText().toString());
            companyEmail = comemail.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }

    private boolean isAddressChanged() {

        if (!companyAddress.equals(comaddress.getEditText().getText().toString())) {

            companyreference.child(companyuser).child("companyphysicaladdress").setValue(comaddress.getEditText().getText().toString());
            companyAddress = comaddress.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }

    private boolean isPhoneChanged() {

        if (!companyPhone.equals(comphone.getEditText().getText().toString())) {

            companyreference.child(companyuser).child("companyphonenumber").setValue(comphone.getEditText().getText().toString());
            companyPhone = comphone.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }

    private boolean isRegisterNumChanged() {

        if (!companyRegister.equals(comregnumber.getEditText().getText().toString())) {

            companyreference.child(companyuser).child("companyregistrationnumber").setValue(comregnumber.getEditText().getText().toString());
            companyRegister = comregnumber.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }

    private boolean isPasswordChanged() {

        if (!companyPassword.equals(compassword.getEditText().getText().toString())) {

            companyreference.child(companyuser).child("companypassword").setValue(compassword.getEditText().getText().toString());
            companyPassword = compassword.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }


}