package com.example.kikui.lepetitfranais.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kikui.lepetitfranais.Memory;
import com.example.kikui.lepetitfranais.R;

public class MenuMemory extends AppCompatActivity {

    private Button buttonMemory;
    private int valueAnimaux=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_memory);

        buttonMemory = (Button) findViewById(R.id.button_memory_animaux);
        TextView animauxPCT = (TextView)findViewById(R.id.animauxPCT);

        animauxPCT.setText(String.valueOf(valueAnimaux+"%"));

        buttonMemory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuMemory.this, Memory.class);
                startActivity(intent);
            }
        });

    }

}
