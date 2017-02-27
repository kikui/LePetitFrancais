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

        verify();

        ProgressBar progressBarAnimauxPCT = (ProgressBar) findViewById(R.id.progressBarAnimauxPCT);
        TextView textAnimauxPCT = (TextView)findViewById(R.id.textAnimauxPCT);
        CheckBox checkBoxAnimauxMemory = (CheckBox)findViewById(R.id.checkBoxAnimauxMemory);
        CheckBox checkBoxAnimauxGame2 = (CheckBox)findViewById(R.id.checkBoxAnimauxGame2);

        ProgressBar progressBarChiffresPCT = (ProgressBar) findViewById(R.id.progressBarChiffresPCT);
        TextView textChiffresPCT = (TextView)findViewById(R.id.textChiffresPCT);
        CheckBox checkBoxChiffresMemory = (CheckBox)findViewById(R.id.checkBoxChiffresMemory);
        CheckBox checkBoxChiffresGame2 = (CheckBox)findViewById(R.id.checkBoxChiffresGame2);

        progressBarAnimauxPCT.setProgress(PCTGlobal("Animaux"));
        textAnimauxPCT.setText(String.valueOf(progressBarAnimauxPCT.getProgress()+"% "));

        checked("memoryAnimaux",checkBoxAnimauxMemory);
        checked("gameDeuxAnimaux",checkBoxAnimauxGame2);
        checked("memoryChiffres",checkBoxChiffresMemory);
        checked("gameDeuxChiffres",checkBoxChiffresGame2);

        progressBarChiffresPCT.setProgress(PCTGlobal("Chiffres"));
        textChiffresPCT.setText(String.valueOf(progressBarChiffresPCT.getProgress()+"% "));

    }

    public int PCTGlobal(String category){
        JeuBDD jeuBdd = new JeuBDD(this);
        jeuBdd.open();
        int result = jeuBdd.getScoresWithCategory(category);
        jeuBdd.close();
        return result;
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

    public void verify(){
        JeuBDD jeuBdd = new JeuBDD(this);
        jeuBdd.open();
        Jeu memoryAnimauxFromBdd = jeuBdd.getJeuWithNameJeu("memoryAnimaux");
        Jeu gameDeuxAnimauxFromBdd = jeuBdd.getJeuWithNameJeu("gameDeuxAnimaux");
        Jeu memoryChiffresFromBdd = jeuBdd.getJeuWithNameJeu("memoryChiffres");
        Jeu gameDeuxChiffresFromBdd = jeuBdd.getJeuWithNameJeu("gameDeuxChiffres");
        Jeu animaux = new Jeu("Animaux",0);
        Jeu chiffres = new Jeu("Chiffres",0);
        jeuBdd.insertJeu(animaux);
        jeuBdd.insertJeu(chiffres);

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
