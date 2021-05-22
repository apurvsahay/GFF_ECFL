package com.example.gffecfl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gffecfl.R;

public class AdminListAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context context;
    String[] footballers;

    public AdminListAdapter(Context context, String[] footballers){
        this.context= context;
        this.footballers = footballers;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return footballers.length;
    }

    @Override
    public Object getItem(int position) {
        return footballers[position];
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
        footballerName.setText(footballers[position]);
        return view;
    }
}
