package com.example.genshintb.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.genshintb.R;
import com.example.genshintb.database.DataAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;


import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.genshintb.activities.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private String m_Text;
    DataAdapter data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new DataAdapter(getApplicationContext());
        data.createDatabase();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);




    }

    @Override
    public void onRestart() {
        super.onRestart();
        recreate();

    }

    public void fabOnClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        builder.setTitle(R.string.nuevoEquipo);


        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                dialog.dismiss();
                /*Toast.makeText(getApplicationContext(), m_Text, Toast.LENGTH_SHORT).show();*/
                data.openWritable();
                data.crearEquipo(m_Text);
                data.close();
                String mensaje = getResources().getString(R.string.equipo) + " " + m_Text + " " + getResources().getString(R.string.equipoCreado);
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                recreate();
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }


}