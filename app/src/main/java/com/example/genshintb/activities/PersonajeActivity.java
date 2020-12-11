package com.example.genshintb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    Button bArma;

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
        nombre.setText(personaje.getNombre());

        elemento = (TextView)findViewById(R.id.elementoPersonaje);
        elemento.setText(personaje.getElemento());

        retrato = (ImageView) findViewById(R.id.imagenPersonaje);
        retrato.setImageBitmap(getBitmapFromAssets("personajes/" + personaje.getNombre().toLowerCase()));

        iconoElemento = (ImageView) findViewById(R.id.elemetoPersonajeIcon);
        iconoElemento.setImageBitmap(getBitmapFromAssets("elementos/" + personaje.getElemento().toLowerCase()));

        tipoArma = (TextView)findViewById(R.id.tipoArmaPersonaje);
        tipoArma.setText(personaje.getTipoArma());

        estrellas = (TextView)findViewById(R.id.numEstrellas);
        estrellas.setText(Integer.toString(personaje.getEstrellas()));

        cambioArma = (Spinner)findViewById(R.id.listaCambioArma);
        cambioArma.setAdapter(armaAdapter);
        cambioArma.setSelection(personaje.getArma().getID());

        cambioSet1 = (Spinner)findViewById(R.id.listaCambioSet1);
        cambioSet1.setAdapter(artefactoAdapter);
        cambioSet1.setSelection(personaje.getSet1().getID());

        cambioSet2 = (Spinner)findViewById(R.id.listaCambioSet2);
        cambioSet2.setAdapter(artefactoAdapter);
        cambioSet2.setSelection(personaje.getSet2().getID());

        reloj = (Spinner) findViewById(R.id.listaRelojPersonaje);
        caliz = (Spinner) findViewById(R.id.listaCalizPersonaje);
        diadema = (Spinner) findViewById(R.id.listaDiademaPersonaje);


        if(personaje.getArma().getID() != 0){
            actualizarEquipamiento();
        }

        bArma = (Button)findViewById(R.id.bEquipar);
        bArma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personaje.setArma((ArmaModel)cambioArma.getSelectedItem());
                personaje.setSet1((ArtefactoModel)cambioSet1.getSelectedItem());
                personaje.setSet2((ArtefactoModel)cambioSet2.getSelectedItem());
                personaje.setReloj((String)reloj.getSelectedItem());
                personaje.setCaliz((String)caliz.getSelectedItem());
                personaje.setDiadema((String)diadema.getSelectedItem());
                SingletonMap.getInstance().put("personaje", personaje);
                data.openWritable();
                data.equiparObjetos(personaje.getID(), personaje.getArma().getID(), personaje.getSet1().getID(),
                        personaje.getSet2().getID(), personaje.getReloj(), personaje.getCaliz(), personaje.getDiadema());
                data.close();
            }
        });

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

    private void actualizarEquipamiento(){
        cambioArma.setSelection(personaje.getArma().getID() % listaArmas.size());
        cambioSet1.setSelection(personaje.getSet1().getID()-1 % listaArtefactos.size());
        cambioSet2.setSelection(personaje.getSet2().getID()-1 % listaArtefactos.size());
        String[] aux = getResources().getStringArray(R.array.listaAtributosReloj);
        for(int i = 0; i < aux.length; i++){
            if(personaje.getReloj().equals(aux[i])){
                reloj.setSelection(i);
                break;
            }
        }
        aux = getResources().getStringArray(R.array.listaAtributosCaliz);
        for(int i = 0; i < aux.length; i++){
            if(personaje.getCaliz().equals(aux[i])){
                caliz.setSelection(i);
                break;
            }
        }
        aux = getResources().getStringArray(R.array.listaAtributosDiadema);
        for(int i = 0; i < aux.length; i++){
            if(personaje.getDiadema().equals(aux[i])){
                diadema.setSelection(i);
                break;
            }
        }
    }
}