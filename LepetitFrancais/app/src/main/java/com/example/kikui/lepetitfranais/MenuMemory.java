package com.example.kikui.lepetitfranais;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuMemory extends AppCompatActivity {

    private Button buttonMemory;
    SharedPreferences sharedValuePCT;
    public String prefAnimauxPCT="animauxPCT";
    private String prefPCT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_memory);

        buttonMemory = (Button) findViewById(R.id.button_memory_animaux);
        TextView animauxPCT = (TextView)findViewById(R.id.animauxPCT);

        sharedValuePCT = getBaseContext().getSharedPreferences(prefPCT, MODE_PRIVATE);
        sharedValuePCT.edit().putInt(prefAnimauxPCT, 25).apply();
        int valueAnimaux = sharedValuePCT.getInt(prefAnimauxPCT, 0);

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
