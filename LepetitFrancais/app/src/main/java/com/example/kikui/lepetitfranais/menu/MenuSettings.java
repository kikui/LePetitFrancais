package com.example.kikui.lepetitfranais.menu;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kikui.lepetitfranais.R;
import com.example.kikui.lepetitfranais.module.Jeu;
import com.example.kikui.lepetitfranais.module.JeuBDD;

public class MenuSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_settings);
    }
}
