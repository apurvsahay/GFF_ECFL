package com.example.gffecfl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.gffecfl.Adapter.FragmentHomeAdapter;
import com.example.gffecfl.Adapter.TeamListAdapter;
import com.example.gffecfl.Objects.Players;
import com.example.gffecfl.Objects.SquadPlayers;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class HomeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    Toolbar toolbar;
    String teamName;

    TabLayout tabLayout ;
    ViewPager2 viewPager2;
    FragmentHomeAdapter fragmentHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = (TabLayout) findViewById(R.id.tabLayoutHome);
        viewPager2 = (ViewPager2) findViewById(R.id.viewPagerHome);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentHomeAdapter = new FragmentHomeAdapter(fragmentManager , getLifecycle());
        viewPager2.setAdapter(fragmentHomeAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("Squad"));
        tabLayout.addTab(tabLayout.newTab().setText("Starting XI"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DatabaseReference referenceTeam = FirebaseDatabase.getInstance().getReference();
        DatabaseReference teamReference = referenceTeam.child("Teams").child(uid).child("Name");

        teamReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                teamName = snapshot.getValue(String.class);
                getSupportActionBar().setTitle(teamName);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
    }

    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(this,view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.options_main);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.optionRank:
                Intent intentRank = new Intent(HomeActivity.this,RakingsActivity.class);
                HomeActivity.this.startActivity(intentRank);
                return true;
            case R.id.option1:
                Intent intent = new Intent(HomeActivity.this,AdminActivity.class);
                HomeActivity.this.startActivity(intent);
                return true;
            case R.id.option2:
                Intent intent1 = new Intent(HomeActivity.this,MainActivity.class);
                FirebaseAuth.getInstance().signOut();
                HomeActivity.this.startActivity(intent1);
                return true;
        }
        return false;
    }
}