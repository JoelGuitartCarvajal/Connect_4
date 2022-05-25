package com.example.connect_4.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connect_4.PreferencesActivity;
import com.example.connect_4.R;
import com.example.connect_4.UTILS.Board;
import com.example.connect_4.UTILS.ImageAdapter;
import com.example.connect_4.UTILS.Variables;

public class Grid_GameFragment extends Fragment {

    public GridGameListener listener;
    public String alias;
    public int mida;
    public boolean controlTemps;
    public ImageView fotoTorn;
    public TextView temps;
    public Board board;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid_game, container, false);
    }
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        super.onViewCreated(v,savedInstanceState);
        getPreferences();
        if(savedInstanceState != null){
            onRestoreInstanceState(savedInstanceState);
        } else {
            board = new Board(mida, controlTemps, alias);
            board.initializeBoard();
        }
        GridView gridView = requireView().findViewById(R.id.graella);
        ImageAdapter imageAdapter = new ImageAdapter(requireActivity(), mida, board,controlTemps,alias,fotoTorn,temps,listener);
        gridView.setNumColumns(mida);
        gridView.setAdapter(imageAdapter);
    }

    private void onRestoreInstanceState(Bundle savedInstanceState) {
        alias = savedInstanceState.getString(Variables.alias);
        mida = savedInstanceState.getInt(Variables.mida);
        controlTemps = savedInstanceState.getBoolean(Variables.controltemps);
        board = savedInstanceState.getParcelable(Variables.board);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString(Variables.alias,alias);
        savedInstanceState.putInt(Variables.mida,mida);
        savedInstanceState.putBoolean(Variables.controltemps,controlTemps);
        savedInstanceState.putParcelable(Variables.board,board);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void getPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity());
        alias = prefs.getString(Variables.alias,"Jugador1");
        mida = Integer.parseInt( prefs.getString(Variables.mida, String.valueOf(7)));
        controlTemps = prefs.getBoolean(Variables.controltemps,false);
        fotoTorn = requireView().findViewById(R.id.torn);
        temps = requireView().findViewById(R.id.temps);
    }
    public interface GridGameListener {
        void onGameClickLog(Integer position, Board board);
    }
    public void setListener(GridGameListener listener){
        this.listener = listener;
    }
    @Override
    public void onAttach (Context context){
        super.onAttach(context);
        try{
            this.listener = (GridGameListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement GridGameListener");
        }
    }
}