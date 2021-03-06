package com.example.connect_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connect_4.Fragments.GameLogFragment;
import com.example.connect_4.Fragments.Grid_GameFragment;
import com.example.connect_4.UTILS.Board;
import com.example.connect_4.UTILS.ImageAdapter;
import com.example.connect_4.UTILS.Variables;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JocActv extends FragmentActivity implements Grid_GameFragment.GridGameListener {

    /*public String alias;
    public int mida;
    public boolean controlTemps;
    public ImageView fotoTorn;
    public TextView temps;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc_actv);
        Grid_GameFragment gameFragment = (Grid_GameFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentJoc);
        gameFragment.setListener(this);
    }

    @Override
    public void onGameClickLog(Integer position, Board board) {
        GameLogFragment gameLogFragment = (GameLogFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentLogJoc);
        if(gameLogFragment!=null && gameLogFragment.isInLayout()){
            String log = getLog(position,board);
            gameLogFragment.showInGameLog(log);
        }
    }

    private String getLog(int position, Board board) {
        String log = "";
        log += Variables.AliasLog + board.alias + "\n";
        log += Variables.midaGraella + String.valueOf(board.size) + "\n";
        log += Variables.casellaOcupada + "(" + String.valueOf(position / board.size) + "," + String.valueOf(position % board.size) + ")" + "\n";
        log += Variables.iniciTirada + new SimpleDateFormat("hh:mm:ss").format(new Date()) + Variables.finalTirada + new SimpleDateFormat("hh:mm:ss").format(new Date()) + "\n";
        if(board.controlTemps == false){
            log += Variables.controlTempsDesactivat;
        } else {
            log += Variables.tempsRestant + String.valueOf(board.getTime() / 1000) + Variables.segons;
        }
        return log;

    }
}