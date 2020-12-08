package com.example.genshintb.model;

public class ArmaModel {
    private int ID;
    private String Nombre;
    private int Estrellas;
    private String StatSecundario;
    private String TipoArma;

    public ArmaModel(int ID, String nombre, int estrellas, String statSecundario, String tipoArma) {
        this.ID = ID;
        Nombre = nombre;
        Estrellas = estrellas;
        StatSecundario = statSecundario;
        TipoArma = tipoArma;
    }

    public ArmaModel(){

    }

    @Override
    public String toString() {
        return "ArmaModel{" +
                "ID=" + ID +
                ", Nombre='" + Nombre + '\'' +
                ", Estrellas=" + Estrellas +
                ", StatSecundario='" + StatSecundario + '\'' +
                ", TipoArma='" + TipoArma + '\'' +
                '}';
    }

    public int getID() { return ID; }

    public void setID(int ID) {this.ID = ID; }

    public String getNombre() {return Nombre; }

    public void setNombre(String nombre) {Nombre = nombre; }

    public int getEstrellas() { return Estrellas; }

    public void setEstrellas(int estrellas) { Estrellas = estrellas;}

    public String getStatSecundario() {return StatSecundario;}

    public void setStatSecundario(String statSecundario) {StatSecundario = statSecundario;}

    public String getTipoArma() { return TipoArma; }

    public void setTipoArma(String tipoArma) { TipoArma = tipoArma; }
}

