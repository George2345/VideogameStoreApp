package com.example.practicafinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class SectionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CategoriesAdapter m_adapter;
    ViewPager m_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        //Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Adapter
        m_adapter = new CategoriesAdapter(getSupportFragmentManager(), this);
        m_pager   = findViewById(R.id.viewpager);
        m_pager.setAdapter(m_adapter);

        //TabLayout
        TabLayout tab = (TabLayout) findViewById(R.id.tablayout);
        tab.setupWithViewPager(m_pager);

        //Recibir datos de intent
        int sectionID = (int) getIntent().getExtras().get("SECTION_ID");
        m_pager.setCurrentItem(sectionID);
        m_adapter.notifyDataSetChanged();

        //DrawerLayout
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

        //NavigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.marketCart:
                Intent intent = new Intent(this, ShoppingCartActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed()
    {
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        int id = menuItem.getItemId();
        switch (id)
        {
            case R.id.navigation_home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.navigation_novedades :
                Log.d("MENUNAVIGATIONDRAWER", "Novedades");
                m_pager.setCurrentItem(0);
                m_adapter.notifyDataSetChanged();
                break;
            case R.id.navigation_ofertas :
                Log.d("MENUNAVIGATIONDRAWER", "Ofertas");
                m_pager.setCurrentItem(1);
                m_adapter.notifyDataSetChanged();
                break;
            case R.id.navigation_ps4 :
                Log.d("MENUNAVIGATIONDRAWER", "PS4");
                m_pager.setCurrentItem(2);
                m_adapter.notifyDataSetChanged();
                break;
            case R.id.navigation_xbox :
                Log.d("MENUNAVIGATIONDRAWER", "XBOX");
                m_pager.setCurrentItem(3);
                m_adapter.notifyDataSetChanged();
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


    public void addItemFromSection(View v){
        TextView textView = (TextView) v.findViewById(R.id.textViewId);
        String id = textView.getText().toString();

        ContentValues gameValues = new ContentValues();
        gameValues.put("SHOPPING_CART", 1);
        try
        {
            SQLiteOpenHelper gameDbHelper = new GameDataHelper(this);
            SQLiteDatabase db = gameDbHelper.getReadableDatabase();
            db.update("GAMES",
                    gameValues,
                    "_id = ?",
                    new String[]{id});
            Toast.makeText(SectionActivity.this, "Añadido al carrito", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {}
    }
}