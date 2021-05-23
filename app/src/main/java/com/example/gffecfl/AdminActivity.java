package com.example.gffecfl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gffecfl.Adapter.AdminListAdapter;
import com.example.gffecfl.Objects.Players;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ListView listView;
    ImageView searchButton;
    EditText searchEditText;
    TextInputLayout search;
    List<Players> playersList = new ArrayList<>();
    List<Players> allPlayersList = new ArrayList<>();
    AdminListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        searchButton = (ImageView) findViewById(R.id.imageViewSearch);
        searchEditText = (EditText) findViewById(R.id.searchPlayer);
        search = (TextInputLayout) findViewById(R.id.search);

        listView = (ListView)findViewById(R.id.list);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference playersReference = reference.child("Players");

        playersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Players players = dataSnapshot.getValue(Players.class);
                    playersList.add(players);
                    allPlayersList.add(players);
                }
                adapter = new AdminListAdapter(AdminActivity.this,playersList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(search.getVisibility() == View.VISIBLE){
                    search.setVisibility(View.GONE);
                    searchEditText.setText("");
                }
                else {
                    search.setVisibility(View.VISIBLE);
                }
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(s)){
                    adapter.filter("",allPlayersList);
                    listView.clearTextFilter();
                }
                else {
                    adapter.filter(s.toString(),allPlayersList);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Players player = new Players();
                player.setName(((TextView) view.findViewById(R.id.footballerName)).getText().toString());
                player.setCountry(((TextView) view.findViewById(R.id.footballerCountry)).getText().toString());
                player.setPosition(((TextView) view.findViewById(R.id.footballerPosition)).getText().toString());
                player.setBasePrice(((TextView) view.findViewById(R.id.footballerPrice)).getText().toString());

                Intent intent = new Intent(AdminActivity.this,IndividualPlayerDetailActivity.class);
                intent.putExtra("Player", player);
                AdminActivity.this.startActivity(intent);
            }
        });
    }

    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(this,view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.options_admin);
        popup.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.optionAddPlayer:
                Intent intent = new Intent(AdminActivity.this,AddPlayerActivity.class);
                AdminActivity.this.startActivity(intent);
                return true;
            case R.id.optionEliminateCountry:
                return true;
        }
        return false;
    }
}