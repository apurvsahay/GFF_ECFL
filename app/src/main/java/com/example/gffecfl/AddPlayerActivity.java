package com.example.gffecfl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AddPlayerActivity extends AppCompatActivity {

    AutoCompleteTextView countries, playerPosition;
    List<String> pos= new ArrayList<>();
    List<String> countryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);


    }

    @Override
    protected void onStart() {
        super.onStart();

        pos.add("Goalkeeper");
        pos.add("Defender");
        pos.add("Midfielder");
        pos.add("Forward");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference countryReference = reference.child("Countries");

        countryReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    countryList.add(dataSnapshot.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        countries = (AutoCompleteTextView) findViewById(R.id.addPlayerCountry);
        playerPosition = (AutoCompleteTextView) findViewById(R.id.addPlayerPosition);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, countryList);
        countries.setThreshold(0);
        countries.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, pos);
        playerPosition.setThreshold(0);
        playerPosition.setAdapter(arrayAdapter1);
    }
}