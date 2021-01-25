package com.example.servicelocatorloginscreeen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfilesList extends AppCompatActivity {

    TextView TextView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles_list);

        TextView1 = (TextView)findViewById(R.id.TextView1);
        TextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ProfilesList.this,Profilepage.class);
                startActivity(intent2);
            }
        });
    }
}