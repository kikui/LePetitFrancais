package com.example.kikui.lepetitfranais.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kikui.lepetitfranais.R;
import com.example.kikui.lepetitfranais.module.Jeu;
import com.example.kikui.lepetitfranais.module.JeuBDD;

public class MenuGame extends AppCompatActivity {

    private LinearLayout layoutPlayMemory;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_game);

        bar = (ProgressBar) findViewById(R.id.progressBar);
        TextView memoryPCT = (TextView)findViewById(R.id.memoryPCT);

        bar.incrementProgressBy(PCTGlobalmemory());
        memoryPCT.setText(String.valueOf(bar.getProgress()+"% "));

        layoutPlayMemory = (LinearLayout) findViewById(R.id.playMenuMemory);
        layoutPlayMemory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuGame.this, MenuMemory.class);
                startActivity(intent);
            }
        });
    }

    public int PCTGlobalmemory(){
        //---------------------------BDD---------------------------
        JeuBDD jeuBdd = new JeuBDD(this);
        jeuBdd.open();
        Jeu memoryAnimauxFromBdd = jeuBdd.getJeuWithNameJeu("memoryAnimaux");
        Jeu memoryChiffresFromBdd = jeuBdd.getJeuWithNameJeu("memoryChiffres");
        if(memoryAnimauxFromBdd==null){
            Jeu memoryAnimaux = new Jeu("memoryAnimaux",0);
            jeuBdd.insertJeu(memoryAnimaux);
            memoryAnimauxFromBdd = jeuBdd.getJeuWithNameJeu("memoryAnimaux");
        }
        if(memoryChiffresFromBdd==null){
            Jeu memoryChiffres = new Jeu("memoryChiffres",0);
            jeuBdd.insertJeu(memoryChiffres);
            memoryChiffresFromBdd = jeuBdd.getJeuWithNameJeu("memoryChiffres");
        }
        int result = memoryAnimauxFromBdd.getScore()+memoryChiffresFromBdd.getScore();
        jeuBdd.close();
        //---------------------------BDD---------------------------
        return result;
    }
}
