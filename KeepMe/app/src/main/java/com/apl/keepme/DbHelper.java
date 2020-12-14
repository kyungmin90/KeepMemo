package com.apl.keepme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static String DBNAME = "keepme.db";

    public DbHelper(Context context){super(context, DBNAME,null,1);}

    @Override
    public void onCreate(SQLiteDatabase db){

        String createTable = "create table if not exists myMemo("
                + " mNo integer PRIMARY KEY autoincrement, "
                + " mTitle text, "
                + " mContent text, "
                + " mTime datetime)";

        db.execSQL(createTable);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int i , int i1){
    db.execSQL("drop table if exists myMemo");
    }
}
