package com.example.renat.tetris.game;

import android.graphics.Point;


/**
 * Created by Renat on 11.08.2015.
 */
public class UserAction {

    private UserAction(){}

    public static void goLeft(FallingBlock block) throws IllegalArgumentException{

        TetrisBlock[][] world = World.get_world();

        int x = block.getPos().x;
        int y = block.getPos().y;
        Point[] relPos = block.getRelPos();

        if(x > 0 && x < World.WORLD_WIDTH) {
            if (world[x - 1][y] != TetrisBlock.EMPTY) {
                return;
            }
        }else throw new IllegalArgumentException("Can't move there");

        for(int i = 0; i < relPos.length; i ++){
            if((x + relPos[i].x) > 0 && (x + relPos[i].x) < World.WORLD_WIDTH) {
                if (world[x + relPos[i].x - 1][y + relPos[i].y] != TetrisBlock.EMPTY) {
                    return;
                }
            }else throw new IllegalArgumentException("Can't move there");
        }

        block.goLeft();
    }


    public static void goRight(FallingBlock block) throws IllegalArgumentException{

        TetrisBlock[][] world = World.get_world();

        int x = block.getPos().x;
        int y = block.getPos().y;
        Point[] relPos = block.getRelPos();

        if(x >= 0 && x < World.WORLD_WIDTH - 1) {
            if (world[x + 1][y] != TetrisBlock.EMPTY) {
                return;
            }
        }else throw new IllegalArgumentException("Can't move there");

        for(int i = 0; i < relPos.length; i ++){
            if((x + relPos[i].x) >= 0 && (x + relPos[i].x) < World.WORLD_WIDTH - 1) {
                if (world[x + relPos[i].x + 1][y + relPos[i].y] != TetrisBlock.EMPTY) {
                    return;
                }
            }else throw new IllegalArgumentException("Can't move there");
        }

        block.goRight();
    }


    public static void rotate(FallingBlock block) throws IllegalArgumentException{

            TetrisBlock[][] world = World.get_world();

            if (block.getType() == TetrisBlock.SQUARE)
                return;

            Point[] relPos = block.getRotation();

            int x = block.getPos().x;
            int y = block.getPos().y;

            if (!World.checkBoundaries(x, y))
                return;


            for (int i = 0; i < block.getRelPos().length; i++) {
                //check boundaries and collision with other blocks
                if (!World.checkBoundaries(relPos[i].x + x, relPos[i].y + y) || world[x + relPos[i].x][y + relPos[i].y] != TetrisBlock.EMPTY) {
                    throw new IllegalArgumentException("Can't rotate here");
                }
            }

            block.rotateRight();
    }




    public static void place(FallingBlock block){

        TetrisBlock[][] world = World.get_world();

        int y = block.getPos().y;
        int x = block.getPos().x;

        Point[] relPos = block.getRelPos();

        outer:
        for(; y < World.WORLD_HEIGHT-1; y++){

            if(world[x][y+1] != TetrisBlock.EMPTY)
                break;

            for(int i = 0; i < relPos.length; i ++){
                if(!World.checkBoundaries( relPos[i].x + x , relPos[i].y + y + 1) || world[x + relPos[i].x][y + relPos[i].y + 1] != TetrisBlock.EMPTY)
                    break outer;
            }

        }

        block.setPos(x,y);

    }


}
