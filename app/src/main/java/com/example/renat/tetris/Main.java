package com.example.renat.tetris;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Renat on 14.07.2015.
 */
public class Main extends Activity {

    private static String TAG = "MAIN_ACTIVITY";

    public static Typeface face;

    @Override
    protected void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.main);
        Log.i(TAG, "onCreate");

        Typeface face_ = Typeface.createFromAsset(getAssets(), "fonts/Leipzig Fraktur Bold.ttf");

        face = face_;

        TextView title = (TextView) findViewById(R.id.Title);
        title.setTypeface(face);

        Button startbutton = (Button) findViewById(R.id.PlayButton);
        startbutton.setTypeface(face);

        Button beenden = (Button) findViewById(R.id.ExitButton);
        beenden.setTypeface(face);

        Button menu = (Button) findViewById(R.id.MenuButton);
        menu.setTypeface(face);

        Button levelButton = (Button) findViewById(R.id.LevelButton);
        levelButton.setTypeface(face);

        String error = Data.init(getApplicationContext());
        if(!error.isEmpty()) {
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            Data.HIGHSCORE = 0;
            Data.SECOND_HIGHSCORE = 0;
            Data.setDefaultOptions();
            Data.CURRENT_LEVEL = 1;
            Data.POINTS = 0;
        }
    }

    @Override
    protected void onStart(){
        super.onStart();

        Log.i(TAG, "onStart");
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

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG,"onRestart");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        try {
            Data.writeData(getApplicationContext());
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"ERROR " + e.getMessage(),Toast.LENGTH_LONG).show();
        }

        Log.i(TAG, "onDestroy");
    }

    /**
     * Will be called when the player presses the 'Play-Button'
     * Starts the Game-Activity
     * @param view
     */
    public void playClick(View view){
        ClassicMenu.SHOW = true;
        Intent intent = new Intent(this, ClassicMenu.class);
        startActivity(intent);
    }

    public void levelClick(View view){
        Intent intent = new Intent(this, LevelMenu.class);
        startActivity(intent);
    }

    public void openMenu(View view){
        Intent intent = new Intent(this, OptionMenu.class);
        startActivity(intent);
    }


   public void ExitButtonClick(View view){
       finish();
   }
}
