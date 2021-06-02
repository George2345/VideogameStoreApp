package com.example.practicafinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.os.Bundle;

import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ShoppingCartFragment extends ListFragment {

    private static String precioTotal;

    public ShoppingCartFragment() {
        // Required empty public constructor
    }

    public static String getPrecioTotal(){
        return precioTotal;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ps4games));
        setListAdapter(adapter);*/
        SQLiteOpenHelper gameDbHelper = new GameDataHelper(getContext()) ;
        SQLiteDatabase db = gameDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _id, NAME, PRICE FROM GAMES WHERE SHOPPING_CART=1", null);
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(
                getContext(),
                R.layout.item_shopingcart_list,
                cursor,
                new String[]{"NAME", "PRICE", "_id"},
                new int[] {R.id.textViewTitulo, R.id.textViewPrecio, R.id.textViewId},
                0);

        setListAdapter(listAdapter);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null){
            //Obtengo el precio total de los juegos añadidos al carrito
            SQLiteOpenHelper gameDbHelper = new GameDataHelper(getContext()) ;
            SQLiteDatabase db = gameDbHelper.getReadableDatabase();
            Cursor cursor2 = db.rawQuery(" SELECT SUM(PRICE) FROM GAMES WHERE SHOPPING_CART=1", null);
            cursor2.moveToFirst();
            precioTotal = cursor2.getString(0);
            ((TextView)view.findViewById(R.id.textViewPrecioTotal)).setText(precioTotal);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Intent intent = new Intent(getActivity(), GameDetail.class);
        SQLiteOpenHelper gameDbHelper = new GameDataHelper(getContext()) ;
        try
        {
            SQLiteDatabase db = gameDbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(" SELECT _id FROM GAMES WHERE SHOPPING_CART=1", null);
            cursor.move(position+1);
            intent.putExtra("GAMEID", cursor.getString(0));
            startActivity(intent);
        }
        catch (Exception e) {
        }
    }
}