package com.example.genshintb.activities.ui.main;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.genshintb.R;
import com.example.genshintb.database.DataAdapter;
import com.example.genshintb.model.EquipoModel;
import com.example.genshintb.model.PersonajeModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PersonajeAdapter extends BaseAdapter {

    Context context;
    List<PersonajeModel> data;
    private static LayoutInflater inflater = null;
    DataAdapter da;

    public PersonajeAdapter(Context context, List<PersonajeModel> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        da = new DataAdapter(context);
    }

    @Override
    public int getCount() { return data.size(); }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {   return position;    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = inflater.inflate(R.layout.personaje_row, parent, false);
        //View view = inflater.inflate(R.layout.equipos_list_view, null);

        TextView nombre = (TextView)view.findViewById(R.id.nombrePersonajeRow);
        TextView estrellas = (TextView)view.findViewById(R.id.estrellasPersonajeRow);
        ImageView elemento = (ImageView) view.findViewById(R.id.elementoPersonajeRow);

        PersonajeModel personaje = (PersonajeModel)getItem(position);
        nombre.setText(personaje.getNombre());
        estrellas.setText(Integer.toString(personaje.getEstrellas()));
        elemento.setImageBitmap(getBitmapFromAssets("elementos/" + personaje.getElemento().toLowerCase()));

        return view;
    }

    private Bitmap getBitmapFromAssets(String fileName){

        AssetManager am = context.getAssets();
        InputStream is = null;
        try{
            is=am.open(fileName + ".png");
        }catch (IOException e){
            e.printStackTrace();
        }

        return BitmapFactory.decodeStream(is);
    }

    public void updateData(List<PersonajeModel> update){
        this.data = update;
        notifyDataSetChanged();
    }
}
