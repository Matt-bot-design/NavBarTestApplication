package com.example.servicelocatorloginscreeen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserSignUp extends AppCompatActivity {

    Button userSignUp_btn, btnlogin;
    TextInputLayout userName, user_name, userEmailAddress, userPhoneNum, userPAddress, userPassword, userIdentityNumber;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        userName = findViewById(R.id.fullname);
        user_name = findViewById(R.id.username);
        userEmailAddress = findViewById(R.id.email);
        userPhoneNum = findViewById(R.id.phonenumber);
        userPAddress = findViewById(R.id.address);
        userPassword = findViewById(R.id.password);
        userIdentityNumber = findViewById(R.id.identificationnumber);
        userSignUp_btn = findViewById(R.id.btnSignUpUser);
        btnlogin = findViewById(R.id.login_btn);

        btnlogin.setOnClickListener((view) -> {
                    Intent intent = new Intent(UserSignUp.this, Login.class);
                    startActivity(intent);

                });
       // userSignUp_btn.setOnClickListener(new View.OnClickListener() {
          //  @Override
          //  public void onClick(View view) {
              //  rootNode = FirebaseDatabase.getInstance();
               // reference = rootNode.getReference("users");

              //  String name = userName.getEditText().getText().toString();
              //  String usern = user_name.getEditText().getText().toString();
              //  String useremail = userEmailAddress.getEditText().getText().toString();
              //  String phone = userPhoneNum.getEditText().getText().toString();
               // String physicaladdress = userPAddress.getEditText().getText().toString();
               // String userpassword = userPassword.getEditText().getText().toString();
               // String idenNumber = userIdentityNumber.getEditText().getText().toString();

               // UserHandlerClass handlerClass = new UserHandlerClass(name, usern, useremail, phone, physicaladdress, userpassword, idenNumber);

                //reference.child(idenNumber).setValue(handlerClass);

           // }
       // });

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

    public void UserReg (View view) {

        if(!validateName() | !validateUsername() | !validateEmail() | !validatePhoneNum() | !validateAddress() | !validateID() | !validatePassword()) {
            return;
        }

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        String name = userName.getEditText().getText().toString();
        String usern = user_name.getEditText().getText().toString();
        String useremail = userEmailAddress.getEditText().getText().toString();
        String phone = userPhoneNum.getEditText().getText().toString();
        String physicaladdress = userPAddress.getEditText().getText().toString();
        String userpassword = userPassword.getEditText().getText().toString();
        String idenNumber = userIdentityNumber.getEditText().getText().toString();
        UserHandlerClass handlerClass = new UserHandlerClass(name, usern, useremail, phone, physicaladdress, userpassword, idenNumber);
        reference.child(usern).setValue(handlerClass);

        Intent intent = new Intent(UserSignUp.this,ProfilesList.class);
        startActivity(intent);
    }
}