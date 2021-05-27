package com.example.gffecfl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gffecfl.Adapter.RankingsAdapter;
import com.example.gffecfl.Objects.Players;
import com.example.gffecfl.Objects.Rankings;
import com.example.gffecfl.Objects.SquadPlayers;
import com.example.gffecfl.Objects.Teams;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RakingsActivity extends AppCompatActivity {

    ListView listView;
    Map<String, Players> playersMap = new HashMap<>();
    Map<String,Integer> pointsMap = new HashMap<>();
    List<Rankings> rankingsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rakings);

        listView = findViewById(R.id.listRanks);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference playersReference = reference.child("Players");

        playersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Players player = dataSnapshot.getValue(Players.class);
                    playersMap.put(player.getName(),player);
                }
                calculateTeamPoints();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    private void calculateTeamPoints() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference();
        DatabaseReference squadReference = reference1.child("Squads");

        squadReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    int points = 0;
                    for (DataSnapshot playerSnapshot : dataSnapshot.getChildren()){
                        SquadPlayers player = playerSnapshot.getValue(SquadPlayers.class);
                        int points1 = Integer.parseInt(player.getPoints1());
                        int points2 = Integer.parseInt(player.getPoints2());
                        int points3 = Integer.parseInt(player.getPoints3());
                        points = points + points1 +points2 + points3;
                    }
                    pointsMap.put(dataSnapshot.getKey(),points);
                }
                getTeamDetails();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void getTeamDetails() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference();
        DatabaseReference teamsReference = reference1.child("Teams");

        teamsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                rankingsList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Teams team = dataSnapshot.getValue(Teams.class);
                    int points = pointsMap.get(team.getName());

                    Rankings ranking = new Rankings(team.getName(),team.getLeader(),team.getMember(),points);
                    rankingsList.add(ranking);
                }

                RankingsAdapter adapter = new RankingsAdapter(RakingsActivity.this,rankingsList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}