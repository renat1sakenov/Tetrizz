package com.example.renat.tetris.game.level;

import com.example.renat.tetris.ColorMode;
import com.example.renat.tetris.drawer.ComboDrawer;
import com.example.renat.tetris.drawer.CountDownDrawer;
import com.example.renat.tetris.Game_Activity;
import com.example.renat.tetris.game.FallingBlockGenerator;
import com.example.renat.tetris.game.ScoreHandler;
import com.example.renat.tetris.game.Sleeper;
import com.example.renat.tetris.game.World;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Renat on 26.08.2015.
 */
public class Level_3 extends Level {

    private int TIME_S;

    private ScheduledExecutorService countdown;


    public Level_3(Game_Activity game) {
        super(game);
        index = 3;
        points_to_win = 200;
        Sleeper.set_waiting_time(100);

        TIME_S = 60;

        countdown = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    protected void mainLoop(){

        start_timer();
        while(RUNNING && TIME_S > 0){
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
                shutDownCountDown();
                end_game();
                break;
            }
        }

        if(TIME_S <= 0) {
            shutDownCountDown();
            won_level();
        }

    }


    @Override
    protected void checkForFullRow(){
        int counter = World.checkForFullRow();
        for(int i = 0; i < counter; i ++){
            ScoreHandler.combo_counter_add();

            ScoreHandler.add_second_score();
        }

        if(counter > 0){
            ComboDrawer.combo_event(block.getPos().x, block.getPos().y);
        }

        showScore(ScoreHandler.second_score());
        ScoreHandler.combo_counter_reset();
    }


    private void start_timer(){
        countdown.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(!game.PAUSED) {
                    TIME_S--;
                    CountDownDrawer.setCountdown(Integer.toString(TIME_S));
                }
            }
        },0,1, TimeUnit.SECONDS);
    }

    public void shutDownCountDown(){
        countdown.shutdownNow();
    }
}
