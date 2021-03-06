package com.example.connect_4.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.connect_4.R;


public class GameLogFragment extends Fragment {

    private TextView gameLog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_log, container, false);
    }

    public void showInGameLog(String log){
        gameLog = requireView().findViewById(R.id.logJoc);
        gameLog.setText(log);
    }
}