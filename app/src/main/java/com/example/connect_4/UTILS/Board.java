package com.example.connect_4.UTILS;
import android.os.CountDownTimer;

import com.example.connect_4.UTILS.Piece;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Board  {
    private static int size;
    private Piece[][] board;
    private static boolean controlTemps;
    private CounterTime clock;
    boolean timeToEnd = false;

    public Board(int size, boolean controlTemps){
        this.size = size;
        this.board = new Piece[size][size];
        this.controlTemps = controlTemps;
    }
    public void initializeBoard(){
        for (int i=0; i<= size;i++){
            for(int j=0; j<= size;j++){
                board[i][j] = new Piece();
            }
        }
        if (controlTemps){

        }
    }
}
