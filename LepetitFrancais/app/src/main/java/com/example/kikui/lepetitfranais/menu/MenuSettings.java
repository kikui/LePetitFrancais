package com.example.kikui.lepetitfranais.menu;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kikui.lepetitfranais.R;
import com.example.kikui.lepetitfranais.module.DataBase;
import com.example.kikui.lepetitfranais.module.Jeu;

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
