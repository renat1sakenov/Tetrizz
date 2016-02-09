package com.example.renat.tetris;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.widget.Toast;

import com.example.renat.tetris.game.level.Level_2;

/**
 * Created by SIM on 03.09.2015.
 */
public class SoundHandler {


    private static MediaPlayer sound;
    private static MediaPlayer partysound;

    private static boolean DEFAULT_ON;
    private static boolean SPECIAL_ON;

    private static boolean DEFAULT_PAUSE;
    private static boolean SPECIAL_PAUSE;

    private static Context context;

    public static void init(Context context_){
        context = context_;

        sound = MediaPlayer.create(context, R.raw.tetrizz1);
        partysound = MediaPlayer.create(context, R.raw.partytrack);

    }

    public static void set_default_track(){
        if (Data.MUSIC_ON && !SPECIAL_ON && !DEFAULT_ON){

            DEFAULT_ON = true;
            sound.start();
            sound.setLooping(true);
        }
    }

    public static void set_party_track(){
        if (Data.MUSIC_ON && !SPECIAL_ON && !DEFAULT_ON){

            SPECIAL_ON = true;
            partysound.start();
            partysound.setLooping(true);
        }
    }

    public static void set_sound_off(){
        sound.release();
        partysound.release();

        if(DEFAULT_ON)
            DEFAULT_ON = false;
        if(SPECIAL_ON)
            SPECIAL_ON = false;
    }

    public static boolean is_playing(){
        return DEFAULT_ON ||  SPECIAL_ON || !Data.MUSIC_ON;
    }

    public static void set_sound_pause(){
        if(!DEFAULT_PAUSE && !SPECIAL_ON && DEFAULT_ON) {
            sound.pause();
            DEFAULT_PAUSE = true;
        }
        if(!SPECIAL_PAUSE && !DEFAULT_ON && SPECIAL_ON) {
            partysound.pause();
            SPECIAL_PAUSE = true;
        }
    }


    public static void continue_sound(){
        if(DEFAULT_ON && DEFAULT_PAUSE){
            sound.start();
            DEFAULT_PAUSE = false;
        }
        else if(SPECIAL_ON && SPECIAL_PAUSE){
            partysound.start();
            SPECIAL_PAUSE = false;
        }
    }


}
