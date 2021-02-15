package com.example.navbartestapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private RecyclerView profilesList;
    private Toolbar myToolbar;
    private ActionBarDrawerToggle actionBarToggle;
    private DatabaseReference CompanyRef;

    private FirebaseAuth mAuth;
    String current_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        current_id = mAuth.getCurrentUser().getUid();
        CompanyRef = FirebaseDatabase.getInstance().getReference().child("Company");

        myToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Home");

        drawerLayout = findViewById(R.id.drawable_layout);
        actionBarToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarToggle);
        actionBarToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navView = findViewById(R.id.navigation_view);

        View navigation = navView.inflateHeaderView(R.layout.nav_header);



        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                MenuSelector(item);
                return false;
            }
        });
    }



    @Override
    protected void onStart() {

        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {

            SendUserToLogin();
        }
        else {

            CheckComExist();
        }
    }

    private void CheckComExist() {

        final String current_id = mAuth.getCurrentUser().getUid();

        CompanyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (!snapshot.hasChild(current_id)) {

                    SendCompanyToSetup();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void SendCompanyToSetup() {
        Intent setupintent = new Intent(MainActivity.this, SetupActivity.class);
        setupintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(setupintent);
        finish();
    }

    private void SendUserToLogin() {

        Intent loginintent = new Intent(MainActivity.this, LoginActivity.class);
        loginintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginintent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarToggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void MenuSelector(MenuItem item) {
        switch (item.getItemId())   {

            case R.id.nav_home:
                // place code in here
                break;

            case R.id.nav_profile:
                // place code in here
                //SendCompanyToSetup();
                break;

            case R.id.nav_settings:
                // place code in here
                break;

            case R.id.nav_search:
                // place code in here
                break;

            case R.id.nav_logout:
                mAuth.signOut();
                SendUserToLogin();
                // place code in here
                break;
        }
    }

}