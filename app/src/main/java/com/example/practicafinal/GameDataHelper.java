package com.example.practicafinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

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
                + "IMAGE_ID INTEGER, "
                + "PRICE DOUBLE, "
                + "PLATFORM TEXT, "
                + "RELEASE_DATE TEXT, "
                + "OFFER INTEGER DEFAULT 0, "
                + "DESCRIPTION TEXT);");

        addVideogame (db, "GTA V",R.drawable.car_tula_gta_v,14.99, "PS4", "2013-09-13" ,"Grand Theft Auto V es un videojuego de acción-aventura de mundo abierto desarrollado por el estudio Rockstar North y distribuido por Rockstar Games. Fue lanzado el 17 de septiembre de 2013 para las consolas PlayStation 3 y Xbox 360.");
        addVideogame (db, "Death Stranding",R.drawable.car_tula_gta_v,14.99, "PS4", "2013-09-13" ,"Grand Theft Auto V es un videojuego de acción-aventura de mundo abierto desarrollado por el estudio Rockstar North y distribuido por Rockstar Games. Fue lanzado el 17 de septiembre de 2013 para las consolas PlayStation 3 y Xbox 360.");
        addVideogame (db, "Mario Bros",R.drawable.car_tula_gta_v,14.99, "PS4", "2013-09-13" ,"Grand Theft Auto V es un videojuego de acción-aventura de mundo abierto desarrollado por el estudio Rockstar North y distribuido por Rockstar Games. Fue lanzado el 17 de septiembre de 2013 para las consolas PlayStation 3 y Xbox 360.");

    }



    public static void addVideogame (SQLiteDatabase db, String name, int imageID, double price, String platform, String releaseDate, String description)
    {
        ContentValues gamesData = new ContentValues();
        gamesData.put("NAME", name);
        gamesData.put("COMPANY", description);
        gamesData.put("IMAGE_ID", imageID);
        gamesData.put("PRICE", price);
        gamesData.put("PLATFORM", platform);
        gamesData.put("RELEASE_DATE", String.valueOf(releaseDate));
        gamesData.put("DESCRIPTION", description);
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