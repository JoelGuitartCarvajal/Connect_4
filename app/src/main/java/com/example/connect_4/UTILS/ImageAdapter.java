package com.example.connect_4.UTILS;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connect_4.R;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int mida;
    private Board board;
    private boolean controlTemps;
    private String alias;
    private ImageView fotoTorn;
    private TextView tempsRestant;

    public ImageAdapter(Context mContext, int mida, Board board, boolean controlTemps, String alias, ImageView fotoTorn, TextView tempsRestant) {
        this.mContext = mContext;
        this.mida = mida;
        this.board = board;
        this.controlTemps = controlTemps;
        this.alias = alias;
        this.fotoTorn = fotoTorn;
        this.tempsRestant = tempsRestant;
    }


    @Override
    public int getCount() {
        return mida * mida;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button btn;
        if (convertView == null) {
            btn = new Button(mContext);
        } else {
            btn = (Button) convertView;
        }
        int x = position / mida;
        int y = position % mida;
        Tuple tupla = new Tuple(x, y);
        btn.setBackgroundResource(setBackground(tupla));
        btn.setOnClickListener(new MyOnClickListener(tupla, mContext));
        btn.setId(position);
        return btn;
    }

    private int setBackground(Tuple positions) {
        if (board.getUserPositions().contains(positions)) {
            return R.drawable.red_piece;
        }
        if (board.getCPUPositions().contains(positions)) {
            return R.drawable.yellow_piece;
        } else {
            return R.drawable.empty_piece;
        }
    }

    private class MyOnClickListener implements View.OnClickListener {
        private Tuple positions;
        private Context context;

        MyOnClickListener(Tuple positions, Context context) {
            this.positions = positions;
            this.context = context;

        }

        @Override
        public void onClick(View view) {
            if (board.getPosiblePositions().contains(positions)) {
                doMovement(positions);
                if(isFinal()){
                    goToResults();
                }
            }
        }

        private boolean isFinal() {
            if(board.isFinalMovement(positions)){
                return true;
            }else{
                if(board.timeToEnd){
                    return true;
                }
                else{
                    return false;
                }
            }
        }

        private void doMovement(Tuple positions) {
            board.doMovement(positions);
            board.changeTurn();
            board.getPossiblePositions();
            updateTorn();
            updateTemps();
            notifyDataSetChanged();
        }
    }


    private void updateTemps() {
        if(this.controlTemps){
            this.tempsRestant.setText(String.valueOf(board.getTime() / 1000));
            this.tempsRestant.setTextColor(mContext.getResources().getColor(R.color.red));
        }
        else {
            this.tempsRestant.setText(String.valueOf((System.currentTimeMillis() / 1000) - board.temps));
            this.tempsRestant.setTextColor(mContext.getResources().getColor(R.color.blue));
        }
    }

    private void updateTorn() {
        if(board.torn == 1){
            this.fotoTorn.setImageResource(R.drawable.turn_red);
        }
        else{
            this.fotoTorn.setImageResource(R.drawable.turn_yellow);
        }
    }
}
