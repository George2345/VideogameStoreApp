package com.example.practicafinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class PS4MainFragment extends ListFragment {

    public PS4MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ps4games));
        setListAdapter(adapter);*/
        SQLiteOpenHelper gameDbHelper = new GameDataHelper(getContext()) ;
        SQLiteDatabase db = gameDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _id, NAME, PRICE FROM GAMES WHERE PLATFORM='PS4' LIMIT 3", null);
        /*Cursor cursor = db.query("GAMES",
                new String[] {"_id", "NAME", "PRICE"},
                null,
                null,
                null, null, null);*/
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(
                getContext(),
                R.layout.item_main_list,
                cursor,
                new String[]{"NAME", "PRICE"},
                new int[] {R.id.textViewTitulo, R.id.textViewPrecio},
                0);

        setListAdapter(listAdapter);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_p_s4_main, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Intent intent = new Intent(getActivity(), GameDetail.class);
        SQLiteOpenHelper gameDbHelper = new GameDataHelper(getContext()) ;
        try
        {
            SQLiteDatabase db = gameDbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(" SELECT _id FROM GAMES WHERE PLATFORM='PS4'  LIMIT 3", null);
            /*Cursor cursor = db.query("GAMES",
                    new String[] {"_id"},
                    null,
                    null,
                    null, null, null);*/
            cursor.move(position+1);
            intent.putExtra("GAMEID", cursor.getString(0));
            startActivity(intent);
        }
        catch (Exception e) {
        }
    }

}