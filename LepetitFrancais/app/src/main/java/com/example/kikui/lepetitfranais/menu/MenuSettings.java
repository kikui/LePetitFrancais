package com.example.kikui.lepetitfranais.menu;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kikui.lepetitfranais.R;
import com.example.kikui.lepetitfranais.module.Jeu;
import com.example.kikui.lepetitfranais.module.JeuBDD;

public class MenuSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_settings);

        final TextView textview2 = (TextView)findViewById(R.id.textView4);
        final TextView textview5 = (TextView)findViewById(R.id.textView5);
        Button button2 = (Button) findViewById(R.id.button2);
        ProgressBar progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);

        int nb = 0;

        //-----------------------------------------------------------------

        JeuBDD jeuBdd = new JeuBDD(this);

        Jeu memoryAnimaux = new Jeu("memoryAnimaux",1500);

        jeuBdd.open();

        jeuBdd.insertJeu(memoryAnimaux);

        Jeu jeuFromBdd = jeuBdd.getJeuWithNameJeu(memoryAnimaux.getJeu());

        if(jeuFromBdd != null){
            Toast.makeText(this, jeuFromBdd.toString(), Toast.LENGTH_LONG).show();
            jeuFromBdd.setJeu("J'ai modifié le nom du jeu");
            jeuBdd.updateJeu(jeuFromBdd.getId(), jeuFromBdd);
        }

        jeuFromBdd = jeuBdd.getJeuWithNameJeu("J'ai modifié le nom du jeu");

        if(jeuFromBdd != null){
            Toast.makeText(this, jeuFromBdd.toString(), Toast.LENGTH_LONG).show();
            String affiche = "score de J'ai modifié le nom du jeu :";
            Toast.makeText(this, affiche.toString()+jeuFromBdd.getScore() , Toast.LENGTH_LONG).show();
            jeuBdd.removeJeuWithID(jeuFromBdd.getId());
        }

        jeuFromBdd = jeuBdd.getJeuWithNameJeu("J'ai modifié le nom du jeu");

        if(jeuFromBdd == null){
            Toast.makeText(this, "Ce livre n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
        }

        else{
            Toast.makeText(this, "Ce livre existe dans la BDD", Toast.LENGTH_LONG).show();
        }

        jeuBdd.close();

        //-----------------------------------------------------------------

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });


        progressBar3.setProgress(jeuFromBdd.getScorePCT());
        textview2.setText(String.valueOf(progressBar3.getProgress()+"% "));

    }

}
