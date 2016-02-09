package com.example.renat.tetris.test;


import android.content.Context;

import com.example.renat.tetris.Data;
import com.example.renat.tetris.game.WorldSize;

import junit.framework.TestCase;


/**
 * Created by Renat on 20.08.2015.
 */
public class DataTest extends TestCase {



    @Override
    protected void setUp() throws Exception{
        super.setUp();
    }


    public void testSetDefaultOptions(){

        Data.setDefaultOptions();

        assertEquals(Data.USE_BUTTONS, false);
        assertEquals(Data.MUSIC_ON, true);
        assertEquals(Data.SIZE, WorldSize.Size.DEFAULT);
    }


    public void testInit(){
        Context con = null;
        String error = Data.init(con);
        if(error.isEmpty()){
            fail();
        }
    }

    public void testWriteHighscore(){
        Context con = null;
        try{
            Data.writeData(con);
            fail();
        }catch(Exception e){

        }
    }




    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }


}
