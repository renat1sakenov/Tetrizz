package com.example.renat.tetris.game.event;

import android.util.Log;

import com.example.renat.tetris.drawer.GameView;
import com.example.renat.tetris.drawer.LightningEventDrawer;
import com.example.renat.tetris.game.GameController;
import com.example.renat.tetris.game.TetrisBlock;
import com.example.renat.tetris.game.World;
import com.example.renat.tetris.game.WorldSize;

import java.util.Random;
import java.util.concurrent.Executors;

/**
 * Created by Renat on 29.08.2015.
 */
public class LightningEvent extends Event {

    private static int radius;
    public static int height;
    public static int pos;
    public static int COUNTER;

    private static int offset_x_pos;
    private static int offset_y_pos;

    public LightningEvent(GameController game_controller_){
        super(game_controller_);
        DURATION = 3;
        radius = 4;

        height = 0;

        COUNTER = 0;

        //test
        offset_x_pos =  5;
        offset_y_pos = 5;

    }


    @Override
    public void act(){
        if(chance_for_event()){
            tower_is_there();
                LightningEventDrawer.lightning_x = pos * GameView.BLOCK_SIZE_X;
                LightningEventDrawer.lightning_y = (height - 10) * GameView.BLOCK_SIZE_Y;

                LightningEventDrawer.DRAW = true;
        }
    }


    private void tower_is_there(){
        world = World.get_world();
        int[] counter = new int[World.WORLD_WIDTH];

        //position of the highest column
        int pos = -1;


        //height of the highest column
        int height = World.WORLD_HEIGHT;


        for(int i = 0; i < World.WORLD_WIDTH; i ++){
            counter[i] = World.WORLD_HEIGHT;
            for(int j = World.DRAWING_OFFSET; j < World.WORLD_HEIGHT; j ++){
                if(world[i][j] != TetrisBlock.EMPTY) {
                    //highest block of column i is saved in counter[i]
                    counter[i] = j;
                    break;
                }
            }

            //if a column is higher than the previous highest
            if(counter[i] < height) {
                pos = i;
                height = counter[i];
            }
        }

        this.height = height;
        this.pos =  pos;


        //x boundaries
        if(this.pos > World.WORLD_WIDTH - offset_x_pos){
            this.pos = World.WORLD_WIDTH - offset_x_pos;
        }
        if(this.pos < offset_x_pos){
            this.pos = offset_x_pos;
        }


        if(this.pos < (World.WORLD_HEIGHT - World.DRAWING_OFFSET) + offset_y_pos)
            this.pos =(World.WORLD_HEIGHT - World.DRAWING_OFFSET) + offset_y_pos;
        if(this.pos > (World.WORLD_HEIGHT - World.DRAWING_OFFSET) - offset_y_pos)
            this.pos = (World.WORLD_HEIGHT - World.DRAWING_OFFSET) - offset_y_pos;
    }

    public static void remove(){
        remove(pos, height);
        COUNTER++;
    }

    public static void remove(int w, int h){
        if(w < 0)
            return;

        for(int j = h - radius; j < World.WORLD_HEIGHT && j > World.DRAWING_OFFSET && j < h + radius; j ++){
            for(int i = w - radius; i < World.WORLD_WIDTH && i > 0 && i < w + radius; i++ ){
                world[i][j] = TetrisBlock.EMPTY;
            }
        }
    }

}
