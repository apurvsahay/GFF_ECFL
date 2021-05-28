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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    EditText sellingPriceET,pointsET1,pointsET2,pointsET3;
    AutoCompleteTextView teamTV;
    Button sellButton,updatePointsButton,updateStatusButton;
    List<String> teamsList = new ArrayList<>();
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_player_detail);

        individualPlayerName=(TextView) findViewById(R.id.individualPlayerName);
        sellingPriceET=(EditText) findViewById(R.id.individualPlayerSellingPrice);
        pointsET1=(EditText) findViewById(R.id.individualPlayerPoints1);
        pointsET2=(EditText) findViewById(R.id.individualPlayerPoints2);
        pointsET3=(EditText) findViewById(R.id.individualPlayerPoints3);
        teamTV=(AutoCompleteTextView) findViewById(R.id.soldToTeamName);
        sellButton=(Button) findViewById(R.id.sellPlayerButton);
        updatePointsButton=(Button) findViewById(R.id.updatePointsButton);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        updateStatusButton = (Button) findViewById(R.id.updateStatusButton);
    }

    @Override
    protected void onStart() {
        super.onStart();

        player = (Players) getIntent().getSerializableExtra("Player");

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
                String sellingPrice = sellingPriceET.getText().toString().trim();
                String team = teamTV.getText().toString().trim();
                if(player.getSoldTo().equals("NA")) {
                    if (!sellingPrice.equals("") && !team.equals("")) {
                        player.setSellingPrice(sellingPrice);
                        player.setSoldTo(team);
                        addSoldPlayerToTeamFirebase(team, player);
                    } else {
                        Toast.makeText(IndividualPlayerDetailActivity.this, "Please insert values in all the fields", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(IndividualPlayerDetailActivity.this, "Player already sold", Toast.LENGTH_LONG).show();
                }
            }
        });

        updatePointsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String points1,points2,points3;
                points1=pointsET1.getText().toString().trim();
                points2=pointsET2.getText().toString().trim();
                points3=pointsET3.getText().toString().trim();

                if(!points1.equals("") && !points2.equals("") && !points3.equals("")){
                    updatePointsFirebase(points1,points2,points3,player);
                }
                else {
                    Toast.makeText(IndividualPlayerDetailActivity.this,"Please insert values into all fields",Toast.LENGTH_LONG).show();
                }

            }
        });

        updateStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(selectedId);
                String status = radioButton.getText().toString();

                updateStatusFirebase(status);
            }
        });
    }

    private void updateStatusFirebase(String status) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference squadReference = firebaseDatabase.getReference("Squads");

        if(!player.getSoldTo().equals("NA")){
            squadReference.child(player.getSoldTo()).child(player.getName()).child("inStartingEleven").setValue(status);
            Toast.makeText(IndividualPlayerDetailActivity.this,"Status Updated",Toast.LENGTH_LONG).show();
            Intent intent =new Intent(IndividualPlayerDetailActivity.this,AdminActivity.class);
            IndividualPlayerDetailActivity.this.startActivity(intent);
        }
        else {
            Toast.makeText(IndividualPlayerDetailActivity.this,"Please sell player first",Toast.LENGTH_LONG).show();
        }
    }

    private void updatePointsFirebase(String points1, String points2, String points3, Players player) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Squads");
        DatabaseReference databaseReference1 = firebaseDatabase.getReference("Players");

        if(player.getSellingPrice().equals("NA")){
            Toast.makeText(IndividualPlayerDetailActivity.this,"Please sell player first",Toast.LENGTH_LONG).show();
            return;
        }

        int p1,p2,p3,totalPoints;
        p1 = Integer.parseInt(points1);
        p2 = Integer.parseInt(points2);
        p3 = Integer.parseInt(points3);
        totalPoints = p1+p2+p3;
        String playerPoints = Integer.toString(totalPoints);
        databaseReference1.child(player.getName()).child("points").setValue(playerPoints);

        databaseReference.child(player.getSoldTo()).child(player.getName()).child("points1").setValue(points1);
        databaseReference.child(player.getSoldTo()).child(player.getName()).child("points2").setValue(points2);
        databaseReference.child(player.getSoldTo()).child(player.getName()).child("points3").setValue(points3).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(IndividualPlayerDetailActivity.this,"Points updated successfully",Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(IndividualPlayerDetailActivity.this,AdminActivity.class);
                    IndividualPlayerDetailActivity.this.startActivity(intent);
                }
                else {
                    Toast.makeText(IndividualPlayerDetailActivity.this,"Error occurred",Toast.LENGTH_LONG).show();
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