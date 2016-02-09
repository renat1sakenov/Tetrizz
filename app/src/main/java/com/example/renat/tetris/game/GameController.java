package com.example.renat.tetris.game;

import android.content.Intent;

import com.example.renat.tetris.ColorMode;
import com.example.renat.tetris.Data;
import com.example.renat.tetris.Game_Activity;
import com.example.renat.tetris.Popup_Window;
import com.example.renat.tetris.TouchHandler;
import com.example.renat.tetris.drawer.ComboDrawer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Handles the game logic.
 * Created by Renat on 17.07.2015.
 */
public class GameController extends Thread{


    public static boolean RUNNING;

    protected FallingBlock block;
    protected Game_Activity game;


    protected ScheduledExecutorService scheduler;
    protected int drawing_rate_ms = 50;


    //--------------------------------------------------------------------------------------------//
    // constructor                                                                                //
    //--------------------------------------------------------------------------------------------//

    public GameController(Game_Activity game){
        super();
        this.game = game;

        TouchHandler.init(game.getWindowDim().x);
        ScoreHandler.init();
        Sleeper.init();
        World.init();
        ColorMode.MODE = ColorMode.Color_Mode.NORMAL;

        RUNNING = true;

        scheduler = Executors.newSingleThreadScheduledExecutor();


        showScore(ScoreHandler.score());

    }



    public void run(){
        start_drawing_timer();
        mainLoop();
        shutDownScheduler();
    }


    protected void mainLoop(){

        while(RUNNING){
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
    }



    //--------------------------------------------------------------------------------------------//
    //   going-up (to Game_Activity) methods                                                      //
    //--------------------------------------------------------------------------------------------//


    //for test
    protected void toast_message(final String str){
        game.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                game.message(str);
            }
        });
    }

    protected void showScore(final int score){
        game.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                game.showScore(score);
            }
        });
    }


    protected void drawGraphics(FallingBlock block){
        game.drawGraphics(block);
    }


    //--------------------------------------------------------------------------------------------//
    //   methods for user input (Running in main thread)                                          //
    //--------------------------------------------------------------------------------------------//


    public void goLeft() throws IllegalArgumentException{

        synchronized (block) {
            UserAction.goLeft(block);
        }

    }

    public void goRight() throws IllegalArgumentException{

        synchronized (block) {
            UserAction.goRight(block);
        }
    }


    public void rotate() throws IllegalArgumentException{

        synchronized (block) {
            UserAction.rotate(block);
        }
    }


    public void place(){
        synchronized (block){
            UserAction.place(block);
        }
    }


    public void action(float x, float y){
        synchronized (block) {
            TouchHandler.action(x, y, block);
        }
    }


    //--------------------------------------------------------------------------------------------//
    //   main components of the game logic                                                        //
    //--------------------------------------------------------------------------------------------//


    protected void goDown(){

        try{
            block.goDown();
        }catch (IllegalArgumentException e){
            game.message(e.getMessage());
        }
    }

    protected void checkForFullRow(){
        int counter = World.checkForFullRow();
        for(int i = 0; i < counter; i++){
            ScoreHandler.combo_counter_add();

            switch (Data.SIZE){
                case DEFAULT:
                    ScoreHandler.add_score();
                    break;
                case BIG:
                case SMALL:
                    ScoreHandler.add_score_hardmode();
                    break;
                case VERY_BIG:
                case VERY_SMALL:
                    ScoreHandler.adds_core_vhardmode();
                    break;
            }

            adjust_waiting_time();
        }

        if(counter > 1){
            ComboDrawer.combo_event(block.getPos().x, block.getPos().y);
        }

        showScore(ScoreHandler.score());
        ScoreHandler.combo_counter_reset();
    }


    protected void adjust_waiting_time(){
        Sleeper.adjust_waiting_time(ScoreHandler.score());
    }


    protected void end_game(){

        if(ScoreHandler.check_new_highscore()){
            Data.HIGHSCORE = (ScoreHandler.score());
            Popup_Window.init("\n"+"Neuer Highscore!" + "\n" +"Deine Punkte: " + ScoreHandler.score() + "\n\n", game);
        }else {
            Popup_Window.init("\n"+"Deine Punkte: " + ScoreHandler.score() + "\n\n", game);
        }

        Intent intent = new Intent(game, Popup_Window.class);
        game.startActivity(intent);
    }

    protected void start_drawing_timer(){
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                drawGraphics(block);
            }
        },0,drawing_rate_ms, TimeUnit.MILLISECONDS);
    }

    public void shutDownScheduler(){
        scheduler.shutdownNow();
    }
}
