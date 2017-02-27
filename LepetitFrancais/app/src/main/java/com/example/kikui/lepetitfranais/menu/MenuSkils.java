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

        ProgressBar progressBarAnimauxPCT = (ProgressBar) findViewById(R.id.progressBarAnimauxPCT);
        TextView textAnimauxPCT = (TextView)findViewById(R.id.textAnimauxPCT);
        CheckBox checkBoxAnimauxMemory = (CheckBox)findViewById(R.id.checkBoxAnimauxMemory);
        CheckBox checkBoxAnimauxGame2 = (CheckBox)findViewById(R.id.checkBoxChiffresGame2);

        progressBarAnimauxPCT.incrementProgressBy(PCTGlobalAnimaux());
        textAnimauxPCT.setText(String.valueOf(progressBarAnimauxPCT.getProgress()+"% "));

        checked("memoryAnimaux",checkBoxAnimauxMemory);

        progressBarAnimauxPCT.incrementProgressBy(0);
        textAnimauxPCT.setText(String.valueOf(progressBarAnimauxPCT.getProgress()+"% "));

    }

    public int PCTGlobalAnimaux(){
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
        result = (result*100)/4000;
        jeuBdd.close();
        return result;
    }

    public int PCTGlobal(String category){
        JeuBDD jeuBdd = new JeuBDD(this);
        jeuBdd.open();
        jeuBdd.close();
        return 0;
    }

    public void checked(String nomJeu, CheckBox nomCheckBox){
        JeuBDD jeuBdd = new JeuBDD(this);
        jeuBdd.open();
        Jeu jeuFromBdd = jeuBdd.getJeuWithNameJeu(nomJeu);
        if(jeuFromBdd.getScore()==2000){
            nomCheckBox.setChecked(true);
        }
        jeuBdd.close();
    }

}
