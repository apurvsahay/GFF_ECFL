package com.example.gffecfl.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import Fragments.SquadFragment;
import Fragments.StartingElevenFragment;

public class FragmentTeamDetailsAdapter extends FragmentStateAdapter {

    String teamName;

    public FragmentTeamDetailsAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle , String teamName) {
        super(fragmentManager, lifecycle);
        this.teamName = teamName;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {

        Bundle bundle = new Bundle();
        bundle.putString("teamName",teamName);
        switch (position){
            case 0 :
                SquadFragment fragment = new SquadFragment();
                fragment.setArguments(bundle);
                return fragment;
            case 1:
                StartingElevenFragment startingElevenFragment = new StartingElevenFragment();
                startingElevenFragment.setArguments(bundle);
                return startingElevenFragment;
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
