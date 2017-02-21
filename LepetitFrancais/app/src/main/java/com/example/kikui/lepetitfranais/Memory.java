package com.example.kikui.lepetitfranais;

/*  need a new emoticone ?
        (V)(;,,;)(V)
    why not zoidberg ?? */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import java.util.Random;

public class Memory extends AppCompatActivity implements View.OnClickListener
{

    private int numberOfElements;
    private MemoryButton[] buttons;
    private int[] buttonGraphicLocations;
    private int[] buttonGraphics;
    private MemoryButton selectedButton1;
    private MemoryButton selectedButton2;
    private boolean isBusy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_memory);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.activity_game_memory);
        int numColums = gridLayout.getColumnCount();
        int numRows = gridLayout.getRowCount();

        numberOfElements = numColums * numRows;

        buttons = new MemoryButton[numberOfElements];
        buttonGraphics = new int[numberOfElements / 2];

        buttonGraphics[0] = R.drawable.memory_test_1;
        buttonGraphics[1] = R.drawable.memory_test_2;
        buttonGraphics[2] = R.drawable.memory_test_3;
        buttonGraphics[3] = R.drawable.memory_test_4;
        buttonGraphics[4] = R.drawable.memory_test_5;
        buttonGraphics[5] = R.drawable.memory_test_6;
        buttonGraphics[6] = R.drawable.memory_test_7;
        buttonGraphics[7] = R.drawable.memory_test_8;
        buttonGraphics[8] = R.drawable.memory_test_9;
        buttonGraphics[9] = R.drawable.memory_test_10;

        buttonGraphicLocations = new int[numberOfElements];

        shuffleButtonGraphics();

        for(int r = 0; r < numRows; r++)
        {
            for(int c = 0; c < numColums; c++)
            {
                MemoryButton tempButton = new MemoryButton(this, r, c, buttonGraphics[buttonGraphicLocations[r * numColums + c] ]);
                gridLayout.addView(tempButton);
            }
        }
    }

    protected void shuffleButtonGraphics()
    {

        Random rand = new Random();

        for(int i = 0; i < numberOfElements; i++)
        {
            buttonGraphicLocations[i] = i % (numberOfElements / 2);
        }

        for(int i = 0; i < numberOfElements; i++)
        {
            int temp = buttonGraphicLocations[i];
            int swapIntex = rand.nextInt(20);
            buttonGraphicLocations[i] = buttonGraphicLocations[swapIntex];
            buttonGraphicLocations[swapIntex] = temp;
        }

    }

    @Override
    public void onClick(View view) {

    }
}
