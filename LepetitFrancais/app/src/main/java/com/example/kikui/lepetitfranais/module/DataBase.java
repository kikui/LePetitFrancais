package com.example.kikui.lepetitfranais.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kikui on 23/02/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    private static final String TABLE_JEUX = "table_jeux";
    private static final String COL_ID = "ID";
    private static final String COL_JEUX = "Jeux";
    private static final String COL_SCORES = "Scores";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_JEUX + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_JEUX + " TEXT NOT NULL, "
            + COL_SCORES + " INTEGER DEFAULT 0);";

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_JEUX + ";");
        onCreate(db);
    }

}