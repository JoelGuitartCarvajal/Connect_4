package com.example.connect_4.UTILS;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int mida;
    private Board board;
    private boolean controlTemps;
    private String alias;
    private ImageView fotoTorn;

    public ImageAdapter (Context c, int size, Board board){
        this.mContext = c;
        this.mida = size;
        this.board=board;
    }
    @Override
    public int getCount() {
        return mida * mida;
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
