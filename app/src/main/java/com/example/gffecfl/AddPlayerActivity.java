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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddPlayerActivity extends AppCompatActivity {

    AutoCompleteTextView countries, playerPosition;
    List<String> pos= new ArrayList<>();
//    String[] country = new String[24];
//    List<String> countryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        pos.add("Goalkeeper");
        pos.add("Defender");
        pos.add("Midfielder");
        pos.add("Forward");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Countries");
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    //countryList = setCountryNames((Map<String, String>) task.getResult().getValue());
                    //country = setCountryNamesArray((Map<String, String>) task.getResult().getValue());
                }
            }
        });

        countries = (AutoCompleteTextView) findViewById(R.id.addPlayerCountry);
        playerPosition = (AutoCompleteTextView) findViewById(R.id.addPlayerPosition);

//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, country);
//        countries.setThreshold(1);
//        countries.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, pos);
        playerPosition.setThreshold(0);
        playerPosition.setAdapter(arrayAdapter1);
    }

    private String[] setCountryNamesArray(Map<String, String> value) {
        String[] array= new String[24];
        int count=0;
        for (Map.Entry<String,String> entry : value.entrySet()){
            array[count] = entry.getValue();
            count++;
        }
        return array;
    }

    private List<String> setCountryNames(Map<String, String> value) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String,String> entry : value.entrySet()){
            String country = entry.getValue();
            list.add(country);
        }

        return list;
    }
}