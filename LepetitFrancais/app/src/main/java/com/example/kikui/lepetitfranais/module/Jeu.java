package com.example.kikui.lepetitfranais.module;

/**
 * Created by kikui on 23/02/2017.
 */

public class Jeu {

    private int id;
    private String jeu;
    private int score;

    public Jeu(){}

    public Jeu(String jeu, int score) {
        this.jeu = jeu;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJeu() {
        return jeu;
    }

    public void setJeu(String jeu) {
        this.jeu = jeu;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String toString(){
        return "ID : "+id+"\njeu : "+jeu+"\nscore : "+score;
    }
}
