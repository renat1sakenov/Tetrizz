package com.example.renat.tetris.drawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.renat.tetris.ImageLoader;
import com.example.renat.tetris.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Renat on 27.08.2015.
 */
public class CountDownDrawer {

    public static boolean DRAW;

    private static Bitmap clock;
    private static Paint paint;
    private static String countdown;
    private static final int CLOCK_HEIGHT = 410;
    private static final int CLOCK_WIDTH = 360;


    public static void init(Context context){
        clock = ImageLoader.getImage(ImageLoader.Imagename.CLOCK, CLOCK_WIDTH, CLOCK_HEIGHT);

        paint = new Paint();
        paint.setTextSize(70);

    }


    public static void draw(Canvas canvas){

        canvas.drawBitmap(clock, GameView.WIDTH / 2 - clock.getWidth() / 2, 1, paint);

        canvas.drawText(countdown, (GameView.WIDTH / 3) * 2, 100, paint);

    }


    public synchronized static void setCountdown(String countdown_){
        countdown = countdown_;
    }

}
