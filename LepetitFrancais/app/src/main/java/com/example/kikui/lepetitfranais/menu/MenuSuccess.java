package com.example.kikui.lepetitfranais.menu;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.kikui.lepetitfranais.R;
import com.example.kikui.lepetitfranais.module.Jeu;
import com.example.kikui.lepetitfranais.module.JeuBDD;

import java.util.ArrayList;

public class MenuSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_success);

        ImageView image1 = (ImageView) findViewById(R.id.imageView22);
        ImageView image2 = (ImageView) findViewById(R.id.imageView23);
        ImageView image3 = (ImageView) findViewById(R.id.imageView24);
        ImageView image4 = (ImageView) findViewById(R.id.imageView25);
        String jeu = "memoryAnimaux";

        image1.setImageResource(R.drawable.card_background_version2);
        image2.setImageResource(R.drawable.card_background_version2);
        image3.setImageResource(R.drawable.card_background_version2);
        image4.setImageResource(R.drawable.card_background_version2);

        verifScore(jeu);

    }

    private int getScore(String jeu){
        int result=0;
        JeuBDD jeuBdd = new JeuBDD(this);
        jeuBdd.open();
        Jeu jeuFromBdd = jeuBdd.getJeuWithNameJeu(jeu);
        result = jeuFromBdd.getScorePCT();
        jeuBdd.close();
        return result;
    }

    private void verifScore(String jeu){

        ImageView image1 = (ImageView) findViewById(R.id.imageView22);
        ImageView image2 = (ImageView) findViewById(R.id.imageView23);
        ImageView image3 = (ImageView) findViewById(R.id.imageView24);
        ImageView image4 = (ImageView) findViewById(R.id.imageView25);

        if(getScore(jeu)>=25&&getScore(jeu)<50){
            image1.setImageResource(R.drawable.rsz_chien14);
        }else if(getScore(jeu)>=50&&getScore(jeu)<75){
            image1.setImageResource(R.drawable.rsz_chien14);
            image2.setImageResource(R.drawable.rsz_chien24);
        }else if(getScore(jeu)>=75&&getScore(jeu)<100){
            image1.setImageResource(R.drawable.rsz_chien14);
            image2.setImageResource(R.drawable.rsz_chien24);
            image3.setImageResource(R.drawable.rsz_chien34);
        }else if(getScore(jeu)==100) {
            image1.setImageResource(R.drawable.rsz_chien14);
            image2.setImageResource(R.drawable.rsz_chien24);
            image3.setImageResource(R.drawable.rsz_chien34);
            image4.setImageResource(R.drawable.rsz_chien44);
        }
    }

}
