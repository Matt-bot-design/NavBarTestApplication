package com.example.servicelocatorloginscreeen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Profilepage extends AppCompatActivity {

    Button btn_request;
    ImageView backarrow;
    TextView comProfusername, comProfname, comProfemail, comProfphone, comProfaddress, comProfid, comProfservicesoffered;
    DatabaseReference profreference, RequestRef;
    FirebaseAuth profAuth;
    String User, CURRENT_STATE, ProfessionalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage);

        profAuth = FirebaseAuth.getInstance();
        User = getIntent().getExtras().get("visit_company_id").toString();

        //ProfessionalId = profAuth.getCurrentUser().getUid();
        profreference = FirebaseDatabase.getInstance().getReference().child("Company");
        RequestRef = FirebaseDatabase.getInstance().getReference().child("Request");
        backarrow = findViewById(R.id.imageView5);


        //btn_request = findViewById(R.id.btn_request);
        //comProfusername = findViewById(R.id. profile_username);
        //comProfname = findViewById(R.id.profile_name);
        //comProfemail = findViewById(R.id.profile_email);
        //comProfaddress = findViewById(R.id.profile_address);
        //comProfphone = findViewById(R.id.profile_phone);
        //comProfid = findViewById(R.id.profile_id);
        //comProfservicesoffered = findViewById(R.id.profile_servicesoffered);

        //companyviewData();

        InitializeFields();

        profreference.child(User).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    String companyuser = snapshot.child("companyusern").getValue().toString();
                    String companyName = snapshot.child("companyname").getValue().toString();
                    String companyEmail = snapshot.child("companyemailaddress").getValue().toString();
                    String companyAddress = snapshot.child("companyphysicaladdress").getValue().toString();
                    String companyPhone = snapshot.child("companyphonenumber").getValue().toString();
                    String companyRegister = snapshot.child("companyregistrationnumber").getValue().toString();
                    String companyServices = snapshot.child("companyservices").getValue().toString();

                    comProfusername.setText(companyuser);
                    comProfname.setText(companyName);
                    comProfemail.setText(companyEmail);
                    comProfphone.setText(companyPhone);
                    comProfaddress.setText(companyAddress);
                    comProfid.setText(companyRegister);
                    comProfservicesoffered.setText(companyServices);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Profilepage.this, ProfilesList.class);
                startActivity(intent2);
            }
        });

        btn_request.setOnClickListener((view) -> {
            Intent intent3 = new Intent(Profilepage.this, RequestSummary.class);
            startActivity(intent3);
        });
    }

    private void InitializeFields() {

        comProfusername = findViewById(R.id. profile_username);
        comProfname = findViewById(R.id.profile_name);
        comProfemail = findViewById(R.id.profile_email);
        comProfaddress = findViewById(R.id.profile_address);
        comProfphone = findViewById(R.id.profile_phone);
        comProfid = findViewById(R.id.profile_id);
        comProfservicesoffered = findViewById(R.id.profile_servicesoffered);
        btn_request = findViewById(R.id.btn_request);

    }

}