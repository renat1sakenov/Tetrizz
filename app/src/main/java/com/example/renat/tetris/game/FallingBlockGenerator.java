package com.example.renat.tetris.game;

import java.util.Random;

/**
 * Created by Renat on 14.08.2015.
 */
public class FallingBlockGenerator {

    private static int DEFAULT_STARTING_HEIGHT = 4;

    public static FallingBlock default_(){

        Random rand = new Random();
        TetrisBlock type;
        int r = rand.nextInt((FallingBlock.NUMBER_BLOCKS - 1) +1) + 1;
        switch (r){
            case 1: type = TetrisBlock.L;
                break;
            case 2: type = TetrisBlock.SNAKE;
                break;
            case 3: type = TetrisBlock.T;
                break;
            case 4: type = TetrisBlock.SQUARE;
                break;
            case 5: type = TetrisBlock.Z;
                break;
            case 6: type = TetrisBlock.Backwards_L;
                break;
            case 7: type = TetrisBlock.Backwards_Z;
                break;
            default: type = null;
        }





        //rand.nextInt(max - min + 1) + min
        if(World.WORLD_WIDTH > 8){
            int max = World.WORLD_WIDTH - 4;
            int min = 4;
            r = rand.nextInt(max - min + 1) + min;
        }else r = World.WORLD_WIDTH / 2;



        FallingBlock block = new FallingBlock(r,DEFAULT_STARTING_HEIGHT,type);

        int rotation = rand.nextInt(4) + 1;
        for(;rotation > 0; rotation--) {
            block.rotateRight();
        }


        return block;
    }



    public static FallingBlock debug(){
        return new FallingBlock(10,DEFAULT_STARTING_HEIGHT,TetrisBlock.SQUARE);
    }


    public static FallingBlock only_one(){

        Random rand = new Random();
        TetrisBlock type = TetrisBlock.L;
        int r;

        //rand.nextInt(max - min + 1) + min
        if(World.WORLD_WIDTH > 8){
            int max = World.WORLD_WIDTH - 4;
            int min = 4;
            r = rand.nextInt(max - min + 1) + min;
        }else r = World.WORLD_WIDTH / 2;



        FallingBlock block = new FallingBlock(r,DEFAULT_STARTING_HEIGHT,type);

        int rotation = rand.nextInt(4) + 1;
        for(;rotation > 0; rotation--) {
            block.rotateRight();
        }


        return block;
    }
}
