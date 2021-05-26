package com.example.gffecfl.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import Fragments.SquadFragment;
import Fragments.StartingElevenFragment;

public class FragmentHomeAdapter extends FragmentStateAdapter {
    public FragmentHomeAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 :
                return new SquadFragment();
            case 1:
                return new StartingElevenFragment();
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
