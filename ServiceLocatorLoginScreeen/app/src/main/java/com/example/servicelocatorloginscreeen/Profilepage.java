package com.example.servicelocatorloginscreeen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Profilepage extends AppCompatActivity {

    Button btn_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage);

        btn_request = findViewById(R.id.btn_request);

        btn_request.setOnClickListener((view) -> {
            Intent intent3 = new Intent(Profilepage.this, RequestSummary.class);
            startActivity(intent3);
        });
    }
}