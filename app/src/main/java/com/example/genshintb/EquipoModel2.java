package com.example.genshintb;

public class EquipoModel2 {

    private int ID;
    private PersonajeModel char1;
    private PersonajeModel char2;
    private PersonajeModel char3;
    private PersonajeModel char4;

    public EquipoModel2(int ID, PersonajeModel char1, PersonajeModel char2, PersonajeModel char3, PersonajeModel char4) {
        this.ID = ID;
        this.char1 = char1;
        this.char2 = char2;
        this.char3 = char3;
        this.char4 = char4;
    }

    @Override
    public String toString() {
        return "EquipoModel2{" +
                "ID=" + ID +
                ", char1=" + char1 +
                ", char2=" + char2 +
                ", char3=" + char3 +
                ", char4=" + char4 +
                '}';
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
}
