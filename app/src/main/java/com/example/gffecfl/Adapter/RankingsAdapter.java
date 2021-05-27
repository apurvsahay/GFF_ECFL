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
        positionImage = view.findViewById(R.id.rankingsImage);

        teamNameTv.setText(getItem(position).getTeamName());
        leaderNameTv.setText(getItem(position).getLeader());
        memberNameTv.setText(getItem(position).getMember());
        pointsTv.setText(Integer.toString(getItem(position).getPoints()));

        switch (position){
            case 0 :
                positionImage.setImageResource(R.drawable.one);
                break;
            case 1 :
                positionImage.setImageResource(R.drawable.two);
                break;
            case 2 :
                positionImage.setImageResource(R.drawable.three);
                break;
            case 3 :
                positionImage.setImageResource(R.drawable.four);
                break;
            case 4 :
                positionImage.setImageResource(R.drawable.five);
                break;
            case 5 :
                positionImage.setImageResource(R.drawable.six);
                break;
            case 6 :
                positionImage.setImageResource(R.drawable.seven);
                break;
            case 7 :
                positionImage.setImageResource(R.drawable.eight);
                break;
            case 8 :
                positionImage.setImageResource(R.drawable.nine);
                break;
            case 9 :
                positionImage.setImageResource(R.drawable.ten);
                break;
            case 10 :
                positionImage.setImageResource(R.drawable.eleven);
                break;
            case 11 :
                positionImage.setImageResource(R.drawable.twelve);
                break;
            case 12 :
                positionImage.setImageResource(R.drawable.thirteen);
                break;
            case 13 :
                positionImage.setImageResource(R.drawable.forteen);
                break;
        }

        return  view;
    }
}
