package com.example.connect_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.example.connect_4.UTILS.Board;
import com.example.connect_4.UTILS.ImageAdapter;

public class JocActv extends AppCompatActivity {

    private static String alias;
    private static int mida;
    private static boolean controlTemps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc_actv);
        getValues();
        Board board = new Board(mida,controlTemps);
        board.initializeBoard();
        GridView gridView = findViewById(R.id.graella);

    }

    private void getValues() {
        alias = getIntent().getStringExtra("alias");
        mida = getIntent().getIntExtra("mida", 5);
        controlTemps = getIntent().getBooleanExtra("temps",false);
    }
}