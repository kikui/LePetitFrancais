package com.example.kikui.lepetitfranais.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.kikui.lepetitfranais.R;
import com.example.kikui.lepetitfranais.module.Jeu;
import com.example.kikui.lepetitfranais.module.JeuBDD;

public class MenuSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_success);

        ImageView image1 = (ImageView) findViewById(R.id.imageView22);
        ImageView image2 = (ImageView) findViewById(R.id.imageView23);
        ImageView image3 = (ImageView) findViewById(R.id.imageView24);
        ImageView image4 = (ImageView) findViewById(R.id.imageView25);

        if(getScore()>=25&&getScore()<50){
            image1.getResources().getDrawable(R.drawable.rsz_chien14);
        }else if(getScore()>=50&&getScore()<75){
            image1.getResources().getDrawable(R.drawable.rsz_chien14);
            image2.getResources().getDrawable(R.drawable.rsz_chien24);
        }else if(getScore()>=75&&getScore()<100){
            image1.getResources().getDrawable(R.drawable.rsz_chien14);
            image2.getResources().getDrawable(R.drawable.rsz_chien24);
            image3.getResources().getDrawable(R.drawable.rsz_chien34);
        }else if(getScore()==100){
            image1.getResources().getDrawable(R.drawable.rsz_chien14);
            image2.getResources().getDrawable(R.drawable.rsz_chien24);
            image3.getResources().getDrawable(R.drawable.rsz_chien34);
            image4.getResources().getDrawable(R.drawable.rsz_chien44);
        }

    }

    private int getScore(){
        int result=0;
        JeuBDD jeuBdd = new JeuBDD(this);
        jeuBdd.open();
        Jeu jeuFromBdd = jeuBdd.getJeuWithNameJeu("memoryAnimaux");
        result = jeuFromBdd.getScorePCT();
        jeuBdd.close();
        return result;
    }
}
