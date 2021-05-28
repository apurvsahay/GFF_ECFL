package com.example.gffecfl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.gffecfl.Adapter.FragmentHomeAdapter;
import com.example.gffecfl.Adapter.FragmentTeamDetailsAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

//This activity is called on clicking any team card from the rankings activity
public class TeamDetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    String teamName;

    TabLayout tabLayout ;
    ViewPager2 viewPager2;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        teamName = getIntent().getStringExtra("teamName");
        back= findViewById(R.id.backTeamDetails);

        tabLayout = (TabLayout) findViewById(R.id.tabLayoutHome);
        viewPager2 = (ViewPager2) findViewById(R.id.viewPagerHome);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTeamDetailsAdapter fragmentTeamDetailsAdapter = new FragmentTeamDetailsAdapter(fragmentManager , getLifecycle(), teamName);
        viewPager2.setAdapter(fragmentTeamDetailsAdapter);

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

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(teamName);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamDetailsActivity.this,RakingsActivity.class);
                TeamDetailsActivity.this.startActivity(intent);
            }
        });
    }
}