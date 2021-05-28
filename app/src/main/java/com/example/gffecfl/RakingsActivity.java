package com.example.gffecfl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.google.firebase.database.core.utilities.Tree;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RakingsActivity extends AppCompatActivity {

    ListView listView;
    Map<String, Players> playersMap = new HashMap<>();
    Map<String,Integer> pointsMap = new HashMap<>();
    List<Rankings> rankingsList = new ArrayList<>();
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rakings);

        listView = findViewById(R.id.listRanks);
        back = findViewById(R.id.backRankings);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RakingsActivity.this,HomeActivity.class);
                RakingsActivity.this.startActivity(intent);
            }
        });

        calculateTeamPoints();

    }

    @Override
    protected void onStart() {
        super.onStart();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String teamName = ((TextView)view.findViewById(R.id.teamNameRank)).getText().toString();

                Intent intent = new Intent(RakingsActivity.this,TeamDetailsActivity.class);
                intent.putExtra("teamName",teamName);
                RakingsActivity.this.startActivity(intent);
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
                    int points = 0;
                    if(pointsMap.get(team.getName()) != null){
                        points = pointsMap.get(team.getName());
                    }

                    Rankings ranking = new Rankings(team.getName(),team.getLeader(),team.getMember(),points);
                    rankingsList.add(ranking);
                }

                rankingsList = getSortedRankingsList(rankingsList);
                RankingsAdapter adapter = new RankingsAdapter(RakingsActivity.this,rankingsList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private List<Rankings> getSortedRankingsList(List<Rankings> rankingsList) {
        List<Rankings> list = new ArrayList<>();
        Set<Integer> pointsSet = new HashSet<>();
        for(Rankings rankings :rankingsList){
            pointsSet.add(rankings.getPoints());
        }
        List<Integer> pointsList = new ArrayList<>(pointsSet);
        Collections.sort(pointsList,Collections.reverseOrder());

        for(int point : pointsList){
            for(Rankings ranking :rankingsList){
                if(ranking.getPoints() == point){
                    list.add(ranking);
                }
            }
        }

        return list;
    }
}