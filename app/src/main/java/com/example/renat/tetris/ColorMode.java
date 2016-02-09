package com.example.renat.tetris;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.renat.tetris.game.TetrisBlock;

import java.util.Random;

/**
 * Created by Renat on 25.08.2015.
 */
public class ColorMode {

    public enum Color_Mode{
        NORMAL,
        COLORFUL;
    }


    public static Color_Mode MODE = Color_Mode.NORMAL;

    public static void settingColor(TetrisBlock b, Paint paint){
        switch (MODE){
            case NORMAL:
                setting_normal_colors(b, paint);
                break;
            case COLORFUL:
                setting_rad_colors(b,paint);
                break;
        }
    }


    public static void setting_rad_colors(TetrisBlock b, Paint paint){
        Random rand = new Random();
        int random_red = rand.nextInt(255) + 1;
        int random_green = rand.nextInt(255) + 1;
        int random_blue = rand.nextInt(255) + 1;
        if(b != TetrisBlock.EMPTY) {
            paint.setARGB(255, random_red, random_green, random_blue);
        }else{
            paint.setARGB(50, random_red, random_green, random_blue);
        }
    }

    public static void setting_normal_colors(TetrisBlock b, Paint paint){
        switch (b){
            case Z:
                paint.setColor(Color.RED);
                break;
            case SNAKE:
                paint.setColor(Color.GREEN);
                break;
            case T:
                paint.setColor(Color.BLACK);
                break;
            case SQUARE:
                paint.setColor(Color.BLUE);
                break;
            case L:
                paint.setColor(Color.CYAN);
                break;
            case Backwards_L:
                paint.setColor(Color.YELLOW);
                break;
            case Backwards_Z:
                paint.setColor(Color.MAGENTA);
                break;
            case EMPTY:
                paint.setColor(Color.TRANSPARENT);
                break;
        }
    }
}
