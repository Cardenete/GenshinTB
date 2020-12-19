package com.example.genshintb.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.genshintb.activities.EquipoActivity;
import com.example.genshintb.activities.PersonajeActivity;
import com.example.genshintb.activities.ui.main.PersonajeAdapter;
import com.example.genshintb.database.DataAdapter;
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

public class PersonajesFragment extends Fragment {

    PersonajeAdapter adapter;
    List<PersonajeModel> lista = new ArrayList<>();
    DataAdapter data;
    ListView lv;
    Button filtrar;

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
        filtrar = view.findViewById(R.id.filtroPersonajes);
        adapter = new PersonajeAdapter(getActivity(), lista);
        lv.setAdapter(adapter);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PersonajeModel personaje = (PersonajeModel)adapter.getItem(position);
                SingletonMap.getInstance().put("personaje", personaje);
                Intent intent = new Intent(getActivity().getApplicationContext(), PersonajeActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.filtroPersonajes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.layout.filtro);
                builder.setTitle(R.string.filtrado);
                /*final EditText nombrePersonaje = new EditText(getActivity());
                nombrePersonaje.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(nombrePersonaje);*/
                View mview = getLayoutInflater().inflate(R.layout.filtro, null);
                final EditText nom = (EditText)mview.findViewById(R.id.nombreFiltroEdit);
                final Spinner ele = mview.findViewById(R.id.elementoFiltroEdit);
                ArrayAdapter<String> filtroAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.listaElementos));
                ele.setAdapter(filtroAdapter);
                final Spinner est = mview.findViewById(R.id.estrellasFiltroEdit);
                filtroAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.listaEstrellas));
                est.setAdapter(filtroAdapter);
                final Spinner tip = mview.findViewById(R.id.tipoFiltroEdit);
                filtroAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.listaTipo));
                tip.setAdapter(filtroAdapter);

                builder.setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        /*if(nom == null || nom.equals(""))

                        /*Toast.makeText(getApplicationContext(), m_Text, Toast.LENGTH_SHORT).show();*/
                        /*data.openWritable();
                        data.crearEquipo(m_Text);
                        data.close();
                        String mensaje = m_Text + " " + getResources().getString(R.string.equipoCreado);
                        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                        recreate();*/
                        data.open();
                        lista = new ArrayList<>();
                        actualizarLista(data.filtrarPersonajes(nom.getText().toString(), (String)ele.getSelectedItem(), (String)est.getSelectedItem(), (String)tip.getSelectedItem()));
                        data.close();
                        adapter = new PersonajeAdapter(getActivity(), lista);
                        lv.setAdapter(adapter);

                    }
                });
                builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setView(mview);
                builder.show();
            }
        });

    }

    private void viewData(){
        data.open();
        Cursor cursor = data.getAllPersonajes();
        actualizarLista(cursor);
        data.close();
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

    private void actualizarLista(Cursor cursor){
        if(cursor.moveToFirst()){
            do{
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

                PersonajeModel newPersonaje = new PersonajeModel(personajeID, personajeName, personajeEstrellas, personajeElemento,
                        personajeTipoArma, personajeArma, set1, set2, reloj, caliz, diadema);

                lista.add(newPersonaje);

            }while(cursor.moveToNext());
        }
    }
}