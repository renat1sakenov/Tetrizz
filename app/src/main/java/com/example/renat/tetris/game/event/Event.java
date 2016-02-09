package com.example.renat.tetris.game.event;

import android.util.Log;

import com.example.renat.tetris.game.GameController;
import com.example.renat.tetris.game.TetrisBlock;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Renat on 29.08.2015.
 */
public class Event {

    protected GameController game_controller;
    protected static TetrisBlock[][] world;
    protected ScheduledExecutorService duration_scheduler;
    protected int DURATION;
    protected static final TimeUnit UNIT = TimeUnit.SECONDS;
    protected Random rand;

    public Event(GameController game_controller){
        this.game_controller = game_controller;
        duration_scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void act(){}

    protected void start_duration_scheduler(){

    }

    protected void shut_down_duration_scheduler(){
        duration_scheduler.shutdownNow();
    }

    protected boolean chance_for_event(){
        rand = new Random();
        //rand.nextInt(max - min + 1) + min
        int r = rand.nextInt(10 - 1 + 1) + 1;
        int r2 = rand.nextInt(10 -1 + 1) + 1;

        //return true;
        return r == r2;
    }

}
