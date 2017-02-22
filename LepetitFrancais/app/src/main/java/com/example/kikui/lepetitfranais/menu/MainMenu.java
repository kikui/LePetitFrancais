package com.example.kikui.lepetitfranais.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kikui.lepetitfranais.R;

public class MainMenu extends AppCompatActivity {

    private Button button_game;
    private Button button_success;
    private Button button_skils;
    private Button button_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        button_game = (Button) findViewById(R.id.button_game);
        button_game.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MenuGame.class);
                startActivity(intent);
            }
        });

        button_success = (Button) findViewById(R.id.button_success);
        button_success.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MenuSuccess.class);
                startActivity(intent);
            }
        });

        button_skils = (Button) findViewById(R.id.button_skils);
        button_skils.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MenuSkils.class);
                startActivity(intent);
            }
        });

        button_settings = (Button) findViewById(R.id.button_settings);
        button_settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MenuSettings.class);
                startActivity(intent);
            }
        });

    }
}
