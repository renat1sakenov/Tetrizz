package com.example.renat.tetris;

import android.util.Log;

import com.example.renat.tetris.drawer.GameView;
import com.example.renat.tetris.game.FallingBlock;
import com.example.renat.tetris.game.UserAction;
import com.example.renat.tetris.game.World;


/**
 * Created by Renat on 16.08.2015.
 */
public class TouchHandler {


    private static int SCREEN_WIDTH;

    private static float DELTA_X = 1 * GameView.BLOCK_SIZE_X;
    private static float DELTA_Y = 2 * GameView.BLOCK_SIZE_Y;
    private static float posX;
    private static float posY;

    private static final String TAG = "TOUCH_HANDLER";


    public static void init(int x){
        SCREEN_WIDTH = x;
    }

    public static void action(float x, float y, FallingBlock block){

        if(x < 0 || y < 0 || block == null){
            Log.e(TAG,"Invalid parameters for TouchHandler.action");
            return;
        }


        posX = GameView.BLOCK_SIZE_X * block.getPos().x;
        posY = GameView.BLOCK_SIZE_Y * (block.getPos().y - World.DRAWING_OFFSET);
        

        if(touch_under_block(y) && touch_on_same_width(x)) {
            UserAction.place(block);
        }
        else if (touch_on_same_width(x) && touch_on_same_height(y)) {
            try {
                UserAction.rotate(block);
            }catch (IllegalArgumentException e){

            }
        }
        else if (touch_right_from_block(x)) {
            try {
                UserAction.goRight(block);
            }catch (IllegalArgumentException e){

            }
        }
        else if (touch_left_from_block(x)) {
            try {
                UserAction.goLeft(block);
            }catch(IllegalArgumentException e){

            }
        }

    }

    private static boolean touch_on_same_height(float y){
        return !touch_above_block(y) && !touch_under_block(y);
    }

    private static boolean touch_on_same_width(float x){
        return !touch_right_from_block(x) && !touch_left_from_block(x);
    }

    private static boolean touch_right_from_block(float x){
        return posX + DELTA_X  < x;
    }

    private static boolean touch_left_from_block(float x){
        return posX - DELTA_X > x;
    }

    private static boolean touch_under_block(float y){
        return posY + DELTA_Y < y;
    }

    private static boolean touch_above_block(float y){
        return posY - DELTA_Y > y;
    }

    private static boolean move_left(float x){
        return x <= SCREEN_WIDTH / 2;
    }

    private static boolean move_right(float x){
        return x > SCREEN_WIDTH / 2;
    }

}




