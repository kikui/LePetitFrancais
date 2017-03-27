package com.example.kikui.lepetitfranais.menu;

import android.content.Intent;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kikui.lepetitfranais.R;
import com.example.kikui.lepetitfranais.module.Jeu;
import com.example.kikui.lepetitfranais.module.JeuBDD;
import com.example.kikui.lepetitfranais.module.Memory;

public class MenuMemory extends AppCompatActivity {

    private Button buttonMemoryAnimaux;
    private Button buttonMemoryChiffres;
    private Button buttonMemoryMaison;
    private Button buttonMemoryForet;
    private Button buttonMemoryVille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_memory);

        buttonMemoryAnimaux = (Button) findViewById(R.id.button_memory_animaux);
        TextView animauxPCT = (TextView)findViewById(R.id.animauxPCT);
        buttonMemoryChiffres = (Button) findViewById(R.id.button_memory_chiffres);
        TextView chiffresPCT = (TextView)findViewById(R.id.chiffresPCT);
        buttonMemoryMaison = (Button) findViewById(R.id.button_memory_maison);
        TextView maisonPCT = (TextView)findViewById(R.id.maisonPCT);
        buttonMemoryForet = (Button) findViewById(R.id.button_memory_foret);
        TextView foretPCT = (TextView)findViewById(R.id.foretPCT);
        buttonMemoryVille = (Button) findViewById(R.id.button_memory_ville);
        TextView villePCT = (TextView)findViewById(R.id.villePCT);

        animauxPCT.setText(String.valueOf(getScoreJeu("memoryAnimaux")+"%"));
        chiffresPCT.setText(String.valueOf(getScoreJeu("memoryChiffres")+"%"));
        maisonPCT.setText("0%");//Comming soon
        foretPCT.setText("0%");//Comming soon
        villePCT.setText("0%");//Comming soon

        buttonMemoryAnimaux.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuMemory.this, Memory.class);
                intent.putExtra("nameGame","memoryAnimaux");
                startActivity(intent);
            }
        });

        buttonMemoryChiffres.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Comming soon !" , Toast.LENGTH_LONG).show();
            }
        });

        buttonMemoryMaison.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Comming soon !" , Toast.LENGTH_LONG).show();
            }
        });

        buttonMemoryForet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Comming soon !" , Toast.LENGTH_LONG).show();
            }
        });

        buttonMemoryVille.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Comming soon !" , Toast.LENGTH_LONG).show();
            }
        });


    }

    private int getScoreJeu(String nameJeu){
        JeuBDD jeuBdd = new JeuBDD(this);
        jeuBdd.open();
        Jeu jeu = jeuBdd.getJeuWithNameJeu(nameJeu);
        int result = jeu.getScorePCT();
        jeuBdd.close();
        return result;
    }

}
