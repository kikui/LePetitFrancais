package com.example.kikui.lepetitfranais.module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by kikui on 23/02/2017.
 */

public class ScoreDAO extends DAOBase {

    public static final String TABLE_NAME = "Score_par_jeu";
    public static final String KEY = "id";
    public static final String JEU = "jeu";
    public static final String SCORE = "score";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + JEU + " TEXT, " + SCORE + " REAL);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public ScoreDAO(Context pContext) {
        super(pContext);
    }

    /**
     * @param m le jeu à ajouter à la base
     */
    public void ajouter(Score m) {
        ContentValues value = new ContentValues();
        value.put(ScoreDAO.JEU, m.getJeu());
        value.put(ScoreDAO.SCORE, m.getScore());
        SQLDb.insert(ScoreDAO.TABLE_NAME, null, value);
    }

    /**
     * @param id l'identifiant du jeu à supprimer
     */
    public void supprimer(long id) {
        SQLDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    /**
     * @param m  le score du jeu à mettre à jour
     */
    public void modifier(Score m) {
        ContentValues value = new ContentValues();
        value.put(SCORE, m.getScore());
        SQLDb.update(TABLE_NAME, value, KEY  + " = ?", new String[] {String.valueOf(m.getId())});

    }

    /**
     * @param id l'identifiant d'un jeu pour récupérer le score
     */
    public Cursor selectionner(int id) {
        Cursor c = SQLDb.rawQuery("select " + SCORE + " from " + TABLE_NAME + " where jeu = ?", new String[]{String.valueOf(id)});
        return c;
    }
}
