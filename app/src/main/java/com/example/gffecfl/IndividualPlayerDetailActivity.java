package com.example.gffecfl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gffecfl.Objects.Players;

public class IndividualPlayerDetailActivity extends AppCompatActivity {

    Players player;
    TextView individualPlayerName;
    EditText sellingPriceET,pointsET;
    AutoCompleteTextView teamTV;
    Button sellButton,updatePointsButton;

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
    }
}