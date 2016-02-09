package com.example.renat.tetris.game.level;

import com.example.renat.tetris.ColorMode;
import com.example.renat.tetris.Game_Activity;
import com.example.renat.tetris.game.FallingBlockGenerator;
import com.example.renat.tetris.game.ScoreHandler;
import com.example.renat.tetris.game.Sleeper;
import com.example.renat.tetris.game.World;

/**
 * Created by Renat on 23.08.2015.
 */
public class Level_2 extends Level {

    private int ACQUIRED_SCORE = 60;

    public Level_2(Game_Activity game){
        super(game);
        index = 2;
        points_to_win = 150;

        drawing_rate_ms = 150;
        ColorMode.MODE = ColorMode.Color_Mode.COLORFUL;
    }

    @Override
    protected void mainLoop(){
        while(RUNNING && ScoreHandler.second_score() < ACQUIRED_SCORE){
            block = FallingBlockGenerator.default_();
            for(;;) {
                if(!game.PAUSED) {
                    if (!World.checkFinalPos(block)) {
                        goDown();
                        try{
                            rotate();
                        }catch(IllegalArgumentException e){}
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
