package com.example.servicelocatorloginscreeen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Users_Profile_Activity extends AppCompatActivity {

     DatabaseReference userProfileReference;
     TextInputLayout userProfname, userProfemail, userProfphone, userProfaddress, userProfid, userProfpassword;
     TextView userProfusername;
     Button btn_update;
     ImageView backarrow;

    private String User_usern, User_Name, User_Email, User_Address, User_Phone, User_Register, User_Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users__profile_);

        userProfileReference = FirebaseDatabase.getInstance().getReference("users");

        userProfusername = findViewById(R.id.userName_Field);
        userProfname = findViewById(R.id.userUpdateName);
        userProfemail = findViewById(R.id.userUpdateEmail);
        userProfaddress = findViewById(R.id.userUpdateAddress);
        userProfphone = findViewById(R.id.userUpdatePhone);
        userProfid = findViewById(R.id.userUpdateRegistration);
        userProfpassword = findViewById(R.id.userUpdatePassword);
        btn_update = findViewById(R.id.btn_save);
        backarrow = findViewById(R.id.imageView5);

        UserData();

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Users_Profile_Activity.this, ProfilesList.class);
                startActivity(intent2);
            }
        });

    }

    public void saveData(View view) {

        if (isNameChanged() || isEmailChanged() || isAddressChanged() || isPhoneChanged() || isRegisterNumChanged() || isPasswordChanged()) {
            Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Data not saved", Toast.LENGTH_SHORT).show();
        }

    }

    private void UserData() {
        Intent intent = getIntent();
        User_usern = intent.getStringExtra("usern");
        User_Name = intent.getStringExtra("name");
        User_Email = intent.getStringExtra("useremail");
        User_Address = intent.getStringExtra("physicaladdress");
        User_Phone = intent.getStringExtra("phone");
        User_Register = intent.getStringExtra("idenNumber");
        User_Password = intent.getStringExtra("userpassword");

        userProfusername.setText(User_usern);
        userProfname.getEditText().setText(User_Name);
        userProfemail.getEditText().setText(User_Email);
        userProfphone.getEditText().setText(User_Phone);
        userProfaddress.getEditText().setText(User_Address);
        userProfid.getEditText().setText(User_Register);
        userProfpassword.getEditText().setText(User_Password);

    }

    private boolean isNameChanged() {

        if (!User_Name.equals(userProfname.getEditText().getText().toString())) {

            userProfileReference.child(User_usern).child("name").setValue(userProfname.getEditText().getText().toString());
            User_Name = userProfname.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }

    private boolean isEmailChanged() {

        if (!User_Email.equals(userProfemail.getEditText().getText().toString())) {

            userProfileReference.child(User_usern).child("name").setValue(userProfemail.getEditText().getText().toString());
            User_Email = userProfemail.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }

    private boolean isAddressChanged() {

        if (!User_Address.equals(userProfaddress.getEditText().getText().toString())) {

            userProfileReference.child(User_usern).child("name").setValue(userProfaddress.getEditText().getText().toString());
            User_Address = userProfaddress.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }

    private boolean isPhoneChanged() {

        if (!User_Phone.equals(userProfphone.getEditText().getText().toString())) {

            userProfileReference.child(User_usern).child("name").setValue(userProfphone.getEditText().getText().toString());
            User_Phone = userProfphone.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }

    private boolean isRegisterNumChanged() {

        if (!User_Register.equals(userProfid.getEditText().getText().toString())) {

            userProfileReference.child(User_usern).child("name").setValue(userProfid.getEditText().getText().toString());
            User_Register = userProfid.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }

    private boolean isPasswordChanged() {

        if (!User_Password.equals(userProfpassword.getEditText().getText().toString())) {

            userProfileReference.child(User_usern).child("name").setValue(userProfpassword.getEditText().getText().toString());
            User_Password = userProfpassword.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }
}