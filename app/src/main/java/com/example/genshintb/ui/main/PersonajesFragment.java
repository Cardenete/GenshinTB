package com.example.genshintb.ui.main;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.genshintb.DataAdapter;
import com.example.genshintb.PersonajeModel;
import com.example.genshintb.R;

import java.util.ArrayList;
import java.util.List;

public class PersonajesFragment extends ListFragment {

    ArrayAdapter adapter;
    List<PersonajeModel> lista = new ArrayList<>();
    DataAdapter data;

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

        data = new DataAdapter(getActivity().getApplicationContext());
        viewData();
        adapter = new ArrayAdapter<PersonajeModel>(getActivity(), android.R.layout.simple_list_item_1, lista);
        setListAdapter(adapter);

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