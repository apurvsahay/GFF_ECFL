package com.example.gffecfl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gffecfl.Adapter.AdminListAdapter;
import com.example.gffecfl.Objects.Players;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SearchPlayerByCountry extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    Button search;
    ListView listView;
    List<String> countryList = new ArrayList<>();
    List<Players> playersList = new ArrayList<>();
    ImageView back;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_player_by_country);

        autoCompleteTextView= (AutoCompleteTextView) findViewById(R.id.searchCountryName);
        search = (Button) findViewById(R.id.searchCountryButton);
        listView = (ListView) findViewById(R.id.list);
        back = (ImageView) findViewById(R.id.backCountry);
        toolbar = (Toolbar) findViewById(R.id.toolbarCountry);
        setSupportActionBar(toolbar);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference countryReference = reference.child("Countries");

        countryReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                countryList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    countryList.add(dataSnapshot.getValue(String.class));
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter(SearchPlayerByCountry.this, android.R.layout.simple_spinner_dropdown_item, countryList);
                autoCompleteTextView.setThreshold(0);
                autoCompleteTextView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countryName=autoCompleteTextView.getText().toString().trim();
                getSupportActionBar().setTitle(countryName);
                if(countryList.contains(countryName)){
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference playersReference = reference.child("Players");

                    playersReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            playersList.clear();
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Players players = dataSnapshot.getValue(Players.class);
                                if(players.getCountry().equals(countryName))
                                    playersList.add(players);
                            }
                            AdminListAdapter adapter = new AdminListAdapter(SearchPlayerByCountry.this, playersList);
                            listView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                }
                else {
                    Toast.makeText(SearchPlayerByCountry.this,"Please enter correct country name",Toast.LENGTH_LONG).show();
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPlayerByCountry.this,AdminActivity.class);
                SearchPlayerByCountry.this.startActivity(intent);
            }
        });
    }
}