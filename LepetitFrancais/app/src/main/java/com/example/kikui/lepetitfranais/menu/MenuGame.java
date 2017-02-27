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
    private LinearLayout layoutPlayGame2;
    private ProgressBar pregressBarMemory;
    private ProgressBar pregressBarGame2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_game);

        pregressBarMemory = (ProgressBar) findViewById(R.id.progressBar);
        TextView memoryPCT = (TextView)findViewById(R.id.memoryPCT);
        pregressBarGame2 = (ProgressBar) findViewById(R.id.progressBar2);
        TextView game2PCT = (TextView) findViewById(R.id.game2PCT);

        pregressBarMemory.setProgress(PCTGlobal("memory"));
        memoryPCT.setText(String.valueOf(pregressBarMemory.getProgress()+"% "));

        pregressBarGame2.setProgress(PCTGlobal("gameDeux"));
        game2PCT.setText(String.valueOf(pregressBarMemory.getProgress()+"% "));

        layoutPlayMemory = (LinearLayout) findViewById(R.id.playMenuMemory);
        layoutPlayMemory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuGame.this, MenuMemory.class);
                startActivity(intent);
            }
        });

        layoutPlayGame2 = (LinearLayout) findViewById(R.id.playMenuGame2);
        layoutPlayGame2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Comming soon !" , Toast.LENGTH_LONG).show();
            }
        });
    }

    public int PCTGlobal(String category){
        JeuBDD jeuBdd = new JeuBDD(this);
        jeuBdd.open();
        int result = jeuBdd.getScoresWithCategory(category);
        jeuBdd.close();
        return result;
    }
}
