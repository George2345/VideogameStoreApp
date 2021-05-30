package com.example.practicafinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class SectionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CategoriesAdapter m_adapter;
    ViewPager m_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        m_adapter = new CategoriesAdapter(getSupportFragmentManager(), this);
        m_pager   = findViewById(R.id.viewpager);
        m_pager.setAdapter(m_adapter);

        TabLayout tab = (TabLayout) findViewById(R.id.tablayout);
        tab.setupWithViewPager(m_pager);

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
        /*switch (id)
        {
            case R.id.navigation_novedades :
                Log.d("MENUNAVIGATIONDRAWER", "Novedades");
                m_pager.setCurrentItem(0);
                m_adapter.notifyDataSetChanged();
                break;
            case R.id.navigation_ofertas :
                Log.d("MENUNAVIGATIONDRAWER", "Ofertas");
                m_pager.setCurrentItem(3);
                m_adapter.notifyDataSetChanged();
                break;
            case R.id.navigation_ps4 :
                Log.d("MENUNAVIGATIONDRAWER", "PS4");
                m_pager.setCurrentItem(1);
                m_adapter.notifyDataSetChanged();
                break;
            case R.id.navigation_xbox :
                Log.d("MENUNAVIGATIONDRAWER", "XBOX");
                m_pager.setCurrentItem(2);
                m_adapter.notifyDataSetChanged();
                break;
        }*/
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}