package com.example.servicelocatorloginscreeen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class sendEmail extends AppCompatActivity {

    Button emailsent;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        emailsent = findViewById(R.id.send);
        loading = new ProgressDialog(this);

        emailsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setTitle("Sending Email");
                loading.setMessage("Please wait...");
                loading.show();
                loading.setCanceledOnTouchOutside(true);

                Intent sendemail = new Intent(sendEmail.this, ProfilesList.class);
                startActivity(sendemail);
                finish();
                Toast.makeText(sendEmail.this, "Email Request sent", Toast.LENGTH_SHORT).show();
            }
        });
    }
}