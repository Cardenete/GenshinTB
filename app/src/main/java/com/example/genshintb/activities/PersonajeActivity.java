package com.example.genshintb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.genshintb.R;
import com.example.genshintb.activities.ui.personaje.ArmaAdapter;
import com.example.genshintb.activities.ui.personaje.ArtefactoAdapter;
import com.example.genshintb.database.DataAdapter;
import com.example.genshintb.model.ArmaModel;
import com.example.genshintb.model.ArtefactoModel;
import com.example.genshintb.model.PersonajeModel;
import com.example.genshintb.util.SingletonMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonajeActivity extends AppCompatActivity {

    PersonajeModel personaje;
    TextView nombre;
    TextView elemento;
    TextView tipoArma;
    TextView estrellas;
    ImageView retrato;
    ImageView iconoElemento;
    Spinner cambioArma;
    Spinner cambioSet1;
    Spinner cambioSet2;
    Spinner reloj;
    Spinner caliz;
    Spinner diadema;

    DataAdapter data;
    List<ArmaModel> listaArmas;
    List<ArtefactoModel> listaArtefactos;
    ArmaAdapter armaAdapter;
    ArtefactoAdapter artefactoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje);

        data = new DataAdapter(getApplicationContext());
        listaArmas = new ArrayList<>();
        listaArtefactos = new ArrayList<>();

        personaje = (PersonajeModel) SingletonMap.getInstance().get("personaje");
        getArmasPersonaje(personaje.getTipoArma());
        getArtefactosPersonaje(listaArtefactos);
        armaAdapter = new ArmaAdapter(PersonajeActivity.this, listaArmas);
        artefactoAdapter = new ArtefactoAdapter(PersonajeActivity.this, listaArtefactos);

        nombre = (TextView)findViewById(R.id.nombrePersonaje);
        elemento = (TextView)findViewById(R.id.elementoPersonaje);
        retrato = (ImageView) findViewById(R.id.imagenPersonaje);
        iconoElemento = (ImageView) findViewById(R.id.elemetoPersonajeIcon);
        tipoArma = (TextView)findViewById(R.id.tipoArmaPersonaje);
        estrellas = (TextView)findViewById(R.id.numEstrellas);
        cambioArma = (Spinner)findViewById(R.id.listaCambioArma);
        cambioSet1 = (Spinner)findViewById(R.id.listaCambioSet1);
        cambioSet2 = (Spinner)findViewById(R.id.listaCambioSet2);
        reloj = (Spinner) findViewById(R.id.listaRelojPersonaje);
        caliz = (Spinner) findViewById(R.id.listaCalizPersonaje);
        diadema = (Spinner) findViewById(R.id.listaDiademaPersonaje);


        nombre.setText(personaje.getNombre());
        elemento.setText(personaje.getElemento());
        retrato.setImageBitmap(getBitmapFromAssets("personajes/" + personaje.getNombre().toLowerCase()));
        iconoElemento.setImageBitmap(getBitmapFromAssets("elementos/" + personaje.getElemento().toLowerCase()));
        tipoArma.setText(personaje.getTipoArma());
        estrellas.setText(Integer.toString(personaje.getEstrellas()));
        cambioArma.setAdapter(armaAdapter);
        cambioArma.setSelection(personaje.getArma().getID());
        cambioSet1.setAdapter(artefactoAdapter);
        cambioSet1.setSelection(personaje.getSet1().getID());
        cambioSet2.setAdapter(artefactoAdapter);
        cambioSet2.setSelection(personaje.getSet2().getID());




    }

    private Bitmap getBitmapFromAssets(String fileName){

        AssetManager am = getAssets();
        InputStream is = null;
        try{
            is=am.open(fileName + ".png");
            Log.i("gf", fileName);
        }catch (IOException e){
            e.printStackTrace();
        }

        return BitmapFactory.decodeStream(is);
    }

    private void getArmasPersonaje(String tipo){
        data.open();
        Cursor cursor = data.getArmasPorTipo(tipo);
        if(cursor.moveToFirst()){
            do{
                int armaID = cursor.getInt(0);
                String nombre = cursor.getString(1);
                int estrellas = cursor.getInt(2);
                String stat = cursor.getString(3);
                String tipoArma = cursor.getString(4);

                ArmaModel newArma = new ArmaModel(armaID, nombre, estrellas, stat, tipoArma);
                listaArmas.add(newArma);

            }while(cursor.moveToNext());
        }
        data.close();
    }

    private void getArtefactosPersonaje(List<ArtefactoModel> lista){
        data.open();
        Cursor cursor = data.getArtefactosAll();
        if(cursor.moveToFirst()){
            do{
                int artefactoID = cursor.getInt(0);
                String nombre = cursor.getString(1);

                ArtefactoModel newArtefacto = new ArtefactoModel(artefactoID, nombre);
                lista.add(newArtefacto);

            }while(cursor.moveToNext());
        }
    }
}