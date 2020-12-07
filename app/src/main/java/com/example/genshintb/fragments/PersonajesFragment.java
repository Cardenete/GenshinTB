package com.example.genshintb.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.genshintb.database.DataAdapter;
import com.example.genshintb.model.EquipoModel;
import com.example.genshintb.model.PersonajeModel;
import com.example.genshintb.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PersonajesFragment extends Fragment {

    ArrayAdapter adapter;
    List<PersonajeModel> lista = new ArrayList<>();
    DataAdapter data;
    ListView lv;

    public static PersonajesFragment newInstance() {
        PersonajesFragment fragment = new PersonajesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_personaje, container, false);
        lv = (ListView)view.findViewById(R.id.lv_personajes);
        data = new DataAdapter(getActivity().getApplicationContext());
        viewData();
        adapter = new ArrayAdapter<PersonajeModel>(getActivity(), android.R.layout.simple_list_item_1, lista);
        lv.setAdapter(adapter);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PersonajeModel personaje = (PersonajeModel)adapter.getItem(position);

                Toast.makeText(getActivity(), personaje.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void viewData(){
        data.open();
        Cursor cursor = data.getAllPersonajes();
        if(cursor.moveToFirst()){
            do{
                int personajeID = cursor.getInt(0);
                String personajeName = cursor.getString(1);
                int personajeEstrellas = cursor.getInt(2);
                String personajeElemento = cursor.getString(3);
                String personajeTipoArma = cursor.getString(4);
                String personajeImagen = cursor.getString(5);

                PersonajeModel newPersonaje = new PersonajeModel(personajeID, personajeName, personajeEstrellas, personajeElemento,
                        personajeTipoArma, personajeImagen);

                lista.add(newPersonaje);

            }while(cursor.moveToNext());
        }
        data.close();
    }
}