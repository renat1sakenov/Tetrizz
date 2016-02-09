package com.example.renat.tetris.test;


import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.InstrumentationTestCase;
import android.widget.Button;
import android.widget.TextView;
import com.example.renat.tetris.Main;
import com.example.renat.tetris.R;


/**
 * Created by Renat on 18.08.2015.
 */


public class MainTest extends ActivityUnitTestCase<Main> {


    private Main main;
    private TextView title;
    private Button play_button;
    private Button highscore_button;
    private Button menu_button;
    private Button exit_button;

    private String expected;
    private String actual;

    public MainTest(){
        super(Main.class);
    }

    //set up all views and check if they're all correct
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        startActivity(new Intent(),null,null);

        main = getActivity();

        assertNotNull("Main is null",main);

        title = (TextView)main.findViewById(R.id.Title);

        assertNotNull("Title is null",title);

        play_button = (Button)main.findViewById(R.id.PlayButton);

        assertNotNull("PlayButton is null",play_button);
        menu_button = (Button)main.findViewById(R.id.MenuButton);

        assertNotNull("menuButton is null",menu_button);
        exit_button = (Button)main.findViewById(R.id.ExitButton);

        assertNotNull("ExitButton is null",exit_button);


    }


    //test if the actual values match the string.xml values
    public void testStrings(){
    /*
        expected = main.getString(R.string.Tetris);
        actual = title.getText().toString();

        assertEquals(expected,actual);

        expected = main.getString(R.string.PlayButton);
        actual = play_button.getText().toString();

        assertEquals(expected,actual);

        expected = main.getString(R.string.HighscoreButton);
        actual = highscore_button.getText().toString();

        assertEquals(expected,actual);

        expected = main.getString(R.string.Menu);
        actual = menu_button.getText().toString();

        assertEquals(expected,actual);

        expected = main.getString(R.string.ExitButton);
        actual = exit_button.getText().toString();

        assertEquals(expected,actual);
        */
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


}