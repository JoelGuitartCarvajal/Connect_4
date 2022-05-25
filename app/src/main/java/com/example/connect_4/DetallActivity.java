package com.example.connect_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.connect_4.Fragments.RegisterFragment;
import com.example.connect_4.UTILS.Variables;

public class DetallActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall);

        RegisterFragment detall = (RegisterFragment) getSupportFragmentManager().
                findFragmentById(R.id.detallFrg);
        detall.viewDetails(getIntent().getIntExtra(Variables.dades, 0));
    }
}