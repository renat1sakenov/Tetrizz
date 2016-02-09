package com.example.renat.tetris;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.renat.tetris.game.World;
import com.example.renat.tetris.game.WorldSize;

/**
 * Created by Renat on 02.10.2015.
 */
public class SelectSizeMenu extends Activity {


    public void onCreate(Bundle bundle){

        super.onCreate(bundle);
        setContentView(R.layout.select_size_menu);


        Button vb = (Button) findViewById(R.id.very_big_button);
        Button b = (Button) findViewById(R.id.big_button);
        Button d = (Button) findViewById(R.id.default_button);
        Button s = (Button) findViewById(R.id.small_button);
        Button vs = (Button) findViewById(R.id.very_small_button);

        vb.setTypeface(Main.face);
        b.setTypeface(Main.face);
        d.setTypeface(Main.face);
        s.setTypeface(Main.face);
        vs.setTypeface(Main.face);

    }

    public void vb_click(View view){
        Data.SIZE = WorldSize.Size.VERY_BIG;
        finish();
    }

    public void b_click(View view){
        Data.SIZE = WorldSize.Size.BIG;
        finish();
    }

    public void d_click(View view){
        Data.SIZE = WorldSize.Size.DEFAULT;
        finish();
    }


    public void s_click(View view){
        Data.SIZE = WorldSize.Size.SMALL;
        finish();
    }

    public void vs_click(View view){
        Data.SIZE = WorldSize.Size.VERY_SMALL;
        finish();
    }
}
