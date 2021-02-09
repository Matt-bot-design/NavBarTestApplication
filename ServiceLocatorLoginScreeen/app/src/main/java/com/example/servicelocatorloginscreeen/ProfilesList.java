package com.example.servicelocatorloginscreeen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfilesList extends AppCompatActivity {

    private ImageButton SearchButton;
    private EditText SearchInput;
    private RecyclerView SearchList;
    private DatabaseReference companiesReference;
    private ImageView userprofileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles_list);

        companiesReference = FirebaseDatabase.getInstance().getReference().child("Company");


        SearchList = findViewById(R.id.recyclerView);
        SearchList.setHasFixedSize(true);
        SearchList.setLayoutManager(new LinearLayoutManager(this));

        SearchInput = findViewById(R.id.searchText);
        userprofileImage = findViewById(R.id.imageView4);

        SearchButton = findViewById(R.id.imageView2);

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SearchTextBox = SearchInput.getText().toString();
                SearchPeople(SearchTextBox);
            }
        });

        userprofileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ProfilesList.this, Users_Profile_Activity.class);
                startActivity(intent2);
            }
        });


    }

    private void SearchPeople(String searchTextBox) {

       // int position;

        Toast.makeText(this, "Searching", Toast.LENGTH_LONG).show();

        Query searchCompaniesQuery = companiesReference.orderByChild("companyservices").startAt(searchTextBox).endAt(searchTextBox + "\uf8ff");

        FirebaseRecyclerAdapter<CompanyHandlerClass, FindCompanyViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CompanyHandlerClass, FindCompanyViewHolder>(CompanyHandlerClass.class, R.layout.all_companies_design_layout, FindCompanyViewHolder.class, searchCompaniesQuery) {
            @Override
            protected void populateViewHolder(FindCompanyViewHolder findCompanyViewHolder, CompanyHandlerClass findCompany, int i) {

                findCompanyViewHolder.setCompanyname(findCompany.getCompanyname());
                findCompanyViewHolder.setCompanyservices(findCompany.getCompanyservices());

                findCompanyViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = 0;
                        String visit_company_id = getRef(position).getKey();

                        Intent profieIntent = new Intent(ProfilesList.this, Profilepage.class);
                        profieIntent.putExtra("visit_company_id", visit_company_id);
                        startActivity(profieIntent);
                    }
                });

            }
        };
        SearchList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class FindCompanyViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public FindCompanyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setCompanyname(String companyname) {

            TextView myName = (TextView) mView.findViewById(R.id.profilefullname);
            myName.setText(companyname);
        }

        public void setCompanyservices(String companyservices) {

            TextView myServices = (TextView) mView.findViewById(R.id.profileservicesinfo);
            myServices.setText(companyservices);
        }
    }
}