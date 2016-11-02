package com.example.gleilson.soliceservices.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.gleilson.soliceservices.model.Category;

/**
 * Created by gleilson on 15/10/16.
 */
public class BaseDAO {

    Database db;

    public BaseDAO(Context context) {
        db = new Database(context);
    }

    public void deleteById(String tableName, long id) {
        String [] params = { String.valueOf(id) };
        db.getWritableDatabase().delete(tableName, "id = ?", params);
    }

    public void close() {
        db.close();
    }
}
