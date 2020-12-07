package com.example.genshintb;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


    public void close(){
        mDbHelper.close();
    }

    public Cursor getAllPersonajes(){
        try{
            String sql = "SELECT * FROM " + "prueba";
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
            String sql = "SELECT * FROM " + "prueba" + " WHERE ID=" +  Integer.toString(id);
            return mDb.rawQuery(sql, null);
        }catch (SQLException e){
            Log.e(TAG, "getTestData >>"+ mDbHelper.DB_FILE + " "+ e.toString());
            throw e;
        }
    }
}
