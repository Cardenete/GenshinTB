package com.example.genshintb.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.genshintb.database.DataAdapter;
import com.example.genshintb.activities.ui.main.EquipoAdapter;
import com.example.genshintb.model.EquipoModel;
import com.example.genshintb.model.PersonajeModel;
import com.example.genshintb.R;

import java.util.ArrayList;
import java.util.List;

public class EquiposFragment extends Fragment {

    EquipoAdapter adapter;
    List<EquipoModel> lista = new ArrayList<>();
    DataAdapter data;
    ListView lv;

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

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Ver como es boton
        /*view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PersonajesFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });*/
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

                PersonajeModel per1 = viewPersonaje(char1);
                PersonajeModel per2 = viewPersonaje(char2);
                PersonajeModel per3 = viewPersonaje(char3);
                PersonajeModel per4 = viewPersonaje(char4);

                EquipoModel newEquipo = new EquipoModel(equipoID, per1, per2, per3, per4);
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
            String personajeImagen = cursor.getString(5);

            return new PersonajeModel(personajeID, personajeName, personajeEstrellas, personajeElemento,
                    personajeTipoArma, personajeImagen);
        }else{
            return new PersonajeModel();
        }
    }
}