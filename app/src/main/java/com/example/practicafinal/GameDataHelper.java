package com.example.practicafinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GameDataHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "gamesdatabase";
    private static final int DBVERSION = 2;


    public GameDataHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE GAMES ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COMPANY TEXT, "
                + "IMAGE_ID INTEGER, "
                + "FAVORITE INTEGER); ");

        addVideogame (db, "OUTCAST","APPEAL", R.drawable.ic_launcher_background);
        addVideogame (db, "SHENMUE","SEGA", R.drawable.ic_launcher_background);
        addVideogame (db, "NINOKUNI II","LEVEL5", R.drawable.ic_launcher_background);
    }



    public static void addVideogame (SQLiteDatabase db, String name, String company, int imageID)
    {
        ContentValues gamesData = new ContentValues();
        gamesData.put("NAME", name);
        gamesData.put("COMPANY", company);
        gamesData.put("IMAGE_ID", imageID);
        db.insert("GAMES", null, gamesData);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (oldVersion == 1 && newVersion == 2)
        {
            db.execSQL("ALTER TABLE GAMES ADD COLUMN FAVORITE BIT DEFAULT 0");
        }
    }
}