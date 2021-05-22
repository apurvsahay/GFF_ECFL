package com.example.gffecfl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

public class AddPlayerActivity extends AppCompatActivity {

    AutoCompleteTextView countries,playerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        String[] country = { "India", "USA", "China", "Japan", "Other"};
        String[] position = { "Goalkeeper" , "Defender" , "Midfielder" , "Forward"};

        countries = (AutoCompleteTextView) findViewById(R.id.addPlayerCountry);
        playerPosition = (AutoCompleteTextView) findViewById(R.id.addPlayerPosition);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,country);
        countries.setThreshold(1);
        countries.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,position);
        playerPosition.setThreshold(0);
        playerPosition.setAdapter(arrayAdapter1);
    }
}