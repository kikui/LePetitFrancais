package com.example.kikui.lepetitfranais;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuGame extends AppCompatActivity {

    private LinearLayout layoutPlayMemory;
    private ProgressBar bar;
    private int valueAnimaux;
    SharedPreferences sharedValuePCT;
    private String prefAnimauxPCT;
    private String prefPCT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_game);

        bar = (ProgressBar) findViewById(R.id.progressBar);
        TextView memoryPCT = (TextView)findViewById(R.id.memoryPCT);

        sharedValuePCT = getBaseContext().getSharedPreferences(prefPCT, MODE_PRIVATE);
        int valueAnimaux = sharedValuePCT.getInt(prefAnimauxPCT, 0);

        bar.incrementProgressBy(valueAnimaux);
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
}
