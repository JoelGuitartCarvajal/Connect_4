package com.example.connect_4.UTILS;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Board implements Parcelable {
    public int size;
    public Piece[][] board;
    public boolean controlTemps;
    public CounterTime clock;
    public List<Integer> llocsPosibles = new ArrayList<>();
    public List<Integer> pecesUsuari;
    public List<Integer> pecesCPU;
    boolean timeToEnd = false;
    long temps;
    int torn = 1;
    int maximPieces;
    public String alias;

    public Board(int size, boolean controlTemps,String alias){
        this.size = size;
        this.board = new Piece[size][size];
        this.controlTemps = controlTemps;
        this.alias = alias;
    }

    protected Board(Parcel in) {
        size = in.readInt();
        controlTemps = in.readByte() != 0;
        timeToEnd = in.readByte() != 0;
        temps = in.readLong();
        torn = in.readInt();
        maximPieces = in.readInt();
        alias = in.readString();
    }

    public static final Creator<Board> CREATOR = new Creator<Board>() {
        @Override
        public Board createFromParcel(Parcel in) {
            return new Board(in);
        }

        @Override
        public Board[] newArray(int size) {
            return new Board[size];
        }
    };

    public void initializeBoard(){
        pecesUsuari = new ArrayList<>();
        pecesCPU = new ArrayList<>();
        for (int i=0; i< size;i++){
            for(int j=0; j< size;j++){
                board[i][j] = new Piece();
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
        boolean emptyColumn = true;
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size && emptyColumn; i++) {
                if (board[i][j].getState()!=0) {
                    llocsPosibles.add(((i - 1) * size) + j);
                    emptyColumn = false;
                }
            }
            if (emptyColumn) {
                llocsPosibles.add(((size - 1) * size) + j);
            }
            emptyColumn = true;
        }
    }
    public List<Integer> getUserPositions(){
        return pecesUsuari;
    }
    public List<Integer> getCPUPositions(){
        return pecesCPU;
    }
    public List<Integer> getposiblepositions(){
        return llocsPosibles;
    }

    public void doMovement(int position){
        if (this.torn == 1){
            pecesUsuari.add(position);
            board[position/size][position % size].setState(1);
        }
        else{
            pecesCPU.add(position);
            board[position / size][position % size].setState(2);
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

    public boolean isFinalMovement(int position) {
        maximPieces = size * size - getUserPositions().size() - getCPUPositions().size();
        if(maximPieces == 0){
            return true;
        } else if(fourInCol(position)){
            return true;
        } else if(fourInRow(position)){
            return true;
        } else if(fourInMainDiagonal(position)){
            return true;
        } else if(fourInContraDiagonal(position)){
            return true;
        }
        return false;
    }

    private boolean fourInContraDiagonal(int position) {
        int connected = 1;
        int x = position/size;
        int y = position%size;
        if((position + ((size-1)*y)>size*size-1)) {
            position = position +(size-1)*((size-x-1));
            x = position / size;
            y = position % size;
        }else{
            if(x!=4) {
                position = position + ((size - 1) * y);
            }
            x = position / size;
            y = position % size;
        }

        for (int i=x, j=y; i>0 && j<size-1; i--, j++) {
            if (y != size - 1) {
                if (board[i][j].getState() == board[i - 1][j + 1].getState() && board[i - 1][j + 1].getState() != 0) {
                    connected++;
                } else {
                    if (connected == 4) {
                        return true;
                    }
                    connected = 1;
                }
            }
        }
        return connected == 4;
    }

    private boolean fourInMainDiagonal(int position) {
        int connected = 1;
        int x = position/size;
        int y = position%size;
        if((position - ((size+1)*y)<0)) {
            position = position - ((size + 1) * x);
            x = position / size;
            y = position % size;
        }else{
            position = position - ((size + 1) * y);
            x = position / size;
            y = position % size;
        }

        for (int i=x, j=y; i<size-1 && j<size-1; i++, j++){
            if (board[i][j].getState() == board[i+1][j+1].getState() && board[i+1][j+1].getState() != 0) {
                connected++;
            }
            else {
                if(connected == 4){return true;}
                connected = 1;
            }
        }
        return connected == 4;
    }

    private boolean fourInRow(int position) {
        int reversedTorn = torn;
        int connect = 0;
        if(reversedTorn == 1){
            reversedTorn = 2;
        } else {
            reversedTorn = 1;
        }
        for (int i=0; i<size; i++){
            if(board[position / size][i].getState() == reversedTorn){
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

    private boolean fourInCol(int position) {
        int reversedTorn = torn;
        int connect = 0;
        if(reversedTorn == 1){
            reversedTorn = 2;
        } else {
             reversedTorn = 1;
        }
        for (int i=0; i<size; i++){
            if(board[i][position % size].getState() == reversedTorn){
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(board);
        parcel.writeInt(size);
        parcel.writeInt(torn);
        parcel.writeList(pecesUsuari);
        parcel.writeList(pecesCPU);
    }
}
