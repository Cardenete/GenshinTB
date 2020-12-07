package com.example.genshintb;

public class EquipoModel {

    private int ID;
    private int char1;
    private int char2;
    private int char3;
    private int char4;

    public EquipoModel(int ID, int char1, int char2, int char3, int char4) {
        this.ID = ID;
        this.char1 = char1;
        this.char2 = char2;
        this.char3 = char3;
        this.char4 = char4;
    }

    @Override
    public String toString() {
        return "EquipoModel{" +
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

    public int getChar1() {
        return char1;
    }

    public void setChar1(int char1) {
        this.char1 = char1;
    }

    public int getChar2() {
        return char2;
    }

    public void setChar2(int char2) {
        this.char2 = char2;
    }

    public int getChar3() {
        return char3;
    }

    public void setChar3(int char3) {
        this.char3 = char3;
    }

    public int getChar4() {
        return char4;
    }

    public void setChar4(int char4) {
        this.char4 = char4;
    }
}
