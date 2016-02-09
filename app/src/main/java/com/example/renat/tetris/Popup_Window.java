package com.example.renat.tetris;


import android.app.Activity;;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Renat on 15.08.2015.
 */
public class Popup_Window extends Activity {

    private static String msg;
    private Button yes_button;
    private TextView text;

    private static Game_Activity game;

    public static void init(String msg_, Game_Activity game_){
        msg = msg_;
        game = game_;
    }



    @Override
    protected void onCreate(Bundle saveInstances){
        super.onCreate(saveInstances);
        setContentView(R.layout.popup_window);

        yes_button = (Button) findViewById(R.id.yes_button);
        yes_button.setTypeface(Main.face);


        text = (TextView) findViewById(R.id.popup_text);
        text.setText(msg);
        text.setTypeface(Main.face);


        getWindow().setLayout((int)(game.getWindowDim().x * .6),(int)(game.getWindowDim().y * .4));
    }


    public void exit_yes(View view){
        game.finish();
        finish();
    }


    /*public void exit_no(View view){
        game.finish();
        finish();
    }
*/
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
