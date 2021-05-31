package com.example.practicafinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class GameDataHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "gamesdatabase";
    private static final int DBVERSION = 1;


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
                + "COMPANY TEXT, "
                + "OFFER INTEGER DEFAULT 0, "
                + "SHOPPING_CART INTEGER DEFAULT 0,"
                + "DESCRIPTION TEXT);");


        addVideogame (db, "GTA V",R.drawable.car_tula_gta_v,14.99, "PS4", "2013-09-13", "Rockstar Games","Grand Theft Auto V es un videojuego de acción-aventura de mundo abierto desarrollado por el estudio Rockstar North y distribuido por Rockstar Games. Fue lanzado el 17 de septiembre de 2013 para las consolas PlayStation 3 y Xbox 360.");
        addVideogame (db, "Death Stranding",R.drawable.deathstranding,20.99, "PS4", "2019-11-08" , "Kojima Productions" ,"Death Stranding es un videojuego de acción y exploración en mundo abierto desarrollado por Kojima Productions y publicado por Sony Interactive Entertainment para PlayStation 4 y por 505 Games para Microsoft Windows.");
        addVideogame (db, "Resident Evil VIII",R.drawable.residentevil8,38.99, "PS4", "2021-04-18" , "Capcom","Resident Evil Village es un videojuego perteneciente al género de horror de supervivencia desarrollado y publicado por Capcom. la novena entrega de la serie principal de Resident Evil y secuela narrativa de Resident Evil 7");
        addVideogame (db, "Spider-Man",R.drawable.spiderman,19.99, "PS4", "2018-09-07", "Insomniac Games","Marvel's Spider-Man es un videojuego de acción y aventura basado en el popular superhéroe hómonimo de la editorial Marvel Comics. Fue desarrollado por Insomniac Games y publicado por Sony Interactive Entertainment en exclusiva para la consola PlayStation 4.");
        addVideogame (db, "Horizon Zero Dawn",R.drawable.horizonzerodawn,24.99, "PS4", "2017-02-28" , "Guerrilla Games" ,"Horizon Zero Dawn es un videojuego de acción, aventura y de mundo abierto desarrollado por Guerrilla Games y distribuido por Sony Interactive Entertainment para PlayStation 4. Es la primera propiedad intelectual de Guerrilla Games desde la serie Killzone en 2004 y se anunció por primera vez en el E3 2015.");
        addVideogame (db, "Fifa 21",R.drawable.fifa,21.50, "PS4", "2020-10-05" , "Electronic Arts","FIFA 21 es un videojuego de fútbol del año 2020 disponible para Microsoft Windows, PlayStation 4, Xbox One y Nintendo Switch el 9 de octubre de 2020, y también es el primer videojuego de la serie FIFA para PlayStation 5 y Xbox Series X|S. El juego es la 28.ª entrega de la serie de videojuegos de FIFA.");

        addVideogame (db, "GTA V",R.drawable.car_tula_gta_v,14.99, "PS4", "2013-09-13", "Rockstar Games","Grand Theft Auto V es un videojuego de acción-aventura de mundo abierto desarrollado por el estudio Rockstar North y distribuido por Rockstar Games. Fue lanzado el 17 de septiembre de 2013 para las consolas PlayStation 3 y Xbox 360.");
        addVideogame (db, "Death Stranding",R.drawable.car_tula_gta_v,20.99, "PS4", "2013-09-13" , "Kojima Productions" ,"Grand Theft Auto V es un videojuego de acción-aventura de mundo abierto desarrollado por el estudio Rockstar North y distribuido por Rockstar Games. Fue lanzado el 17 de septiembre de 2013 para las consolas PlayStation 3 y Xbox 360.");
        addVideogame (db, "Resident Evil VIII",R.drawable.car_tula_gta_v,38.99, "PS4", "2013-09-13" , "Capcom","Grand Theft Auto V es un videojuego de acción-aventura de mundo abierto desarrollado por el estudio Rockstar North y distribuido por Rockstar Games. Fue lanzado el 17 de septiembre de 2013 para las consolas PlayStation 3 y Xbox 360.");
        addVideogame (db, "GTA V",R.drawable.car_tula_gta_v,14.99, "PS4", "2013-09-13", "Rockstar Games","Grand Theft Auto V es un videojuego de acción-aventura de mundo abierto desarrollado por el estudio Rockstar North y distribuido por Rockstar Games. Fue lanzado el 17 de septiembre de 2013 para las consolas PlayStation 3 y Xbox 360.");
        addVideogame (db, "Death Stranding",R.drawable.car_tula_gta_v,20.99, "PS4", "2013-09-13" , "Kojima Productions" ,"Grand Theft Auto V es un videojuego de acción-aventura de mundo abierto desarrollado por el estudio Rockstar North y distribuido por Rockstar Games. Fue lanzado el 17 de septiembre de 2013 para las consolas PlayStation 3 y Xbox 360.");
        addVideogame (db, "Resident Evil VIII",R.drawable.car_tula_gta_v,38.99, "PS4", "2013-09-13" , "Capcom","Grand Theft Auto V es un videojuego de acción-aventura de mundo abierto desarrollado por el estudio Rockstar North y distribuido por Rockstar Games. Fue lanzado el 17 de septiembre de 2013 para las consolas PlayStation 3 y Xbox 360.");


    }



    public static void addVideogame (SQLiteDatabase db, String name, int imageID, double price, String platform, String releaseDate, String company , String description)
    {
        ContentValues gamesData = new ContentValues();
        gamesData.put("NAME", name);
        gamesData.put("IMAGE_ID", imageID);
        gamesData.put("PRICE", price);
        gamesData.put("PLATFORM", platform);
        gamesData.put("RELEASE_DATE", releaseDate);
        gamesData.put("COMPANY", company);
        gamesData.put("DESCRIPTION", description);
        db.insert("GAMES", null, gamesData);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (oldVersion == 1 && newVersion == 2)
        {
            //db.execSQL("ALTER TABLE GAMES ADD COLUMN FAVORITE BIT DEFAULT 0");
        }
    }
}