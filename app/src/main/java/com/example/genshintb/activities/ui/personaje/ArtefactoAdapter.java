package com.example.genshintb.activities.ui.personaje;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.genshintb.R;
import com.example.genshintb.database.DataAdapter;
import com.example.genshintb.model.ArtefactoModel;

import java.util.List;

public class ArtefactoAdapter extends BaseAdapter {

    Context context;
    List<ArtefactoModel> data;
    private static LayoutInflater inflater = null;
    DataAdapter da;

    public ArtefactoAdapter(Context context, List<ArtefactoModel> data) {
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
            view = inflater.inflate(R.layout.set_row, parent, false);
        //View view = inflater.inflate(R.layout.equipos_list_view, null);

        TextView nombreArtefacto = (TextView)view.findViewById(R.id.nombreSetRow);
        ArtefactoModel artefacto = (ArtefactoModel)getItem(position);
        nombreArtefacto.setText(artefacto.getNombre());

        return view;
    }
}
