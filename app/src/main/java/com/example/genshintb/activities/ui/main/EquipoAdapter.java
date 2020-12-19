package com.example.genshintb.activities.ui.main;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EquipoAdapter extends BaseAdapter {

    Context context;
    List<EquipoModel> data;
    private static LayoutInflater inflater = null;
    DataAdapter da;

    public EquipoAdapter(Context context, List<EquipoModel> data) {
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
            view = inflater.inflate(R.layout.equipo_row, parent, false);
        //View view = inflater.inflate(R.layout.equipos_list_view, null);

        TextView char1 = (TextView)view.findViewById(R.id.pers1);
        TextView char2 = (TextView)view.findViewById(R.id.pers2);
        TextView char3 = (TextView)view.findViewById(R.id.pers3);
        TextView char4 = (TextView)view.findViewById(R.id.pers4);
        TextView nombre = (TextView)view.findViewById(R.id.nombre);
        ImageView img1 = (ImageView) view.findViewById(R.id.pers1Imagen);
        ImageView img2 = (ImageView) view.findViewById(R.id.pers2Imagen);
        ImageView img3 = (ImageView) view.findViewById(R.id.pers3Imagen);
        ImageView img4 = (ImageView) view.findViewById(R.id.pers4Imagen);

        EquipoModel equipo = (EquipoModel)getItem(position);

        char1.setText(equipo.getChar1().getNombre());
        char2.setText(equipo.getChar2().getNombre());
        char3.setText(equipo.getChar3().getNombre());
        char4.setText(equipo.getChar4().getNombre());
        nombre.setText(equipo.getNombre());
        img1.setImageBitmap(getBitmapFromAssets(equipo.getChar1().getNombre().toLowerCase()));
        img2.setImageBitmap(getBitmapFromAssets(equipo.getChar2().getNombre().toLowerCase()));
        img3.setImageBitmap(getBitmapFromAssets(equipo.getChar3().getNombre().toLowerCase()));
        img4.setImageBitmap(getBitmapFromAssets(equipo.getChar4().getNombre().toLowerCase()));

        return view;
    }

    private Bitmap getBitmapFromAssets(String fileName){

        AssetManager am = context.getAssets();
        InputStream is = null;
        try{
            is=am.open("personajes/" + fileName + ".png");
        }catch (IOException e){
            e.printStackTrace();
        }

        return BitmapFactory.decodeStream(is);
    }
}
