package com.example.connect_4.UTILS;

import java.util.ArrayList;
import java.util.List;

public class Board  {
    private static int size;
    private Piece[][] board;
    private static boolean controlTemps;
    private CounterTime clock;
    private List<Tuple> llocsPosibles = new ArrayList<>();
    private List<Tuple> pecesUsuari;
    private List<Tuple> pecesCPU;
    boolean timeToEnd = false;
    long temps;
    int torn = 1;
    int maximPieces;

    public Board(int size, boolean controlTemps){
        this.size = size;
        this.board = new Piece[size][size];
        this.controlTemps = controlTemps;
    }
    public void initializeBoard(){
        pecesUsuari = new ArrayList<>();
        pecesCPU = new ArrayList<>();
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

    public void getPossiblePositions() {
        llocsPosibles.clear();
        for (int j=0; j< size;j++){
            for(int i=0; i< size;i++){
                if(board[i][j].getState() == 0 && (i+1 == size || board[i+1][j].getState()!=0) ){
                    llocsPosibles.add(board[i][j].getTuple());
                }
            }
        }
    }
    public List<Tuple> getUserPositions(){
        return pecesUsuari;
    }
    public List<Tuple> getCPUPositions(){
        return pecesCPU;
    }
    public List<Tuple> getPosiblePositions(){
        return llocsPosibles;
    }

    public void doMovement(Tuple positions){
        if (this.torn == 1){
            pecesUsuari.add(positions);
            board[positions.getTupleI()][positions.getTupleJ()].setState(1);
        }
        else{
            pecesCPU.add(positions);
            board[positions.getTupleI()][positions.getTupleJ()].setState(2);
        }
    }

    public void changeTurn() {
        if(this.torn == 1){
            this.torn = 2;
        }
        else {
            this.torn = 1;
        }
    }

    public int getTime() {
        return (int) clock.getTime();
    }

    public boolean isFinalMovement(Tuple positions) {
        maximPieces = size * size - getUserPositions().size() - getCPUPositions().size();
        if(maximPieces == 0){
            return true;
        } else if(fourInCol(positions)){
            return true;
        } else if(fourInRow(positions)){
            return true;
        } else if(fourInMainDiagonal(positions)){
            return true;
        } else if(fourInContraDiagonal(positions)){
            return true;
        }
        return false;
    }

    private boolean fourInContraDiagonal(Tuple positions) {
        int reversedTorn = torn;
        int connect = 1;
        if(reversedTorn == 1){
            reversedTorn = 2;
        } else {
            reversedTorn = 1;
        }
        for (int i =positions.getTupleI(), j = positions.getTupleJ(); i < size && j < size && i>=0 && j>=0; i++,j++ ){
            if(board[i][j].getState()!=reversedTorn ){
                break;
            }
            connect++;
        }
        for (int i =positions.getTupleI(), j = positions.getTupleJ(); i < size && j < size && i>=0 && j>=0; i--,j-- ){
            if(board[i][j].getState()!=reversedTorn ){
                break;
            }
            connect++;
        }
        return connect >= 4;
    }

    private boolean fourInMainDiagonal(Tuple positions) {
        int reversedTorn = torn;
        int connect = 1;
        if(reversedTorn == 1){
            reversedTorn = 2;
        } else {
            reversedTorn = 1;
        }
        for (int i =positions.getTupleI(), j = positions.getTupleJ(); i < size && j < size && i>=0 && j>=0; i++,j-- ){
            if(board[i][j].getState()!=reversedTorn ){
                break;
            }
            connect++;
        }
        for (int i =positions.getTupleI(), j = positions.getTupleJ(); i < size && j < size && i>=0 && j>=0; i--,j++ ){
            if(board[i][j].getState()!=reversedTorn ){
                break;
            }
            connect++;
        }
        return connect >= 4;
    }

    private boolean fourInRow(Tuple positions) {
        int reversedTorn = torn;
        int connect = 0;
        if(reversedTorn == 1){
            reversedTorn = 2;
        } else {
            reversedTorn = 1;
        }
        for (int i=0; i<size; i++){
            if(board[positions.getTupleI()][i].getState() == reversedTorn){
                connect++;
            }
            else {
                connect = 0;
            }
            if(connect == 4){
                return true;
            }
        }
        return false;
    }

    private boolean fourInCol(Tuple positions) {
        int reversedTorn = torn;
        int connect = 0;
        if(reversedTorn == 1){
            reversedTorn = 2;
        } else {
             reversedTorn = 1;
        }
        for (int i=0; i<size; i++){
            if(board[i][positions.getTupleJ()].getState() == reversedTorn){
                connect++;
            }
            else {
                connect = 0;
            }
            if(connect == 4){
                return true;
            }
        }
        return false;
    }
}
