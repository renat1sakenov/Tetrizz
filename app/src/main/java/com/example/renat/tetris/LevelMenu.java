package com.example.renat.tetris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.renat.tetris.drawer.GameView;

import java.util.ArrayList;


/**
 * Created by Renat on 23.08.2015.
 */
public class LevelMenu extends Activity{


    private ArrayList<Button> lvlbuttons;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_menu);

        makeLayout();
    }

    @Override
    protected void onResume(){
        super.onResume();

        makeLayout();
    }


    public void startLevel_1(View view){
        Game_Activity.setLevel(1);
        startLevel();
    }


    public void startLevel_2(View view){
        Game_Activity.setLevel(2);
        startLevel();
    }

    public void startLevel_3(View view){
        Game_Activity.setLevel(3);
        startLevel();
    }

    public void startLevel_4(View view){
        Game_Activity.setLevel(4);
        startLevel();
    }

    public void startLevel_5(View view){
        Game_Activity.setLevel(5);
        startLevel();
    }

    public void startLevel(){
        Intent intent = new Intent(this,Game_Activity.class);
        startActivity(intent);
    }

    public static void set_new_level_enabled(int index){
        if(index == Data.CURRENT_LEVEL)
            Data.CURRENT_LEVEL ++;
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();

        Game_Activity.setLevel(0);

    }


    public void makeLayout(){
        lvlbuttons = new ArrayList<>();

        TextView text = (TextView) findViewById(R.id.text_level_menu);
        text.setTypeface(Main.face);

        Button lvl1 = (Button) findViewById(R.id.lvl1_button);
        lvlbuttons.add(lvl1);
        Button lvl2 = (Button) findViewById(R.id.lvl2_button);
        lvlbuttons.add(lvl2);
        Button lvl3 = (Button) findViewById(R.id.lvl3_button);
        lvlbuttons.add(lvl3);
        Button lvl4 = (Button) findViewById(R.id.lvl4_button);
        lvlbuttons.add(lvl4);
        Button lvl5 = (Button) findViewById(R.id.lvl5_button);
        lvlbuttons.add(lvl5);

        for(Button b : lvlbuttons){
            b.setTypeface(Main.face);
        //    if(lvlbuttons.indexOf(b) + 1 > Data.CURRENT_LEVEL)
        //       b.setEnabled(false);
        }


    }
}
