package com.example.gffecfl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gffecfl.Objects.Rankings;
import com.example.gffecfl.R;

import java.util.ArrayList;
import java.util.List;

public class RankingsAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context context;
    List<Rankings> rankingsList = new ArrayList<>();

    public RankingsAdapter(Context context, List<Rankings> rankingsList) {
        this.context = context;
        this.rankingsList = rankingsList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return rankingsList.size();
    }

    @Override
    public Rankings getItem(int position) {
        return rankingsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view== null)
            view=inflater.inflate(R.layout.rankings_row,null);

        TextView teamNameTv , leaderNameTv , memberNameTv , pointsTv ;
        ImageView positionImage;

        teamNameTv = view.findViewById(R.id.teamNameRank);
        leaderNameTv = view.findViewById(R.id.leaderNameRank);
        memberNameTv = view.findViewById(R.id.memberNameRank);
        pointsTv = view.findViewById(R.id.statPoints);

        teamNameTv.setText(getItem(position).getTeamName());
        leaderNameTv.setText(getItem(position).getLeader());
        memberNameTv.setText(getItem(position).getMember());
        pointsTv.setText(Integer.toString(getItem(position).getPoints()));

        switch (position){
            case 0 :

        }

        return  view;
    }
}
