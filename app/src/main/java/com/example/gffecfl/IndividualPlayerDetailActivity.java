package com.example.gffecfl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gffecfl.Objects.Players;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class IndividualPlayerDetailActivity extends AppCompatActivity {

    Players player;
    TextView individualPlayerName;
    EditText sellingPriceET,pointsET;
    AutoCompleteTextView teamTV;
    Button sellButton,updatePointsButton;
    List<String> teamsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_player_detail);

        player = (Players) getIntent().getSerializableExtra("Player");

        individualPlayerName=(TextView) findViewById(R.id.individualPlayerName);
        sellingPriceET=(EditText) findViewById(R.id.individualPlayerSellingPrice);
        pointsET=(EditText) findViewById(R.id.individualPlayerPoints);
        teamTV=(AutoCompleteTextView) findViewById(R.id.soldToTeamName);
        sellButton=(Button) findViewById(R.id.sellPlayerButton);
        updatePointsButton=(Button) findViewById(R.id.updatePointsButton);

        individualPlayerName.setText(player.getName());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference teamsReference = reference.child("Teams");

        teamsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    teamsList.add(dataSnapshot.child("Name").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, teamsList);
        teamTV.setThreshold(0);
        teamTV.setAdapter(arrayAdapter);
    }
}