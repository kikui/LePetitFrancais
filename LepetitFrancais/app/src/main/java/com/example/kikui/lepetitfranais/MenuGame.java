package com.example.kikui.lepetitfranais;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;

public class MenuGame extends AppCompatActivity {

    private TableRow RowPlayMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_game);

        RowPlayMemory = (TableRow) findViewById(R.id.playMenuMemory);
        RowPlayMemory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuGame.this, MenuMemory.class);
                startActivity(intent);
            }
        });
    }
}
