package com.example.genshintb.activities;

import android.os.Bundle;

import com.example.genshintb.R;
import com.example.genshintb.model.EquipoModel;
import com.example.genshintb.util.SingletonMap;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.genshintb.activities.ui.equipo.SectionsPagerAdapter;

public class EquipoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);
        EquipoModel equipo = (EquipoModel) SingletonMap.getInstance().get("equipo");
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), equipo);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        SingletonMap.getInstance().put("tab", tabs);
    }


}