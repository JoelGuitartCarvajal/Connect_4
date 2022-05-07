package com.example.connect_4.UTILS;
import android.os.CountDownTimer;

import com.example.connect_4.UTILS.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Board  {
    private static int size;
    private Piece[][] board;
    private static boolean controlTemps;
    private CounterTime clock;
    private List<Tuple> llocsPosibles;
    private List<Tuple> pecesUsuari;
    private List<Tuple> pecesCPU;
    boolean timeToEnd = false;
    long temps;

    public Board(int size, boolean controlTemps){
        this.size = size;
        this.board = new Piece[size][size];
        this.controlTemps = controlTemps;
    }
    public void initializeBoard(){
        for (int i=0; i< size;i++){
            for(int j=0; j< size;j++){
                board[i][j] = new Piece();
                board[i][j].setPosition(i,j);
            }
        }
        getPossiblePositions();
        if (controlTemps){
            clock = new CounterTime(60 * 1000, 1000,this);
            clock.start();
        } else{
            this.temps = System.currentTimeMillis() / 1000;
        }
    }

    private void getPossiblePositions() {
        llocsPosibles.clear();
        for (int j=0; j< size;j++){
            for(int i=0; i< size;i++){
                if(board[i][j].getState() == 0 && (i+1 == size || board[i+1][j].getState()!=0) ){
                    llocsPosibles.add(board[i][j].getTuple());
                }
            }
        }
    }
}
