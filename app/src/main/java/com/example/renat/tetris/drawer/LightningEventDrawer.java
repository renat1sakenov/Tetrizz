package com.example.renat.tetris.drawer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.renat.tetris.Data;
import com.example.renat.tetris.Game_Activity;
import com.example.renat.tetris.R;


/**
 * Created by Renat on 29.08.2015.
 */
public class LightningEventDrawer {

    public static boolean DRAW = false;

    public static float lightning_x;
    public static float lightning_y;
    private static GameView gv;
    private static GifView lightning_gif;



    public static void init(GameView gv_) {
        gv = gv_;

        lightning_gif = new GifView(gv,"lightning.gif",EventDrawer.LIGHTNING);

        lightning_x = 0;
        lightning_y = 0;


    }


    public static void draw_lightning(Canvas canvas){
        lightning_gif.draw(canvas,lightning_x,lightning_y);
    }

}
