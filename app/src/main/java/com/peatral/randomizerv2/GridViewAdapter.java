package com.peatral.randomizerv2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {
    ArrayList<Track> lstSource;
    private LayoutInflater layoutInflater;
    Context mContext;

    public GridViewAdapter(ArrayList<Track> lstSource, Context mContext) {
        this.lstSource = lstSource;
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return lstSource.size();
    }

    @Override
    public Object getItem(int i) {
        return lstSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            view = layoutInflater.inflate(R.layout.grid_item_layout, null);
            holder = new ViewHolder();
            holder.name = view.findViewById(R.id.textView_name);
            holder.cup = view.findViewById(R.id.imageView_cup);


            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        Track track = this.lstSource.get(i);
        holder.name.setText(Utils.getStringResByID("track_" + track.getID(), mContext));
        int imageId = Utils.getMipmapResIdByName("cup_" + track.getCup(), mContext);

        holder.cup.setImageResource(imageId);

        return view;
    }


    static class ViewHolder {
        TextView name;
        ImageView cup;

    }
}
