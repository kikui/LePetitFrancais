package com.example.kikui.lepetitfranais.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.kikui.lepetitfranais.R;

public class Memory extends Activity {
    private static int ROW_COUNT = -1;
    private static int COL_COUNT = -1;
    private Context context;
    private Drawable backImage;
    private int [] [] cards;
    private int [] [] count;
    private int id = 0;
    private List<Drawable> images;
    private Card firstCard;
    private Card seconedCard;
    private ButtonListener buttonListener;
    private int count_turn;

    private static Object lock = new Object();

    private TableLayout mainTable;
    private UpdateCardsHandler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String nameGame = intent.getStringExtra("nameGame");

        //-----------------------------TEST-SCORE-BDD-------------------------------------
        upDateScore(1000,nameGame);
        //-----------------------------TEST-SCORE-BDD-------------------------------------

        handler = new UpdateCardsHandler();
        loadImages();
        setContentView(R.layout.activity_memory);

        backImage =  getResources().getDrawable(R.drawable.memory_test_0);

        buttonListener = new ButtonListener();

        mainTable = (TableLayout)findViewById(R.id.TableLayout03);

        context  = mainTable.getContext();

        newGame(nameGame);

    }

    private void newGame(String jeu) {
        ROW_COUNT = 5;
        COL_COUNT = 4;

        cards = new int [COL_COUNT] [ROW_COUNT];

        TableRow tr = ((TableRow)findViewById(R.id.TableRow03));
        tr.removeAllViews();

        mainTable = new TableLayout(context);
        tr.addView(mainTable);

        for (int y = 0; y < ROW_COUNT; y++) {
            mainTable.addView(createRow(y));
        }

        firstCard=null;
        loadCards();

        if (verif()==true){
            int score = getScore();
            TextView textView = (TextView)findViewById(R.id.tVScore);
            textView.setText("Your score : "+String.valueOf(score));
            upDateScore(score,jeu);
        }

    }

    private void loadImages() {
        images = new ArrayList<Drawable>();

        images.add(getResources().getDrawable(R.drawable.memory_test_1));
        images.add(getResources().getDrawable(R.drawable.memory_test_2));
        images.add(getResources().getDrawable(R.drawable.memory_test_3));
        images.add(getResources().getDrawable(R.drawable.memory_test_4));
        images.add(getResources().getDrawable(R.drawable.memory_test_5));
        images.add(getResources().getDrawable(R.drawable.memory_test_6));
        images.add(getResources().getDrawable(R.drawable.memory_test_7));
        images.add(getResources().getDrawable(R.drawable.memory_test_8));
        images.add(getResources().getDrawable(R.drawable.memory_test_9));
        images.add(getResources().getDrawable(R.drawable.memory_test_10));

    }

    private void loadCards(){
        try{
            int size = 20;

            ArrayList<Integer> list = new ArrayList<Integer>();

            for(int i=0;i<size;i++){
                list.add(new Integer(i));
            }


            Random r = new Random();

            for(int i=size-1;i>=0;i--){
                int t=0;

                if(i>0){
                    t = r.nextInt(i);
                }

                t=list.remove(t).intValue();
                cards[i%COL_COUNT][i/COL_COUNT]=t%(size/2);

                Log.i("loadCards()", "card["+(i%COL_COUNT)+
                        "]["+(i/COL_COUNT)+"]=" + cards[i%COL_COUNT][i/COL_COUNT]);
            }
        }
        catch (Exception e) {
            Log.e("loadCards()", e+"");
        }

    }

    private TableRow createRow(int y){
        TableRow row = new TableRow(context);
        row.setHorizontalGravity(Gravity.CENTER);

        for (int x = 0; x < COL_COUNT; x++) {
            row.addView(createImageButton(x,y));
        }
        return row;
    }

    private View createImageButton(int x, int y){
        Button button = new Button(context);
        button.setBackgroundDrawable(backImage);
        button.setId(100*x+y);
        button.setOnClickListener(buttonListener);
        return button;
    }

    class ButtonListener implements OnClickListener {

        @Override
        public void onClick(View v) {

            synchronized (lock) {
                if(firstCard != null && seconedCard != null){
                    return;
                }
                int id = v.getId();
                int x = id/100;
                int y = id%100;
                count = new int [id][count_turn];
                turnCard((Button)v,x,y);
            }

        }

        private void turnCard(Button button,int x, int y) {
            button.setBackgroundDrawable(images.get(cards[x][y]));

            if(firstCard == null){
                firstCard = new Card(button,x,y);
            }
            else{

                if(firstCard.x == x && firstCard.y == y){
                    return; //the user pressed the same card
                }

                seconedCard = new Card(button,x,y);

                TimerTask tt = new TimerTask() {

                    @Override
                    public void run() {
                        try{
                            synchronized (lock) {
                                handler.sendEmptyMessage(0);
                            }
                        }
                        catch (Exception e) {
                            Log.e("E1", e.getMessage());
                        }
                    }
                };

                Timer t = new Timer(false);
                t.schedule(tt, 1000);
            }


        }

    }

    class UpdateCardsHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            synchronized (lock) {
                checkCards();
            }
        }
        public void checkCards(){
            if(cards[seconedCard.x][seconedCard.y] == cards[firstCard.x][firstCard.y]){
                firstCard.button.setVisibility(View.INVISIBLE);
                seconedCard.button.setVisibility(View.INVISIBLE);
            }
            else {
                seconedCard.button.setBackgroundDrawable(backImage);
                firstCard.button.setBackgroundDrawable(backImage);
            }

            firstCard=null;
            seconedCard=null;
        }
    }

    private int getScore(){
        int result=0;
        int n = 0;
        /*for(n,n<405,n++){
            if(getCount()<=2){
                result+=100;
            }
            if(getCount()==3){
                result+=80;
            }
            if(getCount()==4){
                result+=60;
            }
            if(getCount()==5){
                result+=40;
            }
            if(getCount()>5){
                result+=20;
            }
        }*/
        return result;
    }

    private boolean verif(){
        boolean result=false;
        int countCardReturn=0;
        for (int y = 0; y < ROW_COUNT; y++) {
            for (int x = 0; x < COL_COUNT; x++) {
                /*Card verif = new Card(x,y); // ici souci car il faut une imageBouton. et c est sur ce bouton qu on va agir en faite
                if (verif.button.getVisibility()==View.INVISIBLE)
                    countCardReturn++;*/
            }
        }
        if (countCardReturn==20){
            result=true;
        }
        return result;
    }
    private int getCount()
    {
        int temp = 0;
        /*for (count [id] []< 405; id++){
            temp = temp + count [] [count_turn];
        }*/
        count_turn = temp;
        return count_turn;
    }

    private void upDateScore(int score, String jeu){
        JeuBDD jeuBdd = new JeuBDD(this);
        jeuBdd.open();
        Jeu jeuFromBdd = jeuBdd.getJeuWithNameJeu(jeu);
        if (jeuFromBdd.getScore()<score){
            jeuFromBdd.setScore(score);
            jeuBdd.updateJeu(jeuFromBdd.getId(), jeuFromBdd);
        }
        jeuBdd.close();
    }

}