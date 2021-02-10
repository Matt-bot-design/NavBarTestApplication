package com.example.navbartestapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

   // Button callSignUp;
    Button ComSignUp;
    Button btnLogin;
    TextInputLayout LoginEmail, LoginPassword;
    Button btnForgotPswrd;
    private FirebaseAuth comAuth;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        comAuth = FirebaseAuth.getInstance();

        loading = new ProgressDialog(this);
        //callSignUp = findViewById(R.id.callSignUp);
        ComSignUp = findViewById(R.id.ComSignUp);
        LoginEmail = findViewById(R.id.email);
        LoginPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        btnForgotPswrd = findViewById(R.id.btnForgot);

        ComSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CallCompanyReg();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginCompany();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = comAuth.getCurrentUser();

        if (currentUser != null) {

            CallMainAct();
        }
    }

    private void LoginCompany() {

        String email = LoginEmail.getEditText().getText().toString().trim();
        String password = LoginPassword.getEditText().getText().toString().trim();

        if (email.isEmpty()) {
            LoginEmail.setError("Field empty");
        }
        else if (password.isEmpty()) {
            LoginPassword.setError("Field empty");
        }
        else {

            loading.setTitle("Logging In");
            loading.setMessage("Please wait");
            loading.show();
            loading.setCanceledOnTouchOutside(true);
            comAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        CallMainAct();

                        Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                    else {
                        String notify = task.getException().getMessage();
                        Toast.makeText(LoginActivity.this, "ERROR " + notify, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                }
            });
        }
    }

    private void CallMainAct() {

        Intent callmain = new Intent(LoginActivity.this, MainActivity.class);
        callmain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(callmain);
        finish();
    }

    private void CallCompanyReg() {

        Intent companyReg = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(companyReg);
        finish();
    }
}