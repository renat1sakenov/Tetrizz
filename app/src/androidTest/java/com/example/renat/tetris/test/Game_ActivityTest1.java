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
public class Game_ActivityTest1 extends ActivityUnitTestCase<Game_Activity> {

    private Game_Activity activity;
    private Button rightButton;
    private Button leftButton;
    private Button pauseButton;
    private Button rotateButton;
    private Button placeButton;
    private TextView score_view;
    private View layout_Button;






    public Game_ActivityTest1(){
        super(Game_Activity.class);
    }


    @Override
    public void setUp() throws Exception{
        super.setUp();


        //test layout with buttons
        Data.USE_BUTTONS = true;

        startActivity(new Intent(), null, null);
        activity = getActivity();

        assertNotNull("Game_Activity is null",activity);

        layout_Button = (RelativeLayout) activity.findViewById(R.id.game_activity_buttons);

        assertNotNull("layout with buttons is null",layout_Button);

        rightButton = (Button) activity.findViewById(R.id.right_button);

        assertNotNull("right button is null",rightButton);

        leftButton = (Button) activity.findViewById(R.id.left_button);

        assertNotNull("left button is null",leftButton);

        rotateButton = (Button) activity.findViewById(R.id.rotate_button);

        assertNotNull("right button is null",rotateButton);

        placeButton = (Button) activity.findViewById(R.id.place_button);

        assertNotNull("place button is null",placeButton);

        pauseButton = (Button) activity.findViewById(R.id.pause_button);

        assertNotNull("pause button is null",pauseButton);

        score_view = (TextView) activity.findViewById(R.id.score_view);

        assertNotNull("score view is null", score_view);

    }

    public void testTest(){

    }







    @Override
    public void tearDown() throws Exception{
        super.tearDown();
    }

}
