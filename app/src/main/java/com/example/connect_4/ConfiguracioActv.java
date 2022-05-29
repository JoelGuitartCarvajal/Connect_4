package com.example.connect_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ConfiguracioActv extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracio);
        Button startBt = findViewById(R.id.btSTART);
        startBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText alias = findViewById(R.id.alias);
        RadioGroup radioGroup = findViewById(R.id.mida);
        RadioButton mida = findViewById(radioGroup.getCheckedRadioButtonId());
        CheckBox temps = findViewById(R.id.controlTemps);
        switch(view.getId()){
            case R.id.btSTART:
                if(!alias.getText().toString().isEmpty()) {
                    Intent intent = new Intent(this, JocActv.class);
                    intent.putExtra("alias",alias.getText().toString());
                    intent.putExtra("mida",Integer.parseInt(mida.getText().toString()));
                    intent.putExtra("temps",temps.isChecked());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this,R.string.Mssg, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}