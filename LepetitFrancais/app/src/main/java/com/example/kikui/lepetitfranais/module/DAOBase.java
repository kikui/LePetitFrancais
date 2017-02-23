package com.example.kikui.lepetitfranais.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kikui on 23/02/2017.
 */

public abstract class DAOBase {

    protected final static int VERSION = 1;
    protected final static String NOM = "database.db";

    protected SQLiteDatabase SQLDb = null;
    protected DataBase mDataBase = null;

    public DAOBase(Context pContext) {
        this.mDataBase = new DataBase(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        SQLDb = mDataBase.getWritableDatabase();
        return SQLDb;
    }

    public void close() {
        SQLDb.close();
    }

    public SQLiteDatabase getDb() {
        return SQLDb;
    }

}
