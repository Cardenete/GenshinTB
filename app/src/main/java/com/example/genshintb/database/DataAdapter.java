package com.example.genshintb.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;

public class DataAdapter {

    private static String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;

    public DataAdapter(Context context){
        this.mContext = context;
        mDbHelper = new DatabaseHelper(mContext);
    }

    public DataAdapter createDatabase(){
        try{
            mDbHelper.createDatabase();
        } catch (IOException e) {
            Log.e(TAG, e.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter open() throws SQLException {
        try {
            mDbHelper.openDatabase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException e) {
            Log.e(TAG, "open >>"+ e.toString());
            throw e;
        }
        return this;
    }

    public DataAdapter openWritable() throws SQLException {
        try {
            mDbHelper.openDatabase();
            mDbHelper.close();
            mDb = mDbHelper.getWritableDatabase();

        } catch (SQLException e) {
            Log.e(TAG, "open >>"+ e.toString());
            throw e;
        }
        return this;
    }


    public void close(){
        mDbHelper.close();
    }

    public Cursor getAllPersonajes(){
        try{
            return mDb.query("Personaje", null, null, null,
                            null, null, null);
            //return cursor;
        }catch (SQLException e){
            Log.e(TAG, "getTestData >>"+ mDbHelper.DB_FILE + " "+ e.toString());
            throw e;
        }
    }

    public Cursor filtrarPersonajes(String nombre, String elemento, String estrellas, String tipoArma){
        try{
            return mDb.query("Personaje", null, "nombre=? and elemento=? and estrellas=? and tipo=?", new String[]{nombre, elemento, estrellas, tipoArma},
                    null, null, null);


            //return cursor;
        }catch (SQLException e){
            Log.e(TAG, "getTestData >>"+ mDbHelper.DB_FILE + " "+ e.toString());
            throw e;
        }
    }

    public Cursor getAllEquipos(){
        try{
            return mDb.query("Equipo", null, null, null,
                            null, null, null);
        }catch (SQLException e){
            Log.e(TAG, "getTestData >>"+ mDbHelper.DB_FILE + " "+ e.toString());
            throw e;
        }
    }

    public Cursor getPersonaje(int id){
        try{
            return mDb.query("Personaje", null, "id=?", new String[]{Integer.toString(id)},
                            null, null, null);
        }catch (SQLException e){
            Log.e(TAG, "getTestData >>"+ mDbHelper.DB_FILE + " "+ e.toString());
            throw e;
        }
    }



    public Cursor getArma(int id){
        try{
            return mDb.query("Arma", null, "id=?", new String[]{Integer.toString(id)},
                    null, null, null);
        }catch (SQLException e){
            Log.e(TAG, "getTestData >>"+ mDbHelper.DB_FILE + " "+ e.toString());
            throw e;
        }
    }

    public Cursor getArtefacto(int id){
        try{
            return mDb.query("Artefacto", null, "id=?", new String[]{Integer.toString(id)},
                    null, null, null);
        }catch (SQLException e){
            Log.e(TAG, "getTestData >>"+ mDbHelper.DB_FILE + " "+ e.toString());
            throw e;
        }
    }

    public Cursor getArmasPorTipo(String tipo){
        try{
            return mDb.query("Arma", null, "tipo=?", new String[]{tipo},
                    null, null, null);
        }catch (SQLException e){
            Log.e(TAG, "getTestData >>"+ mDbHelper.DB_FILE + " "+ e.toString());
            throw e;
        }
    }

    public Cursor getArtefactosAll(){
        try{
            return mDb.query("Artefacto", null, null, null,
                    null, null, null);
        }catch (SQLException e){
            Log.e(TAG, "getTestData >>"+ mDbHelper.DB_FILE + " "+ e.toString());
            throw e;
        }
    }

    public void cambiarPersonajeEquipo(int pos, int idP, int idE){
        try{
            ContentValues cv = new ContentValues();
            cv.put("personaje" + pos, idP);
            mDb.update("Equipo", cv, "id=?", new String[]{Integer.toString(idE)});
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void equiparObjetos(int personaje, int arma, int set1, int set2, String reloj,
                               String caliz, String diadema){
        try{
            ContentValues cv = new ContentValues();
            cv.put("arma", arma);
            cv.put("set1", set1);
            cv.put("set2", set2);
            cv.put("reloj", reloj);
            cv.put("caliz", caliz);
            cv.put("diadema", diadema);
            mDb.update("Personaje", cv, "id=?", new String[]{Integer.toString(personaje)});
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void crearEquipo(String nombre){
        try{
            ContentValues cv = new ContentValues();
            cv.put("personaje1", 19);
            cv.put("personaje2", 1);
            cv.put("personaje3", 12);
            cv.put("personaje4", 9);
            cv.put("nombre", nombre);
            mDb.insert("Equipo", null, cv);
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void borrarEquipo(int ID){
        this.openWritable();
        mDb.delete("Equipo", "id=?", new String[]{Integer.toString(ID)} );
        this.close();
    }
}
