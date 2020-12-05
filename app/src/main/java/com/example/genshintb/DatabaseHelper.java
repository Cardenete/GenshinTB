package com.example.genshintb;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static String TAG = "DatabaseHelper";
    public static final String PERSONAJE = "PERSONAJE";
    public static final String DB_NAME = "genshin.db";

    private final Context mContext;
    public final File DB_FILE;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "genshin.db", null, 1);
        DB_FILE = new File(context.getApplicationInfo().dataDir + "/databases/" + DB_NAME);
        this.mContext = context;
    }

    public void createDatabase() throws IOException{
        // If the database does not exist, copy it from the assets.
        boolean myDatabaseExist = checkDatabase();
        if(!myDatabaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                // Copy the database from assests
                copyDatabase();
                Log.e(TAG, "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private boolean checkDatabase(){
        return DB_FILE.exists();
    }

    private void copyDatabase() throws IOException{
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_FILE);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while((mLength = mInput.read(mBuffer)) > 0){
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDatabase() throws SQLException{
        mDatabase = SQLiteDatabase.openDatabase(DB_FILE.getAbsolutePath(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDatabase != null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<PersonajeModel> getPersonajes(){

        List<PersonajeModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + PERSONAJE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                int personajeID = cursor.getInt(0);
                String personajeName = cursor.getString(1);
                int personajeEstrellas = cursor.getInt(2);
                String personajeElemento = cursor.getString(3);
                String personajeTipoArma = cursor.getString(4);
                String personajeImagen = cursor.getString(5);

                PersonajeModel newPersonaje = new PersonajeModel(personajeID, personajeName, personajeEstrellas, personajeElemento,
                                                                    personajeTipoArma, personajeImagen);
                returnList.add(newPersonaje);
            }while(cursor.moveToNext());
        }else{
            //Failure. Do not add anything to the list.
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
