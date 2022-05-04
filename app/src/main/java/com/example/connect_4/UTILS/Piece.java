package com.example.connect_4.UTILS;

public class Piece {
    // Tres estats: 0 buida, 1 vermell, 2 groc
    private static int state;

    // Comencem amb estat buit (0)
    public Piece (){
        this.state = 0;
    }

    public static void setState(int state) {
        Piece.state = state;
    }

    public static int getState() {
        return state;
    }
}
