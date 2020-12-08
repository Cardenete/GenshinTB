package com.example.genshintb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.genshintb.R;
import com.example.genshintb.model.PersonajeModel;
import com.example.genshintb.util.SingletonMap;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;

public class PersonajeActivity extends AppCompatActivity {

    PersonajeModel personaje;
    TextView nombre;
    TextView elemento;
    TextView tipoArma;
    TextView estrellas;
    ImageView retrato;
    ImageView iconoElemento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje);

        personaje = (PersonajeModel) SingletonMap.getInstance().get("personaje");
        Log.i("FTVGBHJNKAMGILEDGBDSA", personaje.getNombre().toLowerCase());
        nombre = (TextView)findViewById(R.id.nombrePersonaje);
        elemento = (TextView)findViewById(R.id.elementoPersonaje);
        retrato = (ImageView) findViewById(R.id.imagenPersonaje);
        iconoElemento = (ImageView) findViewById(R.id.elemetoPersonajeIcon);
        tipoArma = (TextView)findViewById(R.id.tipoArmaPersonaje);
        estrellas = (TextView)findViewById(R.id.numEstrellas);

        nombre.setText(personaje.getNombre());
        elemento.setText(personaje.getElemento());
        retrato.setImageBitmap(getBitmapFromAssets("personajes/" + personaje.getNombre().toLowerCase()));
        iconoElemento.setImageBitmap(getBitmapFromAssets("elementos/" + personaje.getElemento().toLowerCase()));
        tipoArma.setText(personaje.getTipoArma());
        estrellas.setText(Integer.toString(personaje.getEstrellas()));
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
}