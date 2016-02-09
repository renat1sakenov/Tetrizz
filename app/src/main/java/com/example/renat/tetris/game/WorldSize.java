package com.example.renat.tetris.game;

import android.graphics.Point;

/**
 * Created by Renat on 20.08.2015.
 */
public class WorldSize {


    public enum Size{
        VERY_BIG,
        BIG,
        DEFAULT,
        SMALL,
        VERY_SMALL;
    }

    public static Point getSize(Size size, boolean buttons){

        switch (size){
            case VERY_BIG:
                if(buttons)
                    return new Point(60,60);
                else return new Point(60,48);

            case BIG:
                if(buttons)
                    return new Point(30,30);
                else return new Point(30,24);
            case DEFAULT:
                if(buttons)
                    return new Point(20,20);
                else return new Point(20,16);
            case SMALL:
                if(buttons)
                    return new Point(10,10);
                else return new Point(10,10);
            case VERY_SMALL:
                if(buttons)
                    return new Point(6,6);
                else return new Point(6,6);
        }

        return  null;
    }

    public static boolean isAValidSize(String str){
        for(Size s : Size.values()){
            if(str.equals(s.toString()))
                return true;
        }
        return false;
    }

}
