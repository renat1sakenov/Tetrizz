package com.example.renat.tetris.game;

import com.example.renat.tetris.Data;

/**
 * Created by Renat on 14.08.2015.
 */
public class ScoreHandler {


    public static final int DEFAULT_SCORE_ADD = 10;
    public static final int HARD_MODE_SCORE_ADD = 20;
    public static final int VHARD_MODE_SCORE_ADD = 40;

    private static int combo_counter;
    private static int score;

    private static int second_score;


    private ScoreHandler(){}


    public static void init(){
        score = 0;
        second_score = 0;
        combo_counter = 0;
    }

    public static void add_score(){
        score += DEFAULT_SCORE_ADD * combo_counter;
    }

    public static void add_second_score(){
        second_score += DEFAULT_SCORE_ADD * combo_counter;
    }

    public static void add_score_hardmode(){
        score += HARD_MODE_SCORE_ADD * combo_counter;
    }

    public static void adds_core_vhardmode(){
        score += VHARD_MODE_SCORE_ADD * combo_counter;
    }

    public static void combo_counter_add(){
        combo_counter ++;
    }

    public static void combo_counter_reset(){
        combo_counter = 0;
    }

    public static int score(){
        return score;
    }

    public static int second_score(){
        return  second_score;
    }

    public static boolean check_new_highscore(){
        return score > Data.HIGHSCORE;
    }

    public static boolean check_new_second_highscore(){
        return second_score > Data.SECOND_HIGHSCORE;
    }



}
