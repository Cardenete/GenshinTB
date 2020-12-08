package com.example.genshintb.model;

public class PersonajeModel {

    private int ID;
    private String Nombre;
    private int Estrellas;
    private String TipoArma;
    private String Elemento;
    private ArmaModel Arma;
    private ArtefactoModel Set1;
    private ArtefactoModel Set2;
    private String Reloj;
    private String Caliz;
    private String Diadema;

    public PersonajeModel(int ID, String nombre, int estrellas, String tipoArma, String elemento, ArmaModel arma, ArtefactoModel set1, ArtefactoModel set2, String reloj, String caliz, String diadema) {
        this.ID = ID;
        Nombre = nombre;
        Estrellas = estrellas;
        TipoArma = tipoArma;
        Elemento = elemento;
        Arma = arma;
        Set1 = set1;
        Set2 = set2;
        Reloj = reloj;
        Caliz = caliz;
        Diadema = diadema;
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
                ", Arma=" + Arma +
                ", Set1=" + Set1 +
                ", Set2=" + Set2 +
                ", Reloj='" + Reloj + '\'' +
                ", Caliz='" + Caliz + '\'' +
                ", Diadema='" + Diadema + '\'' +
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

    public ArmaModel getArma() { return Arma; }

    public void setArma(ArmaModel arma) { Arma = arma; }

    public ArtefactoModel getSet1() { return Set1; }

    public void setSet1(ArtefactoModel set1) { Set1 = set1; }

    public ArtefactoModel getSet2() { return Set2; }

    public void setSet2(ArtefactoModel set2) { Set2 = set2;}

    public String getReloj() {return Reloj; }

    public void setReloj(String reloj) { Reloj = reloj; }

    public String getCaliz() { return Caliz; }

    public void setCaliz(String caliz) {Caliz = caliz; }

    public String getDiadema() { return Diadema; }

    public void setDiadema(String diadema) { Diadema = diadema; }

}


