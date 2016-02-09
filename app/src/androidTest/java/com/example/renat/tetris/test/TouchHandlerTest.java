package com.example.renat.tetris.test;

import android.graphics.Point;

import com.example.renat.tetris.Data;
import com.example.renat.tetris.drawer.GameView;
import com.example.renat.tetris.TouchHandler;
import com.example.renat.tetris.game.FallingBlock;
import com.example.renat.tetris.game.FallingBlockGenerator;
import com.example.renat.tetris.game.World;
import com.example.renat.tetris.game.WorldSize;

import junit.framework.TestCase;

/**
 * Created by Renat on 20.08.2015.
 */
public class TouchHandlerTest extends TestCase {

    private FallingBlock testBlock;
    private Point pos_b4;
    private Point pos_after;

    @Override
    protected void setUp() throws Exception{
        super.setUp();


        Data.SIZE = WorldSize.Size.DEFAULT;
        World.init();

        GameView.BLOCK_SIZE_X = 500;
        GameView.BLOCK_SIZE_Y = 1000;


        pos_b4 = new Point();
        pos_after = new Point();

    }


    public void testAction(){

        //TEST 1: 0,0 touch event
        testBlock = FallingBlockGenerator.default_();

        pos_b4.set(testBlock.getPos().x,testBlock.getPos().y);
        TouchHandler.action(0, 0, testBlock);
        pos_after.set(testBlock.getPos().x, testBlock.getPos().y);

        //moves one to the left
        assertTrue(pos_b4.x == pos_after.x + 1);



        //TEST 2: -1, -1 touch event
        testBlock = FallingBlockGenerator.default_();

        pos_b4.set(testBlock.getPos().x,testBlock.getPos().y);
        TouchHandler.action(-1,-1,testBlock);
        pos_after.set(testBlock.getPos().x,testBlock.getPos().y);

        //nothing should happen, the method returns
        assertEquals(pos_b4,pos_after);




        //TEST 3: extreme values event
        testBlock = FallingBlockGenerator.default_();

        pos_b4.set(testBlock.getPos().x, testBlock.getPos().y);
        TouchHandler.action(1331, -31, testBlock);
        pos_after.set(testBlock.getPos().x,testBlock.getPos().y);


        assertEquals(pos_b4,pos_after);




        //TEST 4: block == null event : Should not throw exception
        try {
            TouchHandler.action(3, 1545, null);
        }catch(Exception e){
            fail();
        }

    }






    @Override
    protected  void tearDown() throws Exception{
        super.tearDown();
    }
}
