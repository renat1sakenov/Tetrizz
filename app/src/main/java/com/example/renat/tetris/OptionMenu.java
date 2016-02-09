package com.example.renat.tetris;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renat.tetris.game.World;
import com.example.renat.tetris.game.WorldSize;

import java.util.Arrays;

/**
 * Created by Renat on 17.08.2015.
 */
public class OptionMenu extends Activity {

    private CheckBox steering;
    private CheckBox music;



    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.optionmenu);

        TextView titel = (TextView) findViewById(R.id.headline);
        steering = (CheckBox) findViewById(R.id.steeringbutton);
        music = (CheckBox) findViewById(R.id.musicradio);
        Button back = (Button) findViewById(R.id.back_button);

        titel.setTypeface(Main.face);
        steering.setTypeface(Main.face);
        music.setTypeface(Main.face);
        back.setTypeface(Main.face);


        steering.setChecked(Data.USE_BUTTONS);
        music.setChecked(Data.MUSIC_ON);


    }

    public void back_buttonClick (View view){
        finish();
    }

    @Override
    protected void onDestroy(){

        super.onDestroy();

        Data.USE_BUTTONS = steering.isChecked();
        Data.MUSIC_ON = music.isChecked();

    }

}

