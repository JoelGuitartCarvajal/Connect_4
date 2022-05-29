package com.example.connect_4.UTILS;

public class Piece {
    // Tres estats: 0 buida, 1 vermell, 2 groc
    public int state;
    // Comencem amb estat buit (0)
    public Piece (){
        this.state = 0;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

}
