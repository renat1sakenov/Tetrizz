package com.example.renat.tetris.game;

import android.graphics.Point;

import com.example.renat.tetris.Data;
import com.example.renat.tetris.Game_Activity;

/**
 * Created by Renat on 14.08.2015.
 */
public class World {


    //the first 5 layer of blocks are not drawn. It's there for the 'falling' effect and to make rotations of the blocks by start possible
    //DRAWING_OFFSET is the new origin on the y axis
    public static final int DRAWING_OFFSET = 5;

    public static int WORLD_HEIGHT;
    public static int WORLD_WIDTH;


    private static TetrisBlock[][] world;


    public static void init(){

        Point p;
        if(Game_Activity.LEVEL == 0) {
            p = WorldSize.getSize(Data.SIZE, true);
        }else{
            p = WorldSize.getSize(WorldSize.Size.DEFAULT, true);
        }

        WORLD_HEIGHT = p.x + DRAWING_OFFSET;
        WORLD_WIDTH = p.y;

        world = new TetrisBlock[WORLD_WIDTH][WORLD_HEIGHT];
        for(int i = 0; i < WORLD_WIDTH; i ++){
            for(int j = 0; j < WORLD_HEIGHT; j ++){
                world[i][j] = TetrisBlock.EMPTY;
            }
        }
    }


    public static TetrisBlock[][] get_world(){
        return world;
    }


    public static boolean checkFinalPos(FallingBlock block){

        int x = block.getPos().x;
        int y = block.getPos().y;
        Point[] relPos = block.getRelPos();

        if (y == WORLD_HEIGHT - 1)
            return true;

        if (checkBoundaries(x, y + 1) && world[x][y + 1] != TetrisBlock.EMPTY)
            return true;

        for (int i = 0; i < relPos.length; i++) {
            if (y + relPos[i].y == WORLD_HEIGHT - 1)
                return true;
            if (checkBoundaries(relPos[i].x + x, relPos[i].y + y + 1) && world[x + relPos[i].x][y + relPos[i].y + 1] != TetrisBlock.EMPTY)
                return true;
        }

        return false;
    }


    public static void writeBlockOnWorld(FallingBlock block){

        int x = block.getPos().x;
        int y = block.getPos().y;
        Point[] relPos = block.getRelPos();

        if (checkBoundaries(x, y))
            world[x][y] = block.getType();

        for (int i = 0; i < relPos.length; i++) {
            if (checkBoundaries(relPos[i].x + x, relPos[i].y + y) && world[relPos[i].x + x][relPos[i].y + y] == TetrisBlock.EMPTY)
                world[x + relPos[i].x][y + relPos[i].y] = block.getType();
        }

    }

    public static int checkForFullRow(){

        int counter = 0;
        boolean full;
        for(int i = 0; i < WORLD_HEIGHT; i ++){
            full = true;
            for(int j = 0; j < WORLD_WIDTH; j ++){
                if(world[j][i] == TetrisBlock.EMPTY) {
                    full = false;
                }
            }
            if(full){
                counter ++;
                remove(i);
            }
        }
        return counter;
    }

    private static void remove(int line){

        for(int i = line; i > 0; i --){
            for(int j = 0; j < WORLD_WIDTH; j ++){
                world[j][i] = world[j][i-1];
            }
        }
    }

    public static boolean checkForGameOver(){
        for(int i = 0; i < WORLD_WIDTH; i ++){
            if(world[i][DRAWING_OFFSET] != TetrisBlock.EMPTY) {
                return true;
            }
        }
        return false;
    }


    public static boolean checkBoundaries(int x, int y){
        return x >= 0 && x < WORLD_WIDTH && y >= 0 && y < WORLD_HEIGHT;
    }
}
