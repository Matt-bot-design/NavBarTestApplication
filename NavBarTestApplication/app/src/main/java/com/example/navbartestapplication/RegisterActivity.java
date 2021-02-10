package com.example.navbartestapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private Button backToLogin;
    private TextInputLayout comEmail, comPassword, comConfirmPswrd;
    private Button RegisterCom;
    private FirebaseAuth mAuth;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        backToLogin = findViewById(R.id.alreadylogin_btn);
        RegisterCom = findViewById(R.id.btnCallComSignUp);
        comEmail = findViewById(R.id.email);
        comPassword = findViewById(R.id.password);
        comConfirmPswrd = findViewById(R.id.repassword);
        loading = new ProgressDialog(this);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent alreadyLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(alreadyLogin);
                finish();
            }
        });

        RegisterCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SignUpCompany();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {

            CallMainAct();
        }
    }

    private void CallMainAct() {

        Intent callmain = new Intent(RegisterActivity.this, MainActivity.class);
        callmain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(callmain);
        finish();
    }

    private void SignUpCompany() {

        String email = comEmail.getEditText().getText().toString();
        String password = comPassword.getEditText().getText().toString();
        String confirmpassword = comConfirmPswrd.getEditText().getText().toString();

        if (TextUtils.isEmpty(email)) {
            comEmail.setError("Field empty");
        }
        else if (TextUtils.isEmpty(password)) {
            comPassword.setError("Field empty");
        }
        else if (TextUtils.isEmpty(confirmpassword)) {
            comConfirmPswrd.setError("Field empty");
        }
        else if (!password.equals(confirmpassword)) {
            comConfirmPswrd.setError("Password does not match");
        }
        else {

            loading.setTitle("Creating Account");
            loading.setMessage("Please wait...");
            loading.show();
            loading.setCanceledOnTouchOutside(true);

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        CallSetupActivity();

                        Toast.makeText(RegisterActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                    else {
                        String notify = task.getException().getMessage();
                        Toast.makeText(RegisterActivity.this, "Error" + notify, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }

                }
            });
        }
    }

    private void CallSetupActivity() {

        Intent callsetup = new Intent(RegisterActivity.this, SetupActivity.class);
        callsetup.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(callsetup);
        finish();
    }

}