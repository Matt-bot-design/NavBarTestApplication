package com.example.navbartestapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity {

    private TextInputLayout comfullname, comusername, comphone, comaddress, comservices, comreg;
    private Button saveButton;
    private CircleImageView ProfileImage;
    private FirebaseAuth comAuth;
    //private ImageButton selectImage;
    private DatabaseReference comRef;
    //private StorageReference comProfileRef;
    String current_id;
    private ProgressDialog loading;

    //final static int Pick_image = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        loading = new ProgressDialog(this);

        comAuth = FirebaseAuth.getInstance();
        current_id = comAuth.getCurrentUser().getUid();
        comRef = FirebaseDatabase.getInstance().getReference().child("Company").child(current_id);
        //comProfileRef = FirebaseStorage.getInstance().getReference().child("Profile Images");

        comfullname = findViewById(R.id.companyUpdateName);
        comusername = findViewById(R.id.username);
        comreg = findViewById(R.id.companyUpdateRegistration);
        comphone = findViewById(R.id.companyUpdatePhone);
        comaddress = findViewById(R.id.companyUpdateAddress);
        comservices = findViewById(R.id.companyUpdateServices);

        saveButton = findViewById(R.id.btn_save);
        ProfileImage = findViewById(R.id.circleImageView);
        //selectImage = findViewById(R.id.ProfileimageButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveCompanyInfo();
            }
        });

    }

    private void saveCompanyInfo() {

        String name = comfullname.getEditText().getText().toString();
        String username = comusername.getEditText().getText().toString();
        String phone = comphone.getEditText().getText().toString();
        String address = comaddress.getEditText().getText().toString();
        String services = comservices.getEditText().getText().toString();
        String registration = comreg.getEditText().getText().toString();

        if (TextUtils.isEmpty(name)) {
            comfullname.setError("Enter name or company name");

        }
        if (TextUtils.isEmpty(username)) {
            comfullname.setError("Enter username");

        }
        if (TextUtils.isEmpty(phone)) {
            comfullname.setError("Enter phone number");

        }
        if (TextUtils.isEmpty(address)) {
            comfullname.setError("Enter address");

        }
        if (TextUtils.isEmpty(services)) {
            comfullname.setError("Enter Offered Services");

        }
        if (TextUtils.isEmpty(registration)) {
            comfullname.setError("Enter ID number");

        }
        else {
            loading.setTitle("Saving Info");
            loading.setMessage("Please wait...");
            loading.show();
            loading.setCanceledOnTouchOutside(true);

            HashMap userMap = new HashMap();
            userMap.put("username", username);
            userMap.put("name or company name", name);
            userMap.put("phone number", phone);
            userMap.put("address", address);
            userMap.put("services", services);
            userMap.put("registration or ID number", registration);

            comRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if (task.isSuccessful()) {
                        callMainAct();
                        Toast.makeText(SetupActivity.this, "Account Created Success", Toast.LENGTH_LONG).show();
                        loading.dismiss();
                    }
                    else {
                        String notify = task.getException().getMessage();
                        Toast.makeText(SetupActivity.this, "ERROR OCCURED " + notify, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                }
            });

        }
    }

    private void callMainAct() {
        Intent callMain = new Intent(SetupActivity.this, MainActivity.class);
        callMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(callMain);
        finish();
    }
}