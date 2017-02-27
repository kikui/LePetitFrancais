package com.example.kikui.lepetitfranais.module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kikui on 25/02/2017.
 */

public class JeuBDD {

    private static final int VERSION_BDD = 3;
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

    public long insertJeu(Jeu jeu){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_JEUX, jeu.getJeu());
        values.put(COL_SCORES, jeu.getScore());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_JEUX, null, values);
    }

    public int updateJeu(int id, Jeu jeu){
        //La mise à jour d'un jeu dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quelle jeu on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_JEUX, jeu.getJeu());
        values.put(COL_SCORES, jeu.getScore());
        return bdd.update(TABLE_JEUX, values, COL_ID + " = " +id, null);
    }

    public int removeJeuWithID(int id){
        //Suppression d'un jeu de la BDD grâce à l'ID
        return bdd.delete(TABLE_JEUX, COL_ID + " = " +id, null);
    }

    public Jeu getJeuWithNameJeu(String jeu){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_JEUX, new String[] {COL_ID, COL_JEUX, COL_SCORES}, COL_JEUX + " LIKE \"" + jeu +"\"", null, null, null, null);
        return cursorToJeu(c);
    }

    //Cette méthode permet de convertir un cursor en un Jeu
    private Jeu cursorToJeu(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un jeu
        Jeu jeu = new Jeu();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        jeu.setId(c.getInt(NUM_COL_ID));
        jeu.setJeu(c.getString(NUM_COL_JEUX));
        jeu.setScore(c.getInt(NUM_COL_SCORES));
        //On ferme le cursor
        c.close();

        //On retourne le jeu
        return jeu;
    }

    public int getScoresWithCategory(String category){
        int result=0;
        int count=0;
        Cursor c = bdd.query(TABLE_JEUX, new String[] {COL_ID, COL_JEUX, COL_SCORES}, COL_JEUX + " LIKE \"%" + category +"%\"", null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                result =+ c.getInt(NUM_COL_SCORES);
                count++;
                c.moveToNext();
            }
            c.close();
            result = (result*100)/((count-1)*2000);
        }

        return result;
    }

}