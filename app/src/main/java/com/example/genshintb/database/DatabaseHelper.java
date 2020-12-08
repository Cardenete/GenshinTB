package com.example.genshintb.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.LocaleList;
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
    public static final String DB_NAME_EN = "genshin.db";
    public static final String DB_NAME_ES = "genshin_es.db";
    private String DB_NAME;


    private final Context mContext;
    public final File DB_FILE;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(@Nullable Context context) {
        super(context, (context.getResources().getConfiguration().getLocales().get(0).getLanguage().equals("es") ? DB_NAME_ES : DB_NAME_EN), null, 1);
        if(context.getResources().getConfiguration().getLocales().get(0).getLanguage().equals("es")) {
            DB_FILE = new File(context.getApplicationInfo().dataDir + "/databases/" + DB_NAME_ES);
            DB_NAME = DB_NAME_ES;
        } else {
            DB_FILE = new File(context.getApplicationInfo().dataDir + "/databases/" + DB_NAME_EN);
            DB_NAME = DB_NAME_EN;
        }
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
}
