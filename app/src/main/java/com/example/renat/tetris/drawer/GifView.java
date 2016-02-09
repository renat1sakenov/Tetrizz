package com.example.renat.tetris.drawer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.Log;
import android.view.View;

import com.example.renat.tetris.R;
import com.example.renat.tetris.game.event.LightningEvent;

/**
 * Created by Renat on 06.10.2015.
 */
public class GifView{

    private Movie movie;
    private long now;
    private long moviestart;
    private int relTime;
    private int duration;
    private GameView gv;
    private EventDrawer ed;

    public boolean loop = false;

    public GifView(GameView gv, String file, EventDrawer ed) {
        this.gv = gv;
        this.ed = ed;

        try {
            setGif(file);
        }catch (Exception e){
            Log.e("GIFVIEW", e.getMessage());
        }
    }

    public void setGif(String file) throws Exception{
        movie = Movie.decodeStream(gv.getResources().getAssets().open(file));

        duration = movie.duration();
    }


    public void draw(Canvas canvas, float x, float y){
        long now=android.os.SystemClock.uptimeMillis();

        if (moviestart == 0) {
            moviestart = now;
        }

        if(!loop && now > moviestart + duration) {
            finish_drawing();
            moviestart = 0;
        }


        int relTime = (int)((now - moviestart) % movie.duration());
        movie.setTime(relTime);
        movie.draw(canvas,x,y);
        gv.invalidate();
    }

    public void finish_drawing(){
        switch (ed){
            case LIGHTNING:
                LightningEventDrawer.DRAW = false;
                LightningEvent.remove();
                break;
        }
    }
}
