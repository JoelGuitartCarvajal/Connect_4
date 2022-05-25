package com.example.connect_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.connect_4.UTILS.SQLite;
import com.example.connect_4.UTILS.Variables;

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
    private ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_partida);

        toolbar = findViewById(R.id.toolbar_resultat);
        setSupportActionBar(toolbar);



        data = findViewById(R.id.diaihora);
        log = findViewById(R.id.valorslog);
        email= findViewById(R.id.emaildst);

        Button enviarMail = findViewById(R.id.enviarmail);
        Button novaPartida = findViewById(R.id.novapartida);
        Button sortir = findViewById(R.id.sortir);
        enviarMail.setOnClickListener(this);
        novaPartida.setOnClickListener(this);
        sortir.setOnClickListener(this);
        SQLite bd = SQLite.getInstance(getApplicationContext());


        if(savedInstanceState!= null){
            getBackInstanceState(savedInstanceState);
        } else {
            values = new ContentValues();
            getIntentValues();
            setEditTexts();
            if (bd.register(values) != -1) {
                Toast.makeText(this, R.string.TextDadesGuardadesCorrectament, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getBackInstanceState(Bundle savedInstanceState) {
        data.setText(savedInstanceState.getString(Variables.data));
        log.setText(savedInstanceState.getString(Variables.log));
        email.setText(savedInstanceState.getString(Variables.email));
        mida = savedInstanceState.getInt(Variables.mida);
        alias = savedInstanceState.getString(Variables.alias);
        timeLeft = savedInstanceState.getInt(Variables.timeLeft);
        controlTemps = savedInstanceState.getBoolean(Variables.controltemps);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(Variables.data,data.getText().toString());
        savedInstanceState.putString(Variables.log,log.getText().toString());
        savedInstanceState.putString(Variables.email,email.getText().toString());
        savedInstanceState.putInt(Variables.mida,mida);
        savedInstanceState.putString(Variables.alias,alias);
        savedInstanceState.putInt(Variables.timeLeft,timeLeft);
        savedInstanceState.putBoolean(Variables.controltemps,controlTemps);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void setEditTexts() {
        int tempsTotal = 60;
        String logTemps ="";
        Date dataAvui = new Date();
        data.setText(dataAvui.toString());
        values.put("data", String.valueOf(dataAvui));
        if(controlTemps){
            tempsTotal = tempsTotal - timeLeft;
            logTemps+= "\n" + "Temps total:" + tempsTotal + "segons.";
        } else {
            logTemps+= "\n" + "Temps total:" + timeLeft + "segons.";
        }
        if (timeLeft == 0){
            log.setText("Alias:" + alias + "." + "\n" +
                    "Mida graella:" + String.valueOf(mida) + "." + logTemps + "\n" +
                    "Temps esgotat.");
            values.put("Resultat","Temps esgotat!");
        }
        else if(maximPieces == 0){
            log.setText("Alias:" + alias + "." + "\n" +
                    "Mida graella:" + String.valueOf(mida) + "." + logTemps + "\n" +
                    "Heu empatat.");
            values.put("Resultat","Has empatat!");
        }
        else if(torn == 2){
            log.setText("Alias:" + alias + "." + "\n" +
                    "Mida graella:" + String.valueOf(mida) + "." + logTemps + "\n" +
                    "Has GUANYAT!.");
            values.put("Resultat","Has GUANYAT!");
        }
        else if (torn == 1){
            log.setText("Alias:" + alias + "." + "\n" +
                    "Mida graella:" + String.valueOf(mida) + "." + logTemps + "\n" +
                    "Has perdut malo.");
            values.put("Resultat","Has PERDUT!");
        }
    }

    private void getIntentValues() {
        alias = getIntent().getStringExtra("alias");
        mida = getIntent().getIntExtra("mida", 5);
        controlTemps = getIntent().getBooleanExtra("temps",false);
        timeLeft = getIntent().getIntExtra("tempsrestant", 0);
        torn = getIntent().getIntExtra("torn",0);
        maximPieces = getIntent().getIntExtra("empat",0);
        values.put("nom",alias);
        values.put("Midagraella",mida);
        values.put("ControlTemps",controlTemps);
        values.put("TempsFinal",timeLeft);
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
                    intent1.putExtra(Intent.EXTRA_SUBJECT,R.string.ResultatPartidaIntent);
                    intent1.putExtra(Intent.EXTRA_TEXT, log.getText().toString());
                    startActivity(intent1);
                }else {
                    Toast.makeText(this, R.string.EmailInvalid, Toast.LENGTH_SHORT).show();
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