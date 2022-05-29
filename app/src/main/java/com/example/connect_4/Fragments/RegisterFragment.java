package com.example.connect_4.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.connect_4.ConsultActv;
import com.example.connect_4.R;
import com.example.connect_4.UTILS.SQLite;
import com.example.connect_4.UTILS.Variables;

public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    public void viewDetails(int cellSelected) {
        ((TextView) requireView().findViewById(R.id.detallDades)).setText(makeText(cellSelected));
    }

    private String makeText(int position) {
        SQLite db = SQLite.getInstance(getContext());
        Cursor cursor = db.getDataFromDB();
        cursor.moveToPosition(position);
        String logPartida = "";
        logPartida+= Variables.AliasLog + "" + cursor.getString(1) + "\n" +
                    Variables.DataLog + "" + cursor.getString(2) + "\n" +
                    Variables.midaGraella + "" + cursor.getString(3) + "\n" +
                    Variables.ControlTempsLog + "" + cursor.getString(4) + "\n" +
                    Variables.tempsFinal + "" + cursor.getString(5) + "\n" +
                    Variables.sqliteResultat + "" + cursor.getString(6);
        return logPartida;
    }
    public void onClick(View v) {
        Intent intent = new Intent(requireContext(), ConsultActv.class);
        startActivity(intent);
    }
}