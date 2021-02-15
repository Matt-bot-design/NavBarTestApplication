package com.example.servicelocatorloginscreeen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserSignUp extends AppCompatActivity {

    Button userSignUp_btn, btnlogin;
    TextInputLayout userName, user_name, userEmailAddress, userPhoneNum, userPAddress, userPassword, userIdentityNumber, SignUpUsername, SignUpPassword;
    ProgressDialog loading;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        loading = new ProgressDialog(this);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        userName = findViewById(R.id.fullname);
        user_name = findViewById(R.id.username);
        userEmailAddress = findViewById(R.id.email);
        userPhoneNum = findViewById(R.id.phonenumber);
        userPAddress = findViewById(R.id.address);
        userPassword = findViewById(R.id.password);
        userIdentityNumber = findViewById(R.id.identificationnumber);
        userSignUp_btn = findViewById(R.id.btnSignUpUser);
        btnlogin = findViewById(R.id.login_btn);
        SignUpPassword = findViewById(R.id.password);
        SignUpUsername = findViewById(R.id.username);

        btnlogin.setOnClickListener((view) -> {
                    Intent intent = new Intent(UserSignUp.this, Login.class);
                    startActivity(intent);

                });

        userSignUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserReg();
             }
        });

    }

    private Boolean validateName() {
        String val = userName.getEditText().getText().toString();

        if (val.isEmpty()) {
            userName.setError("Field empty");
            return false;
        }
        else{
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = user_name.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            user_name.setError("Field empty");
            return false;
        } else if(val.length()>=15){
            user_name.setError("Too Long");
            return false;
        }
        else if(!val.matches(noWhiteSpace)){
            user_name.setError("No White Spaces");
            return false;
        }
        else{
            user_name.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = userEmailAddress.getEditText().getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            userEmailAddress.setError("Field empty");
            return false;
        } else if(!val.matches(emailPattern)) {
            userEmailAddress.setError("Invalid email");
            return false;
        }

        else{
            userEmailAddress.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNum() {
        String val = userPhoneNum.getEditText().getText().toString();

        if (val.isEmpty()) {
            userPhoneNum.setError("Field empty");
            return false;
        }
        else{
            userPhoneNum.setError(null);
            return true;
        }
    }

    private Boolean validateAddress() {
        String val = userPAddress.getEditText().getText().toString();

        if (val.isEmpty()) {
            userPAddress.setError("Field empty");
            return false;
        }
        else{
            userPAddress.setError(null);
            return true;
        }
    }

    private Boolean validateID() {
        String val = userIdentityNumber.getEditText().getText().toString();

        if (val.isEmpty()) {
            userIdentityNumber.setError("Field empty");
            return false;
        }
        else{
            userIdentityNumber.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = userPassword.getEditText().getText().toString();

        if (val.isEmpty()) {
            userPassword.setError("Field empty");
            return false;
        }
        else{
            userPassword.setError(null);
            return true;
        }
    }

    private Boolean validateRetypePswrd () {
        EditText userRetypePswrd;

        userRetypePswrd = findViewById(R.id.user_repassword);

        String val = userPassword.getEditText().getText().toString();
        String val1 = userRetypePswrd.getText().toString();

        if (!val.equals(val1)) {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            userRetypePswrd.setError(null);
            return true;
        }
    }

    public void UserReg () {

        if(!validateName() | !validateUsername() | !validateEmail() | !validatePhoneNum() | !validateAddress() | !validateID() | !validatePassword() | !validateRetypePswrd()) {
            return;
        }

        loading.setTitle("Creating ACcount");
        loading.setMessage("please wait...");
        loading.show();
        loading.setCanceledOnTouchOutside(true);
        isUser();

        String name = userName.getEditText().getText().toString();
        String usern = user_name.getEditText().getText().toString();
        String useremail = userEmailAddress.getEditText().getText().toString();
        String phone = userPhoneNum.getEditText().getText().toString();
        String physicaladdress = userPAddress.getEditText().getText().toString();
        String userpassword = userPassword.getEditText().getText().toString();
        String idenNumber = userIdentityNumber.getEditText().getText().toString();
        UserHandlerClass handlerClass = new UserHandlerClass(name, usern, useremail, phone, physicaladdress, userpassword, idenNumber);
        reference.child(usern).setValue(handlerClass);

        Intent intent = new Intent(UserSignUp.this,Users_Profile_Activity.class);
        startActivity(intent);
        finish();
    }

    private void isUser() {

        String userEnteredUsername = SignUpUsername.getEditText().getText().toString().trim();
        String userEnteredPassword = SignUpPassword.getEditText().getText().toString().trim();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("usern").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {

                    SignUpUsername.setError(null);
                    SignUpUsername.setErrorEnabled(false);

                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("userpassword").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPassword)) {

                        SignUpUsername.setError(null);
                        SignUpUsername.setErrorEnabled(false);

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