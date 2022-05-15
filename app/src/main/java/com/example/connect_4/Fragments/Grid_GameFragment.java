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

import com.example.connect_4.R;
import com.example.connect_4.UTILS.Board;
import com.example.connect_4.UTILS.ImageAdapter;

public class Grid_GameFragment extends Fragment {

    public GridGameListener listener;
    public String alias;
    public int mida;
    public boolean controlTemps;
    public ImageView fotoTorn;
    public TextView temps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid__game, container, false);
    }
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        super.onViewCreated(v,savedInstanceState);
        getPreferences();
        Board board = new Board(mida,controlTemps,alias);
        board.initializeBoard();
        GridView gridView = requireView().findViewById(R.id.graella);
        ImageAdapter imageAdapter = new ImageAdapter(requireActivity(), mida, board,controlTemps,alias,fotoTorn,temps);
        gridView.setNumColumns(mida);
        gridView.setAdapter(imageAdapter);
    }

    private void getPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        alias = prefs.getString("alias","Jugador1");
        mida = prefs.getInt("board_size",7);
        controlTemps = prefs.getBoolean("control_temps",false);
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