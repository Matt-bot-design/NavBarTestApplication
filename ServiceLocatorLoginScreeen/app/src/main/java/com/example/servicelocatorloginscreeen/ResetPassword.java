package com.example.servicelocatorloginscreeen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    private Button resetPswrdLink;
    private EditText resetEmailInput;
    private FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        myAuth = FirebaseAuth.getInstance();

        resetPswrdLink = findViewById(R.id.btnResetPsswrdLink);
        resetEmailInput = findViewById(R.id.resetEmail);

        resetPswrdLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = resetEmailInput.getText().toString();
                String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (userEmail.isEmpty()) {
                    resetEmailInput.setError("Field empty");
                } else if(!userEmail.matches(emailPattern)) {
                    resetEmailInput.setError("Invalid email");
                }
                else {
                    myAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ResetPassword.this, "Please check your Email...", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ResetPassword.this, Login.class));
                            } else {
                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(ResetPassword.this, "Error Occured:" + errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }
}