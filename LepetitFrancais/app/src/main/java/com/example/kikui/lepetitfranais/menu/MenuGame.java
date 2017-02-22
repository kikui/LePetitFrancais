package com.example.kikui.lepetitfranais.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kikui.lepetitfranais.R;

public class MenuGame extends AppCompatActivity {

    private LinearLayout layoutPlayMemory;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_game);

        bar = (ProgressBar) findViewById(R.id.progressBar);
        TextView memoryPCT = (TextView)findViewById(R.id.memoryPCT);

        bar.incrementProgressBy(5);
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
