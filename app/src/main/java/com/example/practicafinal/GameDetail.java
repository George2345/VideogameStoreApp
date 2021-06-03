package com.example.practicafinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class GameDetail extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    int gameId;

    TextView videogameName;
    TextView videogamePrice;
    TextView videogamePlatform;
    TextView videogameReleaseDate;
    TextView videogameCompany;
    TextView videogameDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        //Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                0,
                0
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_view);
        navigationView.setNavigationItemSelectedListener(this);

        videogameName = (TextView) findViewById(R.id.videogameName);
        videogamePrice = (TextView) findViewById(R.id.videogamePrice);
        videogamePlatform = (TextView) findViewById(R.id.videogamePlatform);
        videogameReleaseDate = (TextView) findViewById(R.id.videogameReleaseDate);
        videogameCompany = (TextView) findViewById(R.id.videogameCompany);
        videogameDescription = (TextView) findViewById(R.id.videogameDescription);

        gameId = Integer.valueOf(getIntent().getStringExtra("GAMEID"));
        SQLiteOpenHelper gameDbHelper = new GameDataHelper(this) ;
        try {
            SQLiteDatabase db = gameDbHelper.getReadableDatabase();
            Cursor cursor = db.query("GAMES",
                    new String[]{"_id", "NAME", "IMAGE_ID", "PRICE", "PLATFORM", "RELEASE_DATE", "COMPANY", "DESCRIPTION"},
                    "_id = ?",
                    new String[]{Integer.toString(gameId)},
                    null, null, null);
            cursor.moveToFirst();
            gameId = cursor.getInt(0);
            videogameName.setText(cursor.getString(1));
            ((ImageView)findViewById(R.id.videogameImage)).setImageResource(cursor.getInt(2));
            videogamePrice.setText(cursor.getString(3) + "€");
            videogamePlatform.setText(cursor.getString(4));
            videogameReleaseDate.setText(cursor.getString(5));
            videogameCompany.setText(cursor.getString(6));
            videogameDescription.setText(cursor.getString(7));
        }
        catch (Exception e){
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalle, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.marketCart:
                Intent intentCart = new Intent(this, ShoppingCartActivity.class);
                startActivity(intentCart);
                break;
            case R.id.shareDetails:
                String message = (String) "Nombre: " + videogameName.getText() + "\nPrecio: " + videogamePrice.getText() + "\nPlataforma: " + videogamePlatform.getText() +
                        "\nFecha de lanzamiento: " + videogameReleaseDate.getText() + "\nDesarrollador: " + videogameCompany.getText() + "\nDescripción: " + videogameDescription.getText();
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                intentShare.putExtra(Intent.EXTRA_SUBJECT, "Detalles de " + (String) videogameName.getText());
                intentShare.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(intentShare, "Compartir vía:"));
                break;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent = new Intent(this, SectionActivity.class);
        switch (id)
        {
            case R.id.navigation_home:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
            case R.id.navigation_novedades:
                intent.putExtra("SECTION_ID", 0);
                startActivity(intent);
                break;
            case R.id.navigation_ofertas:
                intent.putExtra("SECTION_ID", 1);
                startActivity(intent);
                break;
            case R.id.navigation_ps4:
                intent.putExtra("SECTION_ID", 2);
                startActivity(intent);
                break;
            case R.id.navigation_xbox:
                intent.putExtra("SECTION_ID", 3);
                startActivity(intent);
                break;
            case R.id.navigation_carrito:
                Intent intentShopCart = new Intent(this, ShoppingCartActivity.class);
                startActivity(intentShopCart);
                break;
            case R.id.navigation_location:
                Intent intentLocation = new Intent(this, LocationActivity.class);
                startActivity(intentLocation);
                break;
            case R.id.navigation_contact:
                Intent intent_contact = new Intent(this, ContactActivity.class);
                startActivity(intent_contact);
                break;
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    //Metodo para el boton de añadir un articulo de la lista desde los detalles
    public void addItemFromDetails(View v){
        ContentValues gameValues = new ContentValues();
        gameValues.put("SHOPPING_CART", 1);
        try
        {
            SQLiteOpenHelper gameDbHelper = new GameDataHelper(this);
            SQLiteDatabase db = gameDbHelper.getReadableDatabase();
            db.update("GAMES",
                    gameValues,
                    "_id = ?",
                    new String[]{Integer.toString(gameId)});
            Toast.makeText(GameDetail.this, "Añadido al carrito", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {}
    }
}