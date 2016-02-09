package com.example.renat.tetris.game.level;

import android.content.Intent;

import com.example.renat.tetris.Game_Activity;
import com.example.renat.tetris.Popup_Window;
import com.example.renat.tetris.game.FallingBlockGenerator;
import com.example.renat.tetris.game.ScoreHandler;
import com.example.renat.tetris.game.Sleeper;
import com.example.renat.tetris.game.World;

import java.util.Timer;

/**
 * Created by Renat on 23.08.2015.
 */
public class Level_1 extends Level {

    private int ACQUIRED_SCORE = 10;

    public Level_1(Game_Activity game){
        super(game);
        index = 1;
        points_to_win = 100;
    }

    @Override
    protected void mainLoop(){
        while(RUNNING && ScoreHandler.second_score() < ACQUIRED_SCORE){
            block = FallingBlockGenerator.only_one();
            for(;;) {
                if(!game.PAUSED) {
                    if (!World.checkFinalPos(block)) {
                        goDown();
                    }
                    else {
                        World.writeBlockOnWorld(block);
                        break;
                    }
                    Sleeper.wait_();
                }else{
                    synchronized (game) {
                        try {
                            game.wait();
                        }catch (Exception e){
                            game.message(e.getMessage());
                        }
                    }
                }
            }
            checkForFullRow();
            if(World.checkForGameOver()){
                end_game();
                break;
            }
        }

        if(ScoreHandler.second_score() >= ACQUIRED_SCORE){
            won_level();
        }

    }
}
