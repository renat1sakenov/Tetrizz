package com.example.renat.tetris.game;

import android.os.SystemClock;

/**
 * Created by Renat on 14.08.2015.
 */
public class Sleeper {


    public final static int MIN_WAITING_TIME = 100;
    public final static int MAX_WAITING_TIME = 400;


    private static int waiting_time;

    private Sleeper(){}

    public static void init(){
        waiting_time = MAX_WAITING_TIME;
    }


    public static void wait_(){
        SystemClock.sleep(waiting_time);
    }

    public static void adjust_waiting_time(int score){

        if(waiting_time <= MIN_WAITING_TIME)
            return;

        waiting_time -= score * 0.5;
    }

    public static void set_waiting_time(int time){
        waiting_time = time;
    }





}
