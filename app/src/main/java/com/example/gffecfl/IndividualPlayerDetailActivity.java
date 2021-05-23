package com.example.gffecfl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gffecfl.Objects.Players;

public class IndividualPlayerDetailActivity extends AppCompatActivity {

    Players player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_player_detail);

        player = (Players) getIntent().getSerializableExtra("Player");
    }
}