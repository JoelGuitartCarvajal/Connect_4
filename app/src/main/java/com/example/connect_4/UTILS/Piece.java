package com.example.connect_4.UTILS;

public class Piece {
    // Tres estats: 0 buida, 1 vermell, 2 groc
    private int state;
    private static Tuple position;
    // Comencem amb estat buit (0)
    public Piece (){
        this.state = 0;
    }

    public static void setPosition(int i, int j){
        position = new Tuple(i,j);
    }
    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
    public int getTupleI() {
        return position.getTupleI();
    }

    public int getTupleJ() {
        return position.getTupleJ();
    }

    public Tuple getTuple() {
        return position;
    }
}
