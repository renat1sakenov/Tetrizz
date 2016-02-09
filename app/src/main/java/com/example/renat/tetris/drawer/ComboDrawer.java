package com.example.renat.tetris.drawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.Image;

import com.example.renat.tetris.ImageLoader;
import com.example.renat.tetris.R;
import com.example.renat.tetris.game.World;

/**
 * Created by Renat on 29.08.2015.
 */
public class ComboDrawer {

    private static Context context;
    public static boolean DRAW;
    private static final int COMBO_HEIGHT = 100;
    private static final int COMBO_WIDTH = 150;

    private static Paint alphaPaint;
    private static int alpha;
    private static int alpha_step = 5;
    private static Bitmap combo;

    private static int combo_x;
    private static int combo_y;

    public static void init(Context context_){
        context = context_;
        DRAW = false;
        combo = ImageLoader.getImage(ImageLoader.Imagename.COMBO,COMBO_WIDTH,COMBO_HEIGHT);

        alphaPaint = new Paint();
    }

    public static void combo_event(int x, int y){
        alpha = 255;
        alphaPaint.setAlpha(alpha);
        combo_x = x;
        combo_y = y;

        DRAW = true;
    }

    public static void draw(GameView gv, Canvas canvas){

        canvas.drawBitmap(combo,combo_x * GameView.BLOCK_SIZE_X, (combo_y- World.DRAWING_OFFSET) * GameView.BLOCK_SIZE_Y - COMBO_HEIGHT,alphaPaint);

        if(alpha > 0){
            alpha -= alpha_step;
            alphaPaint.setAlpha(alpha);

            gv.postInvalidate();
        }else{
            DRAW = false;
        }
    }
}
