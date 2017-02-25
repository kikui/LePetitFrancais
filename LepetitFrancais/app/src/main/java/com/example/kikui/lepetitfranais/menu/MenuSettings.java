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

        //Création d'une instance de ma classe JeuBDD
        JeuBDD jeuBdd = new JeuBDD(this);

        //Création d'un jeu
        Jeu memoryAnimaux = new Jeu("memoryAnimaux",1500);

        //On ouvre la base de données pour écrire dedans
        jeuBdd.open();
        //On insère le livre que l'on vient de créer
        jeuBdd.insertJeu(memoryAnimaux);

        //Pour vérifier que l'on a bien créé notre jeu dans la BDD
        //on extrait le jeu de la BDD grâce au nom du jeu que l'on a créé précédemment
        Jeu jeuFromBdd = jeuBdd.getJeuWithNameJeu(memoryAnimaux.getJeu());
        //Si un livre est retourné (donc si le jeu à bien été ajouté à la BDD)
        if(jeuFromBdd != null){
            //On affiche les infos du jeu dans un Toast
            Toast.makeText(this, jeuFromBdd.toString(), Toast.LENGTH_LONG).show();
            //On modifie le titre du livre
            jeuFromBdd.setJeu("J'ai modifié le nom du jeu");
            //Puis on met à jour la BDD
            jeuBdd.updateJeu(jeuFromBdd.getId(), jeuFromBdd);
        }

        //On extrait le livre de la BDD grâce au nouveau titre
        jeuFromBdd = jeuBdd.getJeuWithNameJeu("J'ai modifié le nom du jeu");
        //S'il existe un livre possédant ce titre dans la BDD
        if(jeuFromBdd != null){
            //On affiche les nouvelle info du livre pour vérifié que le titre du livre a bien été mis à jour
            Toast.makeText(this, jeuFromBdd.toString(), Toast.LENGTH_LONG).show();
            //on supprime le livre de la BDD grâce à son ID
            jeuBdd.removeJeuWithID(jeuFromBdd.getId());
        }

        //On essait d'extraire de nouveau le livre de la BDD toujours grâce à son nouveau titre
        jeuFromBdd = jeuBdd.getJeuWithNameJeu("J'ai modifié le nom du jeu");
        //Si aucun livre n'est retourné
        if(jeuFromBdd == null){
            //On affiche un message indiquant que le livre n'existe pas dans la BDD
            Toast.makeText(this, "Ce livre n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
        }

        //Si le livre existe (mais normalement il ne devrait pas)
        else{
            //on affiche un message indiquant que le livre existe dans la BDD
            Toast.makeText(this, "Ce livre existe dans la BDD", Toast.LENGTH_LONG).show();
        }

        jeuBdd.close();

        //-----------------------------------------------------------------

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });


        progressBar3.setProgress(nb);
        //textview2.setText(String.valueOf(progressBar3.getProgress()+"% "));

    }

}
