package com.example.genshintb.model;

public class PersonajeModel {

    private int ID;
    private String Nombre;
    private int Estrellas;
    private String Elemento;
    private String TipoArma;
    private String Imagen;

    public PersonajeModel(int ID, String nombre, int estrellas, String elemento, String tipoArma, String imagen) {
        this.ID = ID;
        Nombre = nombre;
        Estrellas = estrellas;
        Elemento = elemento;
        TipoArma = tipoArma;
        Imagen = imagen;
    }

    public PersonajeModel() {

    }

    @Override
    public String toString() {
        return "PersonajeModel{" +
                "ID=" + ID +
                ", Nombre='" + Nombre + '\'' +
                ", Estrellas=" + Estrellas +
                ", Elemento='" + Elemento + '\'' +
                ", TipoArma='" + TipoArma + '\'' +
                ", Imagen='" + Imagen + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getEstrellas() {
        return Estrellas;
    }

    public void setEstrellas(int estrellas) {
        Estrellas = estrellas;
    }

    public String getElemento() {
        return Elemento;
    }

    public void setElemento(String elemento) {
        Elemento = elemento;
    }

    public String getTipoArma() {
        return TipoArma;
    }

    public void setTipoArma(String tipoArma) {
        TipoArma = tipoArma;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }
}

