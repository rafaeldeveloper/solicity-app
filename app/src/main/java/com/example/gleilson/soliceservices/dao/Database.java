package com.example.gleilson.soliceservices.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gleilson on 15/10/16.
 */
public class Database extends SQLiteOpenHelper {

    final String DB_NAME = "SOLICITE";

    public Database(Context context) {
        super(context, "SOLICITE", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSql.getCreateProfile());
        db.execSQL(ScriptSql.getCreateCategory());
        db.execSQL(ScriptSql.getCreateProfessionals());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int i1) {

    }
}
