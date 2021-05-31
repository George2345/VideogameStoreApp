package com.example.practicafinal;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoriesAdapter extends FragmentPagerAdapter {

    Context m_context;

    public CategoriesAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
    }

    public CategoriesAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        m_context = context;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return m_context.getResources().getText(R.string.novedades_tab);
            case 1:
                return m_context.getResources().getText(R.string.ofertas_tab);
            case 2:
                return m_context.getResources().getText(R.string.ps4_tab);
            case 3:
                return m_context.getResources().getText(R.string.xbox_tab);
        }
        return null;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new NovedadesFragment();
            case 1:
                return new OfertasFragment();
            case 2:
                return new PS4Fragment();
            case 3:
                return new XboxFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}