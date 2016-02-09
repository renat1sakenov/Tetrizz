package com.example.renat.tetris.drawer;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.renat.tetris.Main;

/**
 * Created by Renat on 09.10.2015.
 */
public class ComboCounterDrawer {


    private static int counter = 0;
    private static Paint paint;

    private static int POS_X = 10;
    private static int POS_Y = 70;

    public static boolean DRAW;


    public static void init(){
        paint = new Paint();
        paint.setTextSize(70);
        paint.setTypeface(Main.face);
    }


    public static void setCounter(int counter_){
        counter = counter_;
    }



    public static void draw(Canvas canvas){

        canvas.drawText("Combos: " + counter,POS_X,POS_Y,paint);

    }



}
