package com.example.renat.tetris.game.level;

import com.example.renat.tetris.Game_Activity;
import com.example.renat.tetris.drawer.ComboCounterDrawer;
import com.example.renat.tetris.drawer.ComboDrawer;
import com.example.renat.tetris.game.FallingBlockGenerator;
import com.example.renat.tetris.game.ScoreHandler;
import com.example.renat.tetris.game.Sleeper;
import com.example.renat.tetris.game.World;

/**
 * Created by Renat on 09.10.2015.
 */
public class Level_5 extends Level {

    private int counter_combo;
    private int AMOUNT_OF_COMBOS = 3;

    //combo level
    public Level_5(Game_Activity game) {
        super(game);
        index = 5;
    }

    @Override
    protected void checkForFullRow(){
        int counter = World.checkForFullRow();
        for(int i = 0; i < counter; i ++){
            ScoreHandler.combo_counter_add();

            ScoreHandler.add_second_score();

        }

        adjust_waiting_time();

        if(counter > 1 ) {
            ComboDrawer.combo_event(block.getPos().x, block.getPos().y);
            counter_combo ++;
            ComboCounterDrawer.setCounter(counter_combo);
        }


        showScore(ScoreHandler.second_score());
    }

    @Override
    protected void mainLoop(){

        while(RUNNING && counter_combo < AMOUNT_OF_COMBOS){
            block = FallingBlockGenerator.default_();
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

        if(counter_combo >= AMOUNT_OF_COMBOS)
            won_level();
    }


}
