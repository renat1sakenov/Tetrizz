package com.example.renat.tetris.game.level;

import com.example.renat.tetris.Game_Activity;
import com.example.renat.tetris.game.FallingBlockGenerator;
import com.example.renat.tetris.game.Sleeper;
import com.example.renat.tetris.game.World;
import com.example.renat.tetris.game.event.LightningEvent;

/**
 * Created by Renat on 29.08.2015.
 */
public class Level_4 extends Level {

    private LightningEvent event;
    private int ACQUIRED_SCORE = 4;

    public Level_4(Game_Activity game){
        super(game);
        index = 4;
        points_to_win = 300;

        event = new LightningEvent(this);

    }


    @Override
    public void mainLoop(){
        while(RUNNING && LightningEvent.COUNTER < ACQUIRED_SCORE){
            block = FallingBlockGenerator.default_();
            for(;;) {
                if(!game.PAUSED) {

                    event.act();

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

        if(LightningEvent.COUNTER >= ACQUIRED_SCORE)
            won_level();
    }
}
