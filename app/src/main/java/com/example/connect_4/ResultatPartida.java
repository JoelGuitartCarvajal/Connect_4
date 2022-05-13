package com.example.connect_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Date;

public class ResultatPartida extends AppCompatActivity implements View.OnClickListener {

    private int mida;
    private boolean controlTemps;
    private int timeLeft;
    private String alias;
    private int torn;
    private int maximPieces;

    private EditText data;
    private EditText log;
    private EditText email;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_partida);

        toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);


        getIntentValues();
        data = findViewById(R.id.diaihora);
        log = findViewById(R.id.valorslog);
        email= findViewById(R.id.emaildst);

        Button enviarMail = findViewById(R.id.enviarmail);
        Button novaPartida = findViewById(R.id.novapartida);
        Button sortir = findViewById(R.id.sortir);
        enviarMail.setOnClickListener(this);
        novaPartida.setOnClickListener(this);
        sortir.setOnClickListener(this);

        setEditTexts();
    }

    private void setEditTexts() {
        int tempsTotal = 60;
        String logTemps ="";
        Date dataAvui = new Date();
        data.setText(dataAvui.toString());
        if(controlTemps){
            tempsTotal = tempsTotal - timeLeft;
            logTemps+= "\n" + "Temps total:" + tempsTotal + "segons.";
        } else {
            logTemps+= "\n" + "Temps total:" + timeLeft + "segons.";
        }
        if (timeLeft == 0){
            log.setText("Alias" + alias + "." + "\n" +
                    "Mida graella:" + String.valueOf(mida) + "." + logTemps + "\n" +
                    "Temps esgotat.");
        }
        else if(maximPieces == 0){
            log.setText("Alias" + alias + "." + "\n" +
                    "Mida graella:" + String.valueOf(mida) + "." + logTemps + "\n" +
                    "Heu empatat.");
        }
        else if(torn == 2){
            log.setText("Alias" + alias + "." + "\n" +
                    "Mida graella:" + String.valueOf(mida) + "." + logTemps + "\n" +
                    "Has GUANYAT!.");
        }
        else if (torn == 1){
            log.setText("Alias" + alias + "." + "\n" +
                    "Mida graella:" + String.valueOf(mida) + "." + logTemps + "\n" +
                    "Has perdut malo.");
        }
    }

    private void getIntentValues() {
        alias = getIntent().getStringExtra("alias");
        mida = getIntent().getIntExtra("mida", 5);
        controlTemps = getIntent().getBooleanExtra("temps",false);
        timeLeft = getIntent().getIntExtra("tempsrestant", 0);
        torn = getIntent().getIntExtra("torn",0);
        maximPieces = getIntent().getIntExtra("empat",0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sortir:
                finish();
                break;
            case R.id.novapartida:
                Intent intent = new Intent(this, JocActv.class);
                startActivity(intent);
                finish();
                break;
            case R.id.enviarmail:
                if(!email.getText().toString().isEmpty()){
                    Intent intent1 = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email.getText().toString()));
                    intent1.putExtra(Intent.EXTRA_SUBJECT,"Resultat Partida");
                    intent1.putExtra(Intent.EXTRA_TEXT, log.getText().toString());
                    startActivity(intent1);
                }else {
                    Toast.makeText(this, "Posa un email valid", Toast.LENGTH_SHORT).show();
                }
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