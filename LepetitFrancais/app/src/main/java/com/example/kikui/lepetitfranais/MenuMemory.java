package com.example.kikui.lepetitfranais;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuMemory extends AppCompatActivity {

    private Button buttonMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_memory);

        buttonMemory = (Button) findViewById(R.id.button_memory_animaux);

        buttonMemory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuMemory.this, Memory.class);
                startActivity(intent);
            }
        });

    }



}
