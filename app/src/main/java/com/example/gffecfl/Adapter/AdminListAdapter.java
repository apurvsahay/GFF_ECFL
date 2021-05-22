package com.example.gffecfl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gffecfl.Objects.Players;
import com.example.gffecfl.R;

import java.util.ArrayList;
import java.util.List;

public class AdminListAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context context;
    List<Players> playersList = new ArrayList<>();

    public AdminListAdapter(Context context, List<Players> players){
        this.context= context;
        this.playersList=players;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return playersList.size();
    }

    @Override
    public Players getItem(int position) {
        return playersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view== null)
            view=inflater.inflate(R.layout.list_row,null);
        TextView footballerName = view.findViewById(R.id.footballerName);
        TextView footballerCountry = view.findViewById(R.id.footballerCountry);
        TextView footballerPosition = view.findViewById(R.id.footballerPosition);
        TextView footballerPrice = view.findViewById(R.id.footballerPrice);

        footballerName.setText(getItem(position).getName());
        footballerCountry.setText(getItem(position).getCountry());
        footballerPosition.setText(" / "+getItem(position).getPosition());
        footballerPrice.setText(getItem(position).getBasePrice()+" million");
        return view;
    }
}
