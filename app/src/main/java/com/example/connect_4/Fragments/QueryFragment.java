package com.example.connect_4.Fragments;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connect_4.R;
import com.example.connect_4.UTILS.SQLite;

public class QueryFragment extends Fragment {

    private GameListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View v,Bundle state) {
        super.onViewCreated(v,state);
        SQLite database = SQLite.getInstance(getContext());
        ListView listado = requireView().findViewById(R.id.QueryList);
        listado.setAdapter(new GameAdapter(this, database));
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(listener != null){
                    listener.callConsultAct(i);
                }
            }
        }
        );
    }

    public void setGameListener(GameListener listener) {
        this.listener = listener;
    }

    public interface GameListener {
        void callConsultAct(int pos);
    }


    private class GameAdapter extends BaseAdapter {
        Activity context;
        SQLite database;

        GameAdapter(QueryFragment fragmentQuery, SQLite database) {
            this.context = fragmentQuery.getActivity();
            this.database = database;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.fragment_query, null);

            Cursor cursor = database.getDataFromDB();
            cursor.moveToPosition(position);

            TextView lblUser = (TextView) item.findViewById(R.id.name);
            lblUser.setText(cursor.getString(1));

            TextView lblTime = (TextView) item.findViewById(R.id.date);
            lblTime.setText(cursor.getString(2));

            TextView lblPosition = (TextView) item.findViewById(R.id.result);
            lblPosition.setText(cursor.getString(6));

            return (item);
        }
        @Override
        public int getCount() {
            return database.getDataFromDB().getCount();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

    }
}