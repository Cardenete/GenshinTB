package com.example.genshintb.activities.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.genshintb.R;
import com.example.genshintb.database.DataAdapter;
import com.example.genshintb.model.EquipoModel;

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

        EquipoModel equipo = (EquipoModel)getItem(position);

        char1.setText(equipo.getChar1().getNombre());
        char2.setText(equipo.getChar2().getNombre());
        char3.setText(equipo.getChar3().getNombre());
        char4.setText(equipo.getChar4().getNombre());
        nombre.setText(equipo.getNombre());

        return view;
    }
}
