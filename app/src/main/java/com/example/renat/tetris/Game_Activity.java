package com.example.renat.tetris;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renat.tetris.drawer.ComboCounterDrawer;
import com.example.renat.tetris.drawer.CountDownDrawer;
import com.example.renat.tetris.drawer.GameView;
import com.example.renat.tetris.game.FallingBlock;
import com.example.renat.tetris.game.GameController;
import com.example.renat.tetris.game.level.Level_1;
import com.example.renat.tetris.game.level.Level_2;
import com.example.renat.tetris.game.level.Level_3;
import com.example.renat.tetris.game.level.Level_4;
import com.example.renat.tetris.game.level.Level_5;


/**
 * Created by Renat on 16.07.2015.
 */
public class Game_Activity extends Activity {


    private static String TAG = "GAME_ACTIVITY";
    private LinearLayout linearLayout;
    private RelativeLayout relativeLayout;
    private GameView game_view;


    public boolean PAUSED = false;
    public static float SCREEN_RATIO;

    private GameController game_controller;

    private Button right_button;
    private Button left_button;
    private Button rotate_button;
    private Button place_button;
    private Button pause_button;

    private TextView score_view;

    private View layout_wButtons;

    public static int LEVEL = 0;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        Log.i(TAG, "onCreate");

        Point wdim = getWindowDim();

        SoundHandler.init(getApplicationContext());
        ImageLoader.init(getApplicationContext());

        ClassicMenu.SHOW = false;

        switch (LEVEL) {
            case 0:
                game_controller = new GameController(this);
                break;
            case 1:
                game_controller = new Level_1(this);
                break;
            case 2:
                game_controller = new Level_2(this);
                SoundHandler.set_party_track();
                break;
            case 3:
                CountDownDrawer.DRAW = true;
                game_controller = new Level_3(this);
                break;
            case 4:
                game_controller = new Level_4(this);
                break;
            case 5:
                ComboCounterDrawer.DRAW = true;
                game_controller = new Level_5(this);
                break;
        }

        if(!SoundHandler.is_playing())
            SoundHandler.set_default_track();


        layout_wButtons = getLayoutInflater().inflate(R.layout.game_activity,null);
        setContentView(layout_wButtons);
        SCREEN_RATIO = .7f;

        init_views_wB();

        game_view = new GameView(this,wdim.y,wdim.x);


        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        init_blackframe();

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.getLayoutParams().height = (int) (wdim.y *SCREEN_RATIO);

        linearLayout.addView(game_view);

        game_controller.start();
    }



    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();


        SoundHandler.continue_sound();

        Log.i(TAG,"onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();


        //when screen is being locked
        //will be called when pop up window opens ( will set pause, so player has to unpause when pressing "yes" for new game)
        //when 'pause' is not called here, the game won't pause when the player locks screen


        SoundHandler.set_sound_pause();

        Log.i(TAG,"onPause");
    }

    @Override
    protected void onStop(){

        //when app is minimized (home button)


        //pausing game, when the app is inactive
        if(!PAUSED)
            pause();

        super.onStop();
        Log.i(TAG,"onStop");
    }


    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart");
    }


    @Override
    protected void onDestroy(){

        super.onDestroy();

        //killing thread
        if(game_controller != null){

            if(game_controller instanceof Level_3)
                ((Level_3) game_controller).shutDownCountDown();

            game_controller.shutDownScheduler();
            game_controller.RUNNING = false;
        }
        CountDownDrawer.DRAW = false;

        SoundHandler.set_sound_off();

        Log.i(TAG, "onDestroy");
    }



    private void init_blackframe(){

        ShapeDrawable rectShapeDrawable = new ShapeDrawable();
        rectShapeDrawable.getPaint().setColor(Color.BLACK);
        rectShapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
        rectShapeDrawable.getPaint().setStrokeWidth(5);

        if(relativeLayout != null)
            relativeLayout.setBackgroundDrawable(rectShapeDrawable);
    }


    private void init_views_wB(){

        right_button = (Button) findViewById(R.id.right_button);
        left_button = (Button) findViewById(R.id.left_button);
        rotate_button = (Button) findViewById(R.id.rotate_button);
        score_view = (TextView) findViewById(R.id.score_view);
        place_button = (Button) findViewById(R.id.place_button);
        pause_button = (Button) findViewById(R.id.pause_button);

        right_button.setTypeface(Main.face);
        left_button.setTypeface(Main.face);
        rotate_button.setTypeface(Main.face);
        place_button.setTypeface(Main.face);
        pause_button.setTypeface(Main.face);
    }

    private void init_views_noB(){
        score_view = (TextView) findViewById(R.id.score_view);
        pause_button = (Button) findViewById(R.id.pause_button);
        pause_button.setTypeface(Main.face);
    }


    public void message(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * pauses the game_controller Thread.
     * PAUSED is checked in the game_controller.run() method, and calls wait() on this object, when PAUSED is true
     * when pause is called again, this thread will notify game_controller.
     *
     * @param view
     */
    public void pause(View view){
        pause();
    }

    private void pause(){
        if(PAUSED){

            enableButtons(true);

            synchronized (this) {
                notifyAll();
            }
        }else{
            enableButtons(false);

        }
        PAUSED = !PAUSED;
    }

    private void enableButtons(boolean b){

            right_button.setEnabled(b);
            left_button.setEnabled(b);
            rotate_button.setEnabled(b);
            place_button.setEnabled(b);


        if (b){
            pause_button.setText("Pause");
            pause_button.setTypeface(Main.face);

        }
        else {
            pause_button.setText("Weiter");
            pause_button.setTypeface(Main.face);
        }
    }


    public void goLeft(View view){

        try {
            game_controller.goLeft();
        }catch (IllegalArgumentException e){

        }

    }

    public void goRight(View view){
        try {
            game_controller.goRight();
        }catch (IllegalArgumentException e){

        }
    }

    public void rotate(View view){
        try {
            game_controller.rotate();
        }catch (IllegalArgumentException e){

        }
    }

    public void place(View view){
        game_controller.place();
    }


    public void drawGraphics(FallingBlock block) {
        game_view.drawGraphics(block);
    }

    public void showScore(int score){
        if(score_view != null)
            score_view.setText(Integer.toString(score));
    }


    public Point getWindowDim(){
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int height = display.getHeight();
        int width = display.getWidth();

        return new Point(width,height);
    }

    public static void setLevel(int i){
         LEVEL = i;
    }
}

