package com.example.servicelocator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {

    Button callSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        callSignUp = findViewById(R.id.callSignUp);

        callSignUp.setOnClickListener((view) -> {
            Intent intent = new Intent(Dashboard.this,UserSignUp.class);
            startActivity(intent);
        } );

    }
}