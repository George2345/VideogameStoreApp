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
                + "RELEASE_DATE DATE, "
                + "COMPANY TEXT, "
                + "OFFER INTEGER DEFAULT 0, "
                + "SHOPPING_CART INTEGER DEFAULT 0,"
                + "DESCRIPTION TEXT);");


        addVideogame (db, "GTA V",R.drawable.car_tula_gta_v,14.99, "PS4", "2013-09-13", "Rockstar Games","Grand Theft Auto V es un videojuego de acción-aventura de mundo abierto desarrollado por el estudio Rockstar North y distribuido por Rockstar Games. Fue lanzado el 17 de septiembre de 2013 para las consolas PlayStation 3 y Xbox 360.", 1);
        addVideogame (db, "Death Stranding",R.drawable.deathstranding,20.99, "PS4", "2019-11-08" , "Kojima Productions" ,"Death Stranding es un videojuego de acción y exploración en mundo abierto desarrollado por Kojima Productions y publicado por Sony Interactive Entertainment para PlayStation 4 y por 505 Games para Microsoft Windows.", 0);
        addVideogame (db, "Resident Evil VIII",R.drawable.residentevil8,38.99, "PS4", "2021-04-18" , "Capcom","Resident Evil Village es un videojuego perteneciente al género de horror de supervivencia desarrollado y publicado por Capcom. la novena entrega de la serie principal de Resident Evil y secuela narrativa de Resident Evil 7", 1);
        addVideogame (db, "Spider-Man",R.drawable.spiderman,19.99, "PS4", "2018-09-07", "Insomniac Games","Marvel's Spider-Man es un videojuego de acción y aventura basado en el popular superhéroe hómonimo de la editorial Marvel Comics. Fue desarrollado por Insomniac Games y publicado por Sony Interactive Entertainment en exclusiva para la consola PlayStation 4.", 0);
        addVideogame (db, "Horizon Zero Dawn",R.drawable.horizonzerodawn,24.99, "PS4", "2017-02-28" , "Guerrilla Games" ,"Horizon Zero Dawn es un videojuego de acción, aventura y de mundo abierto desarrollado por Guerrilla Games y distribuido por Sony Interactive Entertainment para PlayStation 4. Es la primera propiedad intelectual de Guerrilla Games desde la serie Killzone en 2004 y se anunció por primera vez en el E3 2015.", 0);
        addVideogame (db, "Fifa 21",R.drawable.fifa,21.55, "PS4", "2020-10-05" , "Electronic Arts","FIFA 21 es un videojuego de fútbol del año 2020 disponible para Microsoft Windows, PlayStation 4, Xbox One y Nintendo Switch el 9 de octubre de 2020, y también es el primer videojuego de la serie FIFA para PlayStation 5 y Xbox Series X|S. El juego es la 28.ª entrega de la serie de videojuegos de FIFA.", 0);

        addVideogame (db, "Assassin's Creed Valhalla",R.drawable.assasinscreed_cover,35.99, "Xbox", "2020-11-10", "Ubisoft Montreal","Assassin's Creed Valhalla es un videojuego desarrollado por Ubisoft Montreal y publicado por Ubisoft. Es el decimosegundo en importancia y el vigesimosegundo lanzado dentro de la saga de Assassin Creed, y sucesor al juego del 2018 Assassin's Creed Odyssey.", 0);
        addVideogame (db, "Biomutant",R.drawable.biomutant_cover,59.99, "Xbox", "2021-05-25" , "Experiment 101" ,"Biomutant es un videojuego perteneciente al género de acción y rol desarrollado por el estudio sueco desarrollador Experiment 101, y publicado por la empresa THQ Nordic. Se lanzó el 25 de mayo de 2021 para Microsoft Windows, PlayStation 4 y Xbox One.", 0);
        addVideogame (db, "Call of Duty WWII",R.drawable.callofdutywwii_cover,34.99, "Xbox", "2017-11-03" , "Sledgehammer Games","Call of Duty: WWII es un videojuego de disparos en primera persona desarrollado por Sledgehammer Games, distribuido por Activision, perteneciente a la franquicia Call of Duty y es la décimo cuarta entrega de la saga.", 0);
        addVideogame (db, "Plants vs. Zombies: Garden Warfare",R.drawable.plantsvs_zombies_gardenwarfare2_cover,9.99, "Xbox", "2014-02-25", "PopCap Games","Plants vs. Zombies: Garden Warfare es un videojuego cooperativo y shooter de disparos en tercera persona perteneciente a la franquicia de Plants vs. Zombies desarrollado por PopCap Games y distribuido por Electronic Arts. ", 1);
        addVideogame (db, "Red Dead Redemption 2: Special Edition",R.drawable.reddeadredemption2_cover,34.99, "Xbox", "2018-10-26" , "Rockstar Games" ,"Red Dead Redemption 2 es un videojuego de acción-aventura western, en un mundo abierto y en perspectiva de primera y tercera persona, con componentes para un jugador y multijugador. Fue desarrollado por Rockstar Games. Es la precuela de Red Dead Redemption y el tercer juego de la saga Red Dead.", 0);
        addVideogame (db, "Sea of Thieves",R.drawable.seaofthieves_cover,19.03, "Xbox", "2018-03-20" , "Rare","Sea of Thieves es un videojuego de acción-aventura en primera persona desarrollado por Rare y distribuido por Xbox Game Studios, para las plataformas Xbox One y Microsoft Windows. El videojuego fue lanzado a la venta el 20 de marzo de 2018.", 1);

    }



    public static void addVideogame (SQLiteDatabase db, String name, int imageID, double price, String platform, String releaseDate, String company , String description, int offer)
    {
        ContentValues gamesData = new ContentValues();
        gamesData.put("NAME", name);
        gamesData.put("IMAGE_ID", imageID);
        gamesData.put("PRICE", price);
        gamesData.put("PLATFORM", platform);
        gamesData.put("RELEASE_DATE", releaseDate);
        gamesData.put("COMPANY", company);
        gamesData.put("DESCRIPTION", description);
        gamesData.put("OFFER", offer);
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