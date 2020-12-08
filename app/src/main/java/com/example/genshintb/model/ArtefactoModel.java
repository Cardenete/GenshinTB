package com.example.genshintb.model;

public class ArtefactoModel {
    private int ID;
    private String Nombre;

    public ArtefactoModel(int ID, String nombre) {
        this.ID = ID;
        Nombre = nombre;
    }

    public int getID() { return ID; }

    public void setID(int ID) { this.ID = ID; }

    public String getNombre() { return Nombre; }

    public void setNombre(String nombre) { Nombre = nombre; }

    @Override
    public String toString() {
        return "ArtefactoModel{" +
                "ID=" + ID +
                ", Nombre='" + Nombre + '\'' +
                '}';
    }
}
