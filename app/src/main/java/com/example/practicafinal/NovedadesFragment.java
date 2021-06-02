package com.example.practicafinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class NovedadesFragment extends ListFragment {



    public NovedadesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Obtengo la fecha actual
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = df.format(c);

        //Obtengo la fecha menos 1 mes
        Calendar c2 = Calendar.getInstance();
        c2.add(Calendar.MONTH, -1);
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate2 = df2.format(c2.getTime());

        SQLiteOpenHelper gameDbHelper = new GameDataHelper(getContext()) ;
        SQLiteDatabase db = gameDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _id, NAME, PRICE FROM GAMES WHERE RELEASE_DATE BETWEEN '"+ currentDate2 +"' AND '" + currentDate + "'", null);
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(
                getContext(),
                R.layout.item_section_list,
                cursor,
                new String[]{"NAME", "PRICE", "_id"},
                new int[] {R.id.textViewTitulo, R.id.textViewPrecio, R.id.textViewId},
                0);

        setListAdapter(listAdapter);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_novedades, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Intent intent = new Intent(getActivity(), GameDetail.class);
        SQLiteOpenHelper gameDbHelper = new GameDataHelper(getContext());

        //Obtengo la fecha actual
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = df.format(c);

        //Obtengo la fecha menos 1 mes
        Calendar c2 = Calendar.getInstance();
        c2.add(Calendar.MONTH, -1);
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate2 = df2.format(c2.getTime());
        try
        {
            SQLiteDatabase db = gameDbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(" SELECT _id, NAME, PRICE FROM GAMES WHERE RELEASE_DATE BETWEEN '"+ currentDate2 +"' AND '" + currentDate + "'", null);
            cursor.move(position+1);
            intent.putExtra("GAMEID", cursor.getString(0));
            startActivity(intent);
        }
        catch (Exception e) {
        }
    }
}