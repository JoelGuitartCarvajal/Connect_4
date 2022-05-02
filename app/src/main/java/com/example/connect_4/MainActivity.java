package com.example.connect_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ajudaBt = findViewById(R.id.ajuda);
        Button començarBt = findViewById(R.id.jugar);
        Button sortirBt = findViewById(R.id.sortir);
        ajudaBt.setOnClickListener(this);
        començarBt.setOnClickListener(this);
        sortirBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ajuda:
                Intent intent = new Intent(this,Ajuda.class);
                startActivity(intent);
                finish();
                break;
            case R.id.jugar:
                Intent intent2 = new Intent(this,Configuracio.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.sortir:
                finish();
                break;
        }
    }
}