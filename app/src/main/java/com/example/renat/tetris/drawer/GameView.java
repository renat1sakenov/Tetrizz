package com.example.renat.tetris.drawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;
import com.example.renat.tetris.ColorMode;
import com.example.renat.tetris.Game_Activity;
import com.example.renat.tetris.ImageLoader;
import com.example.renat.tetris.game.FallingBlock;
import com.example.renat.tetris.game.TetrisBlock;
import com.example.renat.tetris.game.World;

/**
 * Created by Renat on 09.08.2015.
 */
public class GameView extends View {

    public static float BLOCK_SIZE_X;
    public static float BLOCK_SIZE_Y;

    public static int WIDTH;
    public static int HEIGHT;

    private TetrisBlock[][] world;
    private FallingBlock block;
    private Bitmap draw_block;
    private Paint paint;

    private Game_Activity activity;
    private Context context;

    private long moviestart = 0;

    public GameView(Game_Activity activity,int height, int width) {
        super(activity.getApplicationContext());

        context = activity.getApplicationContext();
        this.activity = activity;

        WIDTH = width;
        HEIGHT = height;

        BLOCK_SIZE_X = (float) (width / World.WORLD_WIDTH);
        BLOCK_SIZE_Y = ((height / (float) (World.WORLD_HEIGHT - World.DRAWING_OFFSET)) * Game_Activity.SCREEN_RATIO);



        if(CountDownDrawer.DRAW)
            CountDownDrawer.init(context);

        if(ComboCounterDrawer.DRAW)
            ComboCounterDrawer.init();

        ComboDrawer.init(context);
        LightningEventDrawer.init(this);

        draw_block = ImageLoader.getImage(ImageLoader.Imagename.BLOCK,(int) BLOCK_SIZE_X, (int) BLOCK_SIZE_Y);

        paint = new Paint();

    }

    public void drawGraphics(FallingBlock block){
        this.world = World.get_world();
        this.block = block;

        postInvalidate();
    }

    public Game_Activity getActivity(){
        return activity;
    }


    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        if(CountDownDrawer.DRAW)
            CountDownDrawer.draw(canvas);

        if(ComboDrawer.DRAW)
            ComboDrawer.draw(this, canvas);

        if(LightningEventDrawer.DRAW){
            LightningEventDrawer.draw_lightning(canvas);
        }

        if(ComboCounterDrawer.DRAW){
            ComboCounterDrawer.draw(canvas);
        }

        //drawing whole world
        for(int i = 0; i < world.length; i ++){
            for(int j = 0; j < world[i].length - World.DRAWING_OFFSET; j ++){
                ColorMode.settingColor(world[i][j + World.DRAWING_OFFSET], paint);
                //only draw if block isn't empty
                if(paint.getColor() != Color.TRANSPARENT) {
                    canvas.drawRect(BLOCK_SIZE_X * i, BLOCK_SIZE_Y * j, BLOCK_SIZE_X * i + BLOCK_SIZE_X, BLOCK_SIZE_Y * j + BLOCK_SIZE_Y, paint);
                    canvas.drawBitmap(draw_block, BLOCK_SIZE_X * i, BLOCK_SIZE_Y * j, paint);
                }
            }

        }

        //drawing the block coord
        int x = block.getPos().x;
        int y = block.getPos().y - World.DRAWING_OFFSET;
        Point[] relPos = block.getRelPos();

        ColorMode.settingColor(block.getType(),paint);

        canvas.drawRect(BLOCK_SIZE_X * x, BLOCK_SIZE_Y * y, BLOCK_SIZE_X * x + BLOCK_SIZE_X, BLOCK_SIZE_Y * y + BLOCK_SIZE_Y, paint);
        canvas.drawBitmap(draw_block, BLOCK_SIZE_X * x, BLOCK_SIZE_Y * y, paint);

        //drawing the relPos of block
        for(int i = 0; i < relPos.length; i ++){
            int xr = x + relPos[i].x;
            int yr = y + relPos[i].y;

            canvas.drawRect(BLOCK_SIZE_X*xr,BLOCK_SIZE_Y*yr,BLOCK_SIZE_X*xr+BLOCK_SIZE_X, BLOCK_SIZE_Y * yr + BLOCK_SIZE_Y, paint);
            canvas.drawBitmap(draw_block,BLOCK_SIZE_X*xr,BLOCK_SIZE_Y*yr,paint);
        }
    }
}
