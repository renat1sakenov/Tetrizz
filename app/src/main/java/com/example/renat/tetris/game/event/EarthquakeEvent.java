package com.example.renat.tetris.game.event;

import com.example.renat.tetris.game.GameController;
import com.example.renat.tetris.game.TetrisBlock;
import com.example.renat.tetris.game.World;

import java.util.Random;

/**
 * Created by Renat on 09.10.2015.
 */
public class EarthquakeEvent extends Event {

    Random rand2;

    public EarthquakeEvent(GameController game_controller) {
        super(game_controller);
        rand2 = new Random();
    }

    @Override
    public void act(){
        if(chance_for_event()){
            earthquake();
        }
    }


    public void earthquake(){
        world = World.get_world();
        int new_x;
        int new_y;

        for(int i = 0; i < World.WORLD_WIDTH; i ++){
            for(int j = World.WORLD_HEIGHT; j > World.WORLD_HEIGHT / 2; j --){
                //rand.nextInt(max - min + 1) + min
                new_x = rand2.nextInt(World.WORLD_WIDTH);
                new_y = rand2.nextInt((World.WORLD_HEIGHT-1) -(World.WORLD_HEIGHT / 2) +1 ) + World.WORLD_HEIGHT / 2;

                world[new_x][new_y] = world[i][j];
            }
        }

    }
}
