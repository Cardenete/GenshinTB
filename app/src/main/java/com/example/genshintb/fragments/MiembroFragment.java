package com.example.genshintb.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.genshintb.R;
import com.example.genshintb.activities.ui.main.EquipoAdapter;
import com.example.genshintb.database.DataAdapter;
import com.example.genshintb.model.PersonajeModel;

public class MiembroFragment extends Fragment {

    private PersonajeModel personaje;


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
        View view = inflater.inflate(R.layout.fragment_lista_equipos, container, false);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Ver como es boton
        /*view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PersonajesFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });*/
    }

}
