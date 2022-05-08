package com.example.connect_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connect_4.UTILS.Board;
import com.example.connect_4.UTILS.ImageAdapter;

public class JocActv extends AppCompatActivity {

    public String alias;
    public int mida;
    public boolean controlTemps;
    public ImageView fotoTorn;
    public TextView temps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc_actv);
        getValues();
        Board board = new Board(mida,controlTemps);
        board.initializeBoard();
        GridView gridView = findViewById(R.id.graella);
        ImageAdapter imageAdapter = new ImageAdapter(this, mida, board,controlTemps,alias,fotoTorn,temps);
        gridView.setNumColumns(mida);
        gridView.setAdapter(imageAdapter);

    }

    private void getValues() {
        alias = getIntent().getStringExtra("alias");
        mida = getIntent().getIntExtra("mida", 5);
        controlTemps = getIntent().getBooleanExtra("temps",false);
        fotoTorn = findViewById(R.id.torn);
        temps = findViewById(R.id.temps);
    }
}