package com.example.connect_4.UTILS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connect_4.Fragments.Grid_GameFragment;
import com.example.connect_4.R;
import com.example.connect_4.ResultatPartida;

public class ImageAdapter extends BaseAdapter {
    private Activity mContext;
    private int mida;
    private Board board;
    private boolean controlTemps;
    private String alias;
    private ImageView fotoTorn;
    private TextView tempsRestant;
    private Grid_GameFragment.GridGameListener listener;

    public ImageAdapter(Activity mContext, int mida, Board board, boolean controlTemps, String alias, ImageView fotoTorn, TextView tempsRestant, Grid_GameFragment.GridGameListener listener) {
        this.mContext = mContext;
        this.mida = mida;
        this.board = board;
        this.controlTemps = controlTemps;
        this.alias = alias;
        this.fotoTorn = fotoTorn;
        this.tempsRestant = tempsRestant;
        this.listener = listener;
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
        /*int x = position / mida;
        int y = position % mida;
        Tuple tupla = new Tuple(x, y);*/
        btn.setBackgroundResource(setBackground(position));
        btn.setOnClickListener(new MyOnClickListener(mContext, position));
        btn.setId(position);
        return btn;
    }

    private int setBackground(int position) {
        if (board.getUserPositions().contains(position)) {
            return R.drawable.red_piece;
        }
        if (board.getCPUPositions().contains(position)) {
            return R.drawable.yellow_piece;
        } else {
            return R.drawable.empty_piece;
        }
    }

    private class MyOnClickListener implements View.OnClickListener {
        private int position;
        private Context context;

        MyOnClickListener(Context context, int position) {
            this.context = context;
            this.position = position;

        }

        @Override
        public void onClick(View view) {
            if (board.getposiblepositions().contains(position)) {
                doMovement(position);
                if(isFinal()){
                    goToResults();
                } else {
                    position = ramdomCPU(position);
                    doMovement(position);
                    if(isFinal()){
                        goToResults();
                    }
                }
            } else {
                Toast.makeText(context, "Moviment invàlid", Toast.LENGTH_SHORT).show();
            }
        }

        private boolean isFinal() {
            if(board.isFinalMovement(position)){
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

        private void doMovement(int positions) {
            board.doMovement(position);
            board.changeTurn();
            board.getPossiblePositions();
            updateTorn();
            updateTemps();
            notifyDataSetChanged();
            listener.onGameClickLog(position,board);
        }
    }

    private Integer ramdomCPU(int position) {
        int randomInt = 100;
        while (!board.getposiblepositions().contains(randomInt)) {
            randomInt = (int) (Math.random() * (mida * mida));
        }
        return randomInt;
    }

    private void goToResults() {
        int timeLeft;
        if(controlTemps){
            if(board.timeToEnd){
                timeLeft = 0;
            } else {
                timeLeft = board.getTime() / 1000;
            }
        } else {
            timeLeft = (int) (System.currentTimeMillis() / 1000 - board.temps);
        }
        Intent intent = new Intent(mContext, ResultatPartida.class);
        intent.putExtra("alias", alias);
        intent.putExtra("mida",mida);
        intent.putExtra("temps",controlTemps);
        intent.putExtra("tempsrestant", timeLeft);
        intent.putExtra("torn", board.torn);
        intent.putExtra("empat",board.maximPieces);
        mContext.startActivity(intent);
        mContext.finish();
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
