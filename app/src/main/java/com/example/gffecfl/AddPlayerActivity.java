package com.example.gffecfl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gffecfl.Objects.Players;
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
    EditText playerName;
    Button addPlayerButton;
    List<String> pos= new ArrayList<>();
    List<String> countryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        countries = (AutoCompleteTextView) findViewById(R.id.addPlayerCountry);
        playerPosition = (AutoCompleteTextView) findViewById(R.id.addPlayerPosition);
        playerName = (EditText) findViewById(R.id.addPlayerName);
        addPlayerButton = (Button) findViewById(R.id.addPlayerButton);

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

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, countryList);
        countries.setThreshold(0);
        countries.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, pos);
        playerPosition.setThreshold(0);
        playerPosition.setAdapter(arrayAdapter1);
    }

    @Override
    protected void onStart() {
        super.onStart();

        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = playerName.getText().toString();
                String country = countries.getText().toString();
                String position = playerPosition.getText().toString();

                if(name!=null && country!=null && position!=null){
                    if(name!="" && country!="" && position!=""){
                        addPlayerToFirebase(name,country,position);
                        Intent intent =new Intent(AddPlayerActivity.this,AdminActivity.class);
                        AddPlayerActivity.this.startActivity(intent);
                    }
                    else {
                        Toast.makeText(AddPlayerActivity.this,"Please insert values for all the fields.",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void addPlayerToFirebase(String name, String country, String position) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Players");

        String price="NA";
        if(position.equals("Goalkeeper") || position.equals("Defender"))
            price="4.5";
        else if(position.equals("Midfielder"))
            price="5";
        else if(position.equals("Forward"))
            price="6";

        Players player = new Players(name,country,position,price,"NA","0","NA");
        databaseReference.child(name).setValue(player).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(AddPlayerActivity.this,"Player added successfully",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(AddPlayerActivity.this,"Error adding player",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}