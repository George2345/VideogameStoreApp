package com.example.practicafinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ShoppingCartActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent = new Intent(this, SectionActivity.class);
        switch (id)
        {
            case R.id.navigation_home:
                Intent intentHome = new Intent(this, MainActivity.class);
                startActivity(intentHome);
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


    public void finalizePurchase(View v){
        Intent intent = new Intent(ShoppingCartActivity.this, FinalizarCompraActivity.class);
        String precioTotal = ShoppingCartFragment.getPrecioTotal();
        intent.putExtra("PRECIO_TOTAL", precioTotal);
        startActivity(intent);
    }

    //Metodo para el boton de eliminar un articulo de la lista
    public void deleteItem(View v){
        TextView textView = (TextView) v.findViewById(R.id.textViewId);
        String id = textView.getText().toString();

        ContentValues gameValues = new ContentValues();
        gameValues.put("SHOPPING_CART", 0);
        try
        {
            SQLiteOpenHelper gameDbHelper = new GameDataHelper(this);
            SQLiteDatabase db = gameDbHelper.getReadableDatabase();
            db.update("GAMES",
                    gameValues,
                    "_id = ?",
                    new String[]{id});
            Toast.makeText(ShoppingCartActivity.this, "Eliminado del carrito", Toast.LENGTH_SHORT).show();

            //Recargar actividad
            finish();
            startActivity(getIntent());
        }
        catch (Exception e) {}
    }
}