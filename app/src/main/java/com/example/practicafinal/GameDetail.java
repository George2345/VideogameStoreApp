package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class GameDetail extends AppCompatActivity {

    int gameId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        gameId = Integer.valueOf(getIntent().getStringExtra("GAMEID"));
        SQLiteOpenHelper gameDbHelper = new GameDataHelper(this) ;
        try {
            SQLiteDatabase db = gameDbHelper.getReadableDatabase();
            Cursor cursor = db.query("GAMES",
                    new String[]{"_id", "NAME", "DESCRIPTION", "IMAGE_ID", "PRICE"}, //"FAVORITE"
                    "_id = ?",
                    new String[]{Integer.toString(gameId)},
                    null, null, null);
            cursor.moveToFirst();
            gameId = cursor.getInt(0);
            ((TextView)findViewById(R.id.videogameName)).setText(cursor.getString(1));
            ((TextView)findViewById(R.id.videogameDescription)).setText(cursor.getString(2));
            ((ImageView)findViewById(R.id.videogameImage)).setImageResource(cursor.getInt(3));
            ((TextView)findViewById(R.id.videogamePrice)).setText(cursor.getString(4));
            //boolean isChecked = (cursor.getInt(4) == 0) ? false : true;
            //((CheckBox)findViewById(R.id.favoriteCheckbox)).setChecked(isChecked);

        }
        catch (Exception e){
        }

    }
}