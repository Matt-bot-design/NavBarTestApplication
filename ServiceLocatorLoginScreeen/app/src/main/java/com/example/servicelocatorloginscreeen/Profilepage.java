package com.example.servicelocatorloginscreeen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Telephony;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profilepage extends AppCompatActivity {

    Button btn_request;
    ImageView backarrow;
    TextView comProfusername, comProfname, comProfemail, comProfphone, comProfaddress, comProfid, comProfservicesoffered;
    //EditText number, comProfphone;
    CircleImageView profileImage;
    DatabaseReference profreference, RequestRef;
    //FirebaseAuth profAuth;
    String User, message;
    Button request;
    final int SEND_SMS_PERMISSION_REQUEST_CODE =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage);

        //profAuth = FirebaseAuth.getInstance();
        User = getIntent().getExtras().get("visit_company_id").toString();

        //ProfessionalId = profAuth.getCurrentUser().getUid();
        profreference = FirebaseDatabase.getInstance().getReference().child("Company");
        RequestRef = FirebaseDatabase.getInstance().getReference().child("users").child("name");
        backarrow = findViewById(R.id.imageView5);


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

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        sendRequest();
                    }
                    else {
                        requestPermissions(new String[] {Manifest.permission.SEND_SMS}, 1);
                    }

                    Intent intent2 = new Intent(Profilepage.this, sendEmail.class);
                    startActivity(intent2);
                }
            }
        });
    }



    public void sendRequest () {
        String phoneNumber = comProfphone.getText().toString().trim();
        String mssg = message.trim();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, mssg, null, null);
            Toast.makeText(this, "Sms sent", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to send Request", Toast.LENGTH_SHORT).show();
        }

    }

    private void InitializeFields() {

        comProfusername = findViewById(R.id. profile_username);
        comProfname = findViewById(R.id.profile_name);
        comProfemail = findViewById(R.id.profile_email);
        comProfaddress = findViewById(R.id.profile_address);
        comProfphone = findViewById(R.id.profile_phone);
        comProfid = findViewById(R.id.profile_id);
        comProfservicesoffered = findViewById(R.id.profile_servicesoffered);
        //number = findViewById(R.id.MobileNo);
        btn_request = findViewById(R.id.btn_request);

        comProfemail.setText(Html.fromHtml("<a href=\"mailto:matthewdavids15.MD@gmail.com\"></a>"));
        comProfemail.setMovementMethod(LinkMovementMethod.getInstance());

        message = "Check email someone has requested your services";

        request = findViewById(R.id.btn_request);

    }
}