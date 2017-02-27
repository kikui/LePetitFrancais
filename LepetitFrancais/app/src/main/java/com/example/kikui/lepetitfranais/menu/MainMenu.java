package com.example.kikui.lepetitfranais.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kikui.lepetitfranais.R;
import com.example.kikui.lepetitfranais.module.Jeu;
import com.example.kikui.lepetitfranais.module.JeuBDD;

public class MainMenu extends AppCompatActivity {

    private Button button_game;
    private Button button_success;
    private Button button_skils;
    private Button button_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        verify();

        button_game = (Button) findViewById(R.id.button_game);
        button_game.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MenuGame.class);
                startActivity(intent);
            }
        });

        button_success = (Button) findViewById(R.id.button_success);
        button_success.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MenuSuccess.class);
                startActivity(intent);
            }
        });

        button_skils = (Button) findViewById(R.id.button_skils);
        button_skils.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MenuSkils.class);
                startActivity(intent);
            }
        });

        button_settings = (Button) findViewById(R.id.button_settings);
        button_settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MenuSettings.class);
                startActivity(intent);
            }
        });

    }

    public void verify(){
        JeuBDD jeuBdd = new JeuBDD(this);
        jeuBdd.open();
        Jeu memoryAnimauxFromBdd = jeuBdd.getJeuWithNameJeu("memoryAnimaux");
        Jeu gameDeuxAnimauxFromBdd = jeuBdd.getJeuWithNameJeu("gameDeuxAnimaux");
        Jeu memoryChiffresFromBdd = jeuBdd.getJeuWithNameJeu("memoryChiffres");
        Jeu gameDeuxChiffresFromBdd = jeuBdd.getJeuWithNameJeu("gameDeuxChiffres");
        Jeu animauxFromBdd = jeuBdd.getJeuWithNameJeu("Animaux");
        Jeu chiffresFromBdd = jeuBdd.getJeuWithNameJeu("Chiffres");
        Jeu memoryFromBdd = jeuBdd.getJeuWithNameJeu("memory");
        Jeu gameDeuxFromBdd = jeuBdd.getJeuWithNameJeu("gameDeux");

        if(animauxFromBdd==null){
            Jeu jeu = new Jeu("Animaux",0);
            jeuBdd.insertJeu(jeu);
        }
        if(chiffresFromBdd==null){
            Jeu jeu = new Jeu("Chiffres",0);
            jeuBdd.insertJeu(jeu);
        }
        if(memoryFromBdd==null){
            Jeu jeu = new Jeu("memory",0);
            jeuBdd.insertJeu(jeu);
        }
        if(gameDeuxFromBdd==null){
            Jeu jeu = new Jeu("gameDeux",0);
            jeuBdd.insertJeu(jeu);
        }
        if(memoryAnimauxFromBdd==null){
            Jeu jeu = new Jeu("memoryAnimaux",0);
            jeuBdd.insertJeu(jeu);
        }
        if(memoryChiffresFromBdd==null){
            Jeu jeu = new Jeu("memoryChiffres",0);
            jeuBdd.insertJeu(jeu);
        }
        if(gameDeuxAnimauxFromBdd==null){
            Jeu jeu = new Jeu("gameDeuxAnimaux",0);
            jeuBdd.insertJeu(jeu);
        }
        if(gameDeuxChiffresFromBdd==null){
            Jeu jeu = new Jeu("gameDeuxChiffres",0);
            jeuBdd.insertJeu(jeu);
        }
        jeuBdd.close();
    }

}
