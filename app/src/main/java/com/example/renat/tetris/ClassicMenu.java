package com.example.renat.tetris;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renat.tetris.game.WorldSize;

import java.util.Arrays;

/**
 * Created by Renat on 21.08.2015.
 */
public class ClassicMenu extends Activity {

    private static final String TAG = "CLASSIC_MENU";


    public static boolean SHOW;

    private Button start_button;
    private Button highscore_button;
    private Button back_button;
    private Button sbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classic_menu);

        start_button = (Button) findViewById(R.id.start_button);
        highscore_button = (Button) findViewById(R.id.highscore_button);
        back_button = (Button) findViewById(R.id.backbutton);
        sbutton = (Button) findViewById(R.id.sizebutton);


        start_button.setTypeface(Main.face);
        highscore_button.setTypeface(Main.face);
        back_button.setTypeface(Main.face);
        sbutton.setTypeface(Main.face);



        Log.i(TAG, "onCreate");

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onRestart(){
        super.onRestart();

        if(!SHOW)
            finish();

        Log.i(TAG,"onRestart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG,"onPause");
    }




    public void start(View view){
        Intent intent = new Intent(this, Game_Activity.class);
        startActivity(intent);
    }


    public void highscoreClick(View view){
        Toast.makeText(getApplicationContext(), "Highscore: " + Data.HIGHSCORE, Toast.LENGTH_SHORT).show();
    }

    public void sizeClick (View view)
    {
        Intent intent = new Intent(this, SelectSizeMenu.class);
        startActivity(intent);

    }


    public void backClick (View view){
        finish();
    }
}
