package com.example.genshintb.model;

import com.example.genshintb.fragments.PersonajesFragment;
import com.example.genshintb.model.PersonajeModel;

import java.util.ArrayList;
import java.util.List;

public class EquipoModel {

    private int ID;
    private PersonajeModel char1;
    private PersonajeModel char2;
    private PersonajeModel char3;
    private PersonajeModel char4;
    private String nombre;

    public EquipoModel(int ID, PersonajeModel char1, PersonajeModel char2, PersonajeModel char3, PersonajeModel char4, String nombre) {
        this.ID = ID;
        this.char1 = char1;
        this.char2 = char2;
        this.char3 = char3;
        this.char4 = char4;
        this.nombre = nombre;
    }

    public EquipoModel(int ID, PersonajeModel char1, PersonajeModel char2, PersonajeModel char3, PersonajeModel char4) {
        this.ID = ID;
        this.char1 = char1;
        this.char2 = char2;
        this.char3 = char3;
        this.char4 = char4;
        this.nombre = "Equipo " + ID;
    }

    @Override
    public String toString() {
        return "EquipoModel{" +
                "ID=" + ID +
                ", char1=" + char1 +
                ", char2=" + char2 +
                ", char3=" + char3 +
                ", char4=" + char4 +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public List<PersonajeModel> listaMiembros(){
        List<PersonajeModel> lista = new ArrayList<>();
        lista.add(char1);
        lista.add(char2);
        lista.add(char3);
        lista.add(char4);
        return lista;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public PersonajeModel getChar1() {
        return char1;
    }

    public void setChar1(PersonajeModel char1) {
        this.char1 = char1;
    }

    public PersonajeModel getChar2() {
        return char2;
    }

    public void setChar2(PersonajeModel char2) {
        this.char2 = char2;
    }

    public PersonajeModel getChar3() {
        return char3;
    }

    public void setChar3(PersonajeModel char3) {
        this.char3 = char3;
    }

    public PersonajeModel getChar4() {
        return char4;
    }

    public void setChar4(PersonajeModel char4) {
        this.char4 = char4;
    }

    public String getNombre() { return nombre;  }

    public void setNombre(String nombre) {  this.nombre = nombre; }
}
