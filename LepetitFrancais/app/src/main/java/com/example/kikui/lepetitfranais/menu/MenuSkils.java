package com.example.kikui.lepetitfranais.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kikui.lepetitfranais.R;
import com.example.kikui.lepetitfranais.module.Jeu;
import com.example.kikui.lepetitfranais.module.JeuBDD;

public class MenuSkils extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_skils);

        ProgressBar progressBarMemoryPCT = (ProgressBar) findViewById(R.id.progressBarMemoryPCT);
        TextView textMemoryPCT = (TextView)findViewById(R.id.textMemoryPCT);
        CheckBox checkBoxAnimaux = (CheckBox)findViewById(R.id.checkBoxAnimaux);
        CheckBox checkBoxChiffres = (CheckBox)findViewById(R.id.checkBoxChiffres);

        progressBarMemoryPCT.incrementProgressBy(PCTGlobalmemory());
        textMemoryPCT.setText(String.valueOf(progressBarMemoryPCT.getProgress()+"% "));

        checked("memoryAnimaux");

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

    public void checked(String nomJeu){
        JeuBDD jeuBdd = new JeuBDD(this);
        jeuBdd.open();
        Jeu jeuFromBdd = jeuBdd.getJeuWithNameJeu(nomJeu);
        if(jeuFromBdd.getScore()==2000){
            checkBoxAnimaux.setChecked(true);
        }
        jeuBdd.close();
    }

}
