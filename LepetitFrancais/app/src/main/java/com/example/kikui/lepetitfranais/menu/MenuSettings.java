package com.example.kikui.lepetitfranais.menu;


import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kikui.lepetitfranais.R;
import com.example.kikui.lepetitfranais.module.Score;
import com.example.kikui.lepetitfranais.module.ScoreDAO;

public class MenuSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_settings);

        final TextView textview2 = (TextView)findViewById(R.id.textView4);
        final TextView textview5 = (TextView)findViewById(R.id.textView5);
        Button button2 = (Button) findViewById(R.id.button2);
        ProgressBar progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);

        final ScoreDAO test = new ScoreDAO(this);
        test.open();
        final Score memory = new Score(1, "memoryAnimaux",1500);
        int nb = 0;

        /*try{
            Cursor c = test.selectionner(1);
            textview2.setText("c'est bon pour la selection");
        }catch (Exception e){
            textview2.setText("problème avec  la sélection");
        }*/


        //textview2.setText(c.getString(1));

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                    test.ajouter(memory);
                    textview5.setText("c'est bon pour la création d un champs dans la base");
                }catch (Exception e){
                    textview5.setText("il n'a pas créer la base");
                }
            }
        });
        test.close();


        progressBar3.setProgress(nb);
        //textview2.setText(String.valueOf(progressBar3.getProgress()+"% "));

    }
}
