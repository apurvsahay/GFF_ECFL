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
import android.widget.TextView;
import android.widget.Toast;

import com.example.gffecfl.Objects.Players;
import com.example.gffecfl.Objects.SquadPlayers;
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

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sellingPrice = sellingPriceET.getText().toString();
                String team = teamTV.getText().toString();
                if(sellingPrice!=null && team!=null){
                    if(sellingPrice!="" && team!=""){
                        player.setSellingPrice(sellingPrice);
                        addSoldPlayerToTeamFirebase(team,player);
                    }
                }
            }
        });
    }

    private void updatePlayerFirebase(Players player) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Players");

        databaseReference.child(player.getName()).setValue(player).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(IndividualPlayerDetailActivity.this,"Player sold successfully",Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(IndividualPlayerDetailActivity.this,AdminActivity.class);
                    IndividualPlayerDetailActivity.this.startActivity(intent);
                }
                else {
                    Toast.makeText(IndividualPlayerDetailActivity.this,"Error selling player",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addSoldPlayerToTeamFirebase(String team, Players player) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Squads");

        SquadPlayers squadPlayer = new SquadPlayers(player.getName(),"No");

        databaseReference.child(team).child(player.getName()).setValue(squadPlayer).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if(task.isSuccessful()) {
                    updatePlayerFirebase(player);
                }
                else {
                    Toast.makeText(IndividualPlayerDetailActivity.this,"Error selling player",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}