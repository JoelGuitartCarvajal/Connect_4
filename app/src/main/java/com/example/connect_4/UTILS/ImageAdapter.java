package com.example.connect_4.UTILS;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int size;
    public ImageAdapter (Context c, int size){
        this.mContext = c;
        this.size = size;
    }
    @Override
    public int getCount() {
        return size*size;
    }
    @Override
    public Object getItem(int position){
        return null;
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return null;
    }
}
