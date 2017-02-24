package com.example.kikui.lepetitfranais.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kikui on 23/02/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    public static final String SCORE_KEY = "id";
    public static final String SCORE_JEUX = "jeux";
    public static final String SCORE_SCORES = "scores";
    public static final String SCORE_TABLE_NAME = "Score_par_jeu";

    public static final String SCORE_TABLE_CREATE = "create table " +
                    SCORE_TABLE_NAME + " (" +
                    SCORE_KEY + " integer primary key autoincrement, " +
                    SCORE_JEUX + " text, " +
                    SCORE_SCORES + " integer default 0);";

    public static final String SCORE_TABLE_DROP = "DROP TABLE IF EXISTS " + SCORE_TABLE_NAME + ";";

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCORE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SCORE_TABLE_DROP);
        onCreate(db);
    }


}
