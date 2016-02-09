package com.example.renat.tetris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Renat on 29.09.2015.
 */
public class ImageLoader {


    private static int width;
    private static int height;

    private static Context context;

    public static void init(Context context_){
        context = context_;
    }

    public enum Imagename{
        BLOCK,
        CLOCK,
        COMBO;
    }

    public static Bitmap getImage(Imagename name, int width_, int height_){

        width = width_;
        height = height_;

        switch (name){
            case BLOCK:
                return get(R.drawable.block);
            case CLOCK:
                return get(R.drawable.kuckuckzu);
            case COMBO:
                return get(R.drawable.combo1);
        }

        width = height = 0;

        return null;
    }


    private static Bitmap get(int id){


        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), id);
        bm = Bitmap.createScaledBitmap(bm,width,height,false);

        return bm;
    }
}
