package com.example.connect_4.UTILS;
import com.example.connect_4.UTILS.Piece;

public class Board extends Piece {
    private static int size;
    private Piece[][] board;

    public Board(int size){
        this.size = size;
        this.board = new Piece[size][size];
    }
    public void initializeBoard(){
        for (int i=0; i<= size;i++){
            for(int j=0; j<= size;j++){
                board[i][j].setState(0);
            }
        }
    }
}
