package com.example.renat.tetris.game;

import android.graphics.Point;


/**
 * This class handles the current Tetrisblock which the player can rotate and position.
 * Created by Renat on 22.07.2015.
 */
public class FallingBlock {

    private Point pos;
    private Point[] relPos;
    private TetrisBlock type;

    public static final int NUMBER_BLOCKS = 7;

    public FallingBlock(int x, int y, TetrisBlock type) throws IllegalArgumentException{

        pos = new Point();

        if(type == null || type == TetrisBlock.EMPTY)
            throw new IllegalArgumentException("type is not valid");

        this.type = type;

        setPos(x, y);
        relPos = new Point[3];

        switch(type){
            case L:
                relPos[0] = new Point(0,-1);
                relPos[1] = new Point(0,1);
                relPos[2] = new Point(1,1);
                break;
            case SNAKE:
                relPos[0] = new Point(-1,0);
                relPos[1] = new Point(1,0);
                relPos[2] = new Point(2,0);
                break;
            case T:
                relPos[0] = new Point(1,0);
                relPos[1] = new Point(-1,0);
                relPos[2] = new Point(0,1);
                break;
            case SQUARE:
                relPos[0] = new Point(1,0);
                relPos[1] = new Point(0,1);
                relPos[2] = new Point(1,1);
                break;
            case Z:
                relPos[0] = new Point(-1,0);
                relPos[1] = new Point(0,1);
                relPos[2] = new Point(1,1);
                break;
            case Backwards_L:
                relPos[0] = new Point(0,-1);
                relPos[1] = new Point(0,1);
                relPos[2] = new Point(-1,1);
                break;
            case Backwards_Z:
                relPos[0] = new Point(1,0);
                relPos[1] = new Point(0,1);
                relPos[2] = new Point(-1,1);
                break;
        }
    }

    public void setPos(int x, int y) throws  IllegalArgumentException{
        if(!World.checkBoundaries(x,y))
            throw new IllegalArgumentException("Wrong position coordinates");

        pos.set(x,y);
    }

    public Point getPos(){
        return pos;
    }

    public Point[] getRelPos(){
        return relPos;
    }

    public TetrisBlock getType(){
        return type;
    }


    public void rotateRight(){
        rotateRight(this.relPos);
    }


    public Point[] rotateRight(Point[] relPos){
        for(int i = 0; i < relPos.length; i ++){
            int x_o;
            x_o = relPos[i].x;
            relPos[i].x = relPos[i].y;
            relPos[i].y = -x_o;
        }
        return relPos;
    }

    public Point[] getRotation(){

        Point[] relPos_ = new Point[relPos.length];
        for(int i = 0; i < relPos_.length; i ++){
            relPos_[i] = new Point(relPos[i].x,relPos[i].y);
        }
        return rotateRight(relPos_);
    }


    public void goLeft() throws IllegalArgumentException{
        setPos(pos.x-1,pos.y);
    }

    public void goRight() throws IllegalArgumentException{
        setPos(pos.x+1,pos.y);
    }

    public void goDown() throws IllegalArgumentException{
        setPos(pos.x,pos.y+1);
    }

}
