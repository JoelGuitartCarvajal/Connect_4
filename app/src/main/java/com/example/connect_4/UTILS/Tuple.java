package com.example.connect_4.UTILS;

public class Tuple {
    public int []tuple = new int[2];
    public Tuple(int i, int j){
        this.tuple[0] = i;
        this.tuple[1] = j;
    }

    public int getTupleI() {
        return tuple[0];
    }

    public int getTupleJ() {
        return tuple[1];
    }
}
