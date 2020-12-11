package com.example.genshintb.activities.ui.personaje;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.genshintb.R;
import com.example.genshintb.database.DataAdapter;
import com.example.genshintb.model.ArmaModel;

import java.util.List;

public class ArmaAdapter extends BaseAdapter {

    Context context;
    List<ArmaModel> data;
    private static LayoutInflater inflater = null;
    DataAdapter da;

    public ArmaAdapter(Context context, List<ArmaModel> data) {
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
            view = inflater.inflate(R.layout.arma_row, parent, false);
        //View view = inflater.inflate(R.layout.equipos_list_view, null);

        TextView nombreArma = (TextView)view.findViewById(R.id.nombreArmaRow);
        TextView estrellas = (TextView)view.findViewById(R.id.numEstrellasArma);
        TextView stat = (TextView)view.findViewById(R.id.statArmaRow);

        ArmaModel arma = (ArmaModel)getItem(position % data.size());

        nombreArma.setText(arma.getNombre());
        estrellas.setText(Integer.toString(arma.getEstrellas()));
        stat.setText(arma.getStatSecundario());

        return view;
    }
}
