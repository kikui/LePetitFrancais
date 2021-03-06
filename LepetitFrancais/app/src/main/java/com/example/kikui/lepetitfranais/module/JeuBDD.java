package com.example.kikui.lepetitfranais.module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by kikui on 25/02/2017.
 */

public class JeuBDD {

    private static final int VERSION_BDD = 5;
    private static final String NOM_BDD = "maBDD.db";

    private static final String TABLE_JEUX = "table_jeux";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_JEUX = "Jeux";
    private static final int NUM_COL_JEUX = 1;
    private static final String COL_SCORES = "Scores";
    private static final int NUM_COL_SCORES = 2;

    private SQLiteDatabase bdd;

    private DataBase maBaseSQLite;

    public JeuBDD(Context context){
        //On créer la BDD et sa table
        maBaseSQLite = new DataBase(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public void insertJeu(Jeu jeu){
        //Création d'un ContentValues
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_JEUX, jeu.getJeu());
        values.put(COL_SCORES, jeu.getScore());
        //on insère l'objet dans la BDD via le ContentValues
        bdd.insert(TABLE_JEUX, null, values);
    }

    public void updateJeu(int id, Jeu jeu){
        //La mise à jour d'un jeu dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quelle jeu on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_JEUX, jeu.getJeu());
        values.put(COL_SCORES, jeu.getScore());
        bdd.update(TABLE_JEUX, values, COL_ID + " = " +id, null);
    }

    public void removeJeuWithID(int id){
        //Suppression d'un jeu de la BDD grâce à l'ID
        bdd.delete(TABLE_JEUX, COL_ID + " = " +id, null);
    }

    public void resetAllJeu(){
        Jeu jeuMemoryAnimaux = getJeuWithNameJeu("memoryAnimaux");
        removeJeuWithID(jeuMemoryAnimaux.getId());
        Jeu jeuGameDeuxAnimaux = getJeuWithNameJeu("gameDeuxAnimaux");
        removeJeuWithID(jeuGameDeuxAnimaux.getId());
        Jeu jeuMemoryChiffres = getJeuWithNameJeu("memoryChiffres");
        removeJeuWithID(jeuMemoryChiffres.getId());
        Jeu jeuGameDeuxChiffres = getJeuWithNameJeu("gameDeuxChiffres");
        removeJeuWithID(jeuGameDeuxChiffres.getId());
    }

    public Jeu getJeuWithNameJeu(String jeu){
        //Récupère dans un Cursor les valeur correspondant à un jeu contenu dans la BDD (ici on sélectionne le jeu grâce à son nom)
        Cursor c = bdd.query(TABLE_JEUX, new String[] {COL_ID, COL_JEUX, COL_SCORES}, COL_JEUX + " LIKE \"" + jeu +"\"", null, null, null, null);
        return cursorToJeu(c);
    }

    private Jeu cursorToJeu(Cursor c){
        if (c.getCount() == 0)
            return null;
        c.moveToFirst();
        Jeu jeu = new Jeu();

        jeu.setId(c.getInt(NUM_COL_ID));
        jeu.setJeu(c.getString(NUM_COL_JEUX));
        jeu.setScore(c.getInt(NUM_COL_SCORES));

        c.close();
        return jeu;
    }

    public int getScoresWithCategory(String category){
        int result=0;
        int count=0;
        int score=0;
        Cursor c = bdd.query(TABLE_JEUX, new String[] {COL_ID, COL_JEUX, COL_SCORES}, COL_JEUX + " LIKE \"%" + category +"%\"", null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                score = c.getInt(NUM_COL_SCORES);
                result += score;
                count++;
                c.moveToNext();
            }
            c.close();
            result = (result*100)/((count-1)*2000);
        }

        return result;
    }

}