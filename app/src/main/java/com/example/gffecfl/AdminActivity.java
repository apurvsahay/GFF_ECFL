package com.example.gffecfl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.gffecfl.Adapter.AdminListAdapter;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(new AdminListAdapter(this,new String[]{"Ronaldo","Zlatan"}));
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
                return true;
            case R.id.optionEliminateCountry:
                return true;
            case R.id.optionSellPlayer:
                return true;
        }
        return false;
    }
}