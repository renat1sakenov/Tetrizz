package com.example.renat.tetris.game.level;

import android.content.Intent;

import com.example.renat.tetris.Game_Activity;
import com.example.renat.tetris.LevelMenu;
import com.example.renat.tetris.Popup_Window;
import com.example.renat.tetris.drawer.ComboDrawer;
import com.example.renat.tetris.game.GameController;
import com.example.renat.tetris.game.PointHandler;
import com.example.renat.tetris.game.ScoreHandler;
import com.example.renat.tetris.game.Sleeper;
import com.example.renat.tetris.game.World;

/**
 * Created by Renat on 23.08.2015.
 */
public class Level extends GameController {

    protected int index;
    protected int points_to_win;

    public Level(Game_Activity game){
        super(game);

        showScore(ScoreHandler.second_score());
    }

    protected void won_level(){
        LevelMenu.set_new_level_enabled(index);
        Popup_Window.init("\n"+"Du hast das Level gewonnen!"+"\n\n"+ "Glückwunsch!", game);

        PointHandler.add_points(points_to_win);


        Intent intent = new Intent(game, Popup_Window.class);
        game.startActivity(intent);
    }


    @Override
    protected void checkForFullRow(){
        int counter = World.checkForFullRow();
        for(int i = 0; i < counter; i ++){
            ScoreHandler.combo_counter_add();

            ScoreHandler.add_second_score();

        }

        adjust_waiting_time();

        if(counter > 1 )
            ComboDrawer.combo_event(block.getPos().x, block.getPos().y);

        showScore(ScoreHandler.second_score());
    }

    @Override
    protected void adjust_waiting_time(){
        Sleeper.adjust_waiting_time(ScoreHandler.second_score());
    }



    @Override
    protected void end_game(){
        Popup_Window.init("\n"+"Du hast versagt!", game);


        Intent intent = new Intent(game, Popup_Window.class);
        game.startActivity(intent);
    }

}
