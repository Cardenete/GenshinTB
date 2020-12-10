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
            String sql = "SELECT * FROM " + "Personaje";
            Cursor cursor = mDb.rawQuery(sql, null);
            return cursor;
        }catch (SQLException e){
            Log.e(TAG, "getTestData >>"+ mDbHelper.DB_FILE + " "+ e.toString());
            throw e;
        }
    }

    public Cursor getAllEquipos(){
        try{
            String sql = "SELECT * FROM " + "Equipo";
            Cursor cursor = mDb.rawQuery(sql, null);
            return cursor;
        }catch (SQLException e){
            Log.e(TAG, "getTestData >>"+ mDbHelper.DB_FILE + " "+ e.toString());
            throw e;
        }
    }

    public Cursor getPersonaje(int id){
        try{
            String sql = "SELECT * FROM " + "Personaje" + " WHERE id=" +  Integer.toString(id);
            return mDb.rawQuery(sql, null);
        }catch (SQLException e){
            Log.e(TAG, "getTestData >>"+ mDbHelper.DB_FILE + " "+ e.toString());
            throw e;
        }
    }

    public Cursor getArma(int id){
        try{
            String sql = "SELECT * FROM " + "Arma" + " WHERE id=" +  Integer.toString(id);
            return mDb.rawQuery(sql, null);
        }catch (SQLException e){
            Log.e(TAG, "getTestData >>"+ mDbHelper.DB_FILE + " "+ e.toString());
            throw e;
        }
    }

    public Cursor getArtefacto(int id){
        try{
            String sql = "SELECT * FROM " + "Artefacto" + " WHERE id=" +  Integer.toString(id);
            return mDb.rawQuery(sql, null);
        }catch (SQLException e){
            Log.e(TAG, "getTestData >>"+ mDbHelper.DB_FILE + " "+ e.toString());
            throw e;
        }
    }

    public Cursor getArmasPorTipo(String tipo){
        try{
            String sql = "SELECT * FROM " + "Arma" + " WHERE tipo LIKE '" +  tipo + "'";
            return mDb.rawQuery(sql, null);
        }catch (SQLException e){
            Log.e(TAG, "getTestData >>"+ mDbHelper.DB_FILE + " "+ e.toString());
            throw e;
        }
    }

    public Cursor getArtefactosAll(){
        try{
            String sql = "SELECT * FROM Artefacto";
            return mDb.rawQuery(sql, null);
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
}
