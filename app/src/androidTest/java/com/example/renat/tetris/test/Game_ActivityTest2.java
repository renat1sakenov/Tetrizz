package com.example.renat.tetris.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.renat.tetris.Data;
import com.example.renat.tetris.Game_Activity;
import com.example.renat.tetris.R;

/**
 * Created by Renat on 21.08.2015.
 */
public class Game_ActivityTest2 extends ActivityUnitTestCase<Game_Activity> {


    private Game_Activity activity;
    private Button pauseButton;
    private TextView score_view;
    private View layout_noButton;


    public Game_ActivityTest2(){
        super(Game_Activity.class);
    }


    @Override
    public void setUp() throws Exception{
        super.setUp();

        //test layout without buttons
        Data.USE_BUTTONS  = false;

        startActivity(new Intent(),null,null);

        activity = getActivity();

        assertNotNull("Game_Activity is null",activity);

        layout_noButton = (RelativeLayout) activity.findViewById(R.id.game_activity_no_buttons);

        assertNotNull("layout without buttons is null",layout_noButton);

        score_view = (TextView) activity.findViewById(R.id.score_view);

        assertNotNull("score view is null",score_view);

        pauseButton = (Button) activity.findViewById(R.id.pause_button);

        assertNotNull("pause button is null", pauseButton);
    }

    public void testTest(){

    }



    @Override
    public void tearDown() throws Exception{
        super.tearDown();
    }
}
