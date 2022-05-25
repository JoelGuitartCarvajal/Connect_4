package com.example.connect_4;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.connect_4.Fragments.QueryFragment;
import com.example.connect_4.Fragments.RegisterFragment;
import com.example.connect_4.UTILS.Variables;

public class ConsultActv extends FragmentActivity implements QueryFragment.GameListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_actv);
        QueryFragment query = (QueryFragment) getSupportFragmentManager().findFragmentById(R.id.queryFrg);
        query.setGameListener(this);
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void callConsultAct(int pos) {
        RegisterFragment rFgt = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.detallFrg);
        if (rFgt != null && rFgt.isInLayout()) {
            rFgt.viewDetails(pos);
        }
        else {
            Intent intent = new Intent(this, DetallActivity.class);
            intent.putExtra(Variables.dades, pos);
            startActivity(intent);
        }
    }
}