package com.example.genshintb.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.genshintb.activities.EquipoActivity;
import com.example.genshintb.database.DataAdapter;
import com.example.genshintb.activities.ui.main.EquipoAdapter;
import com.example.genshintb.model.ArmaModel;
import com.example.genshintb.model.ArtefactoModel;
import com.example.genshintb.model.EquipoModel;
import com.example.genshintb.model.PersonajeModel;
import com.example.genshintb.R;
import com.example.genshintb.util.SingletonMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EquiposFragment extends Fragment {

    EquipoAdapter adapter;
    List<EquipoModel> lista = new ArrayList<>();
    DataAdapter data;
    ListView lv;
    boolean allowRefresh = false;

    public static EquiposFragment newInstance() {
        EquiposFragment fragment = new EquiposFragment();
        return fragment;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_equipos, container, false);

        lv = (ListView)view.findViewById(R.id.equipos);
        data = new DataAdapter(getActivity().getApplicationContext());
        viewData();
        adapter = new EquipoAdapter(getActivity(), lista);
        lv.setAdapter(adapter);
        lv.setClickable(true);
        return view;
    }




    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EquipoModel equipo = (EquipoModel)adapter.getItem(position);
                SingletonMap.getInstance().put("equipo", equipo);
                Toast.makeText(getActivity(), Integer.toString(equipo.getID()), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity().getApplicationContext(), EquipoActivity.class);
                startActivity(intent);
            }
        });
    }



    private void viewData(){
        data.open();
        Cursor cursor = data.getAllEquipos();
        if(cursor.moveToFirst()){
            do{
                int equipoID = cursor.getInt(0);
                int char1 = cursor.getInt(1);
                int char2 = cursor.getInt(2);
                int char3 = cursor.getInt(3);
                int char4 = cursor.getInt(4);
                String nombre = cursor.getString(5);

                PersonajeModel per1 = viewPersonaje(char1);
                PersonajeModel per2 = viewPersonaje(char2);
                PersonajeModel per3 = viewPersonaje(char3);
                PersonajeModel per4 = viewPersonaje(char4);

                EquipoModel newEquipo = new EquipoModel(equipoID, per1, per2, per3, per4, nombre);
                lista.add(newEquipo);

            }while(cursor.moveToNext());
        }
        data.close();
    }

    public PersonajeModel viewPersonaje(int id){
        Cursor cursor = data.getPersonaje(id);
        if(cursor.moveToFirst()){
            int personajeID = cursor.getInt(0);
            String personajeName = cursor.getString(1);
            int personajeEstrellas = cursor.getInt(2);
            String personajeElemento = cursor.getString(3);
            String personajeTipoArma = cursor.getString(4);
            ArmaModel personajeArma = viewArma(cursor.getInt(5));
            ArtefactoModel set1 = viewArtefacto(cursor.getInt(6));
            ArtefactoModel set2 = viewArtefacto(cursor.getInt(7));
            String reloj = cursor.getString(8);
            String caliz = cursor.getString(9);
            String diadema = cursor.getString(10);

            return new PersonajeModel(personajeID, personajeName, personajeEstrellas, personajeElemento,
                    personajeTipoArma, personajeArma, set1, set2, reloj, caliz, diadema);
        }else{
            return new PersonajeModel();
        }
    }

    public ArmaModel viewArma(int id){
        Cursor cursor = data.getArma(id);
        if(cursor.moveToFirst()){
            return new ArmaModel(cursor.getInt(0), cursor.getString(1),
                        cursor.getInt(2), cursor.getString(3),
                        cursor.getString(4));
        }else {
            return new ArmaModel();
        }
    }

    public ArtefactoModel viewArtefacto(int id){
        Cursor cursor = data.getArtefacto(id);
        if(cursor.moveToFirst()){
            return new ArtefactoModel(cursor.getInt(0), cursor.getString(1));
        }else {
            return new ArtefactoModel();
        }
    }
}