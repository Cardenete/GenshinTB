package com.example.genshintb.fragments;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.genshintb.R;
import com.example.genshintb.activities.PersonajeActivity;
import com.example.genshintb.activities.ui.main.EquipoAdapter;
import com.example.genshintb.database.DataAdapter;
import com.example.genshintb.model.ArmaModel;
import com.example.genshintb.model.ArtefactoModel;
import com.example.genshintb.model.EquipoModel;
import com.example.genshintb.model.PersonajeModel;
import com.example.genshintb.util.SingletonMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MiembroFragment extends Fragment {

    private PersonajeModel personaje;
    Cursor cursor;
    DataAdapter data;
    ImageView elemento;
    ImageView foto;
    TextView estrellas;
    TextView nombre;
    EquipoModel equipo;
    List<PersonajeModel> listaEquipo;
    List<PersonajeModel> listaPersonajes = new ArrayList<>();

    public MiembroFragment(PersonajeModel personaje) {
        this.personaje = personaje;
    }

    public static MiembroFragment newInstance(PersonajeModel personaje) {
        MiembroFragment fragment = new MiembroFragment(personaje);
        return fragment;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_miembro, container, false);

        cargaPersonaje(view);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Ver como es boton
        view.findViewById(R.id.detalles).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingletonMap.getInstance().put("personaje", personaje);
                Intent intent = new Intent(getActivity().getApplicationContext(), PersonajeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void cargaPersonaje(View view){
        nombre = (TextView) view.findViewById(R.id.nombreMiembro);
        nombre.setText(personaje.getNombre());

        estrellas = (TextView) view.findViewById(R.id.estrellasMiembro);
        estrellas.setText(Integer.toString(personaje.getEstrellas()));

        elemento = (ImageView) view.findViewById(R.id.fotoElemento);
        elemento.setImageBitmap(getBitmapFromAssets("elementos/" + personaje.getElemento().toLowerCase()));

        foto = (ImageView) view.findViewById(R.id.fotoPersonaje);
        foto.setImageBitmap(getBitmapFromAssets("personajes/" + personaje.getNombre().toLowerCase()));


        equipo = (EquipoModel) SingletonMap.getInstance().get("equipo");
        listaEquipo = equipo.listaMiembros();
        //listaPersonajes.add(personaje);
        data = new DataAdapter(getActivity().getApplicationContext());

        data.open();
        cursor = data.getAllPersonajes();

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

                if(!listaEquipo.contains(newPersonaje)) {

                    listaPersonajes.add(newPersonaje);
                }

            }while(cursor.moveToNext());
        }

        data.close();
    }


    private ArmaModel viewArma(int id){
        Cursor cursor = data.getArma(id);
        if(cursor.moveToFirst()){
            return new ArmaModel(cursor.getInt(0), cursor.getString(1),
                    cursor.getInt(2), cursor.getString(3),
                    cursor.getString(4));
        }else {
            return new ArmaModel();
        }
    }

    private ArtefactoModel viewArtefacto(int id){
        Cursor cursor = data.getArtefacto(id);
        if(cursor.moveToFirst()){
            return new ArtefactoModel(cursor.getInt(0), cursor.getString(1));
        }else {
            return new ArtefactoModel();
        }
    }

    private Bitmap getBitmapFromAssets(String fileName){

        AssetManager am = getActivity().getAssets();
        InputStream is = null;
        try{
            is=am.open(fileName + ".png");
            Log.i("gf", is.toString());
        }catch (IOException e){
            e.printStackTrace();
        }

        return BitmapFactory.decodeStream(is);
    }



}
