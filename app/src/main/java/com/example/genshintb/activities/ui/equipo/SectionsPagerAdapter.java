package com.example.genshintb.activities.ui.equipo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.genshintb.R;
import com.example.genshintb.fragments.EquiposFragment;
import com.example.genshintb.fragments.MiembroFragment;
import com.example.genshintb.fragments.PersonajesFragment;
import com.example.genshintb.model.EquipoModel;
import com.example.genshintb.model.PersonajeModel;

import java.util.ArrayList;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    private static String[] titulos;
    private ArrayList<PersonajeModel> lista = new ArrayList<>();
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm, EquipoModel equipo) {
        super(fm);
        mContext = context;
        lista.add(equipo.getChar1());
        lista.add(equipo.getChar2());
        lista.add(equipo.getChar3());
        lista.add(equipo.getChar4());
        titulos = new String[]{equipo.getChar1().getNombre(), equipo.getChar2().getNombre(), equipo.getChar3().getNombre(), equipo.getChar4().getNombre()};

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.

        return MiembroFragment.newInstance(lista.get(position));


    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titulos[position];
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }
}