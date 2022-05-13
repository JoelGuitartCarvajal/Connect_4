package com.example.connect_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.config:
                Intent intent1 = new Intent(this, PreferencesActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}