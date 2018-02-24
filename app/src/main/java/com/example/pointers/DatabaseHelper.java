package com.example.pointers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dbname";
    public static final String TABLE_NAME = "tablename";

    public static final String COL1_ROLL = "ROLL";
    public static final String COL2_NAME = "NAME";
    public static final String COL3_SE = "SE";
    public static final String COL4_CN = "CN";
    public static final String COL5_DAA = "DAA";
    public static final String COL6_DP = "DP";
    public static final String COL7_MAP = "MAP";

    public static final int VERSION = 0;

    DatabaseHelper(Context c){
        super(c,DATABASE_NAME,null,1);
    }

//CREATE TABLE TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,MARKS INTEGER)

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL1_ROLL+" TEXT,"+
                COL2_NAME+" TEXT,"+
                COL3_SE+" TEXT,"+
                COL4_CN+" TEXT,"+
                COL5_DAA+" TEXT,"+
                COL6_DP+" TEXT,"+
                COL7_MAP+" TEXT ); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean insertData(String roll,String name,String se,String cn,String daa,String dp, String map){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1_ROLL,roll);
        cv.put(COL2_NAME,name);
        cv.put(COL3_SE,se);
        cv.put(COL4_CN,cn);
        cv.put(COL5_DAA,daa);
        cv.put(COL6_DP,dp);
        cv.put(COL7_MAP,map);

        Log.d("tag","SE is "+se+"\nDP is "+dp);

        long result = db.insert(TABLE_NAME,null,cv);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return c;
    }

    public int deleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME,"ROLL=?",new String[]{COL1_ROLL});
        return i;
    }

    public boolean updateData(String roll,String name,String se,String cn,String daa,String dp, String map){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL1_ROLL,roll);
        cv.put(COL2_NAME,name);
        cv.put(COL3_SE,se);
        cv.put(COL4_CN,cn);
        cv.put(COL5_DAA,daa);
        cv.put(COL6_DP,dp);
        cv.put(COL7_MAP,map);

        int i = db.update(TABLE_NAME,cv,"ROLL=?",new String[]{roll});

        if(i>0)
            return true;
        else
            return  false;
    }

}
