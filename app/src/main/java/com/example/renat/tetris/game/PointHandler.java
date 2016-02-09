package com.example.renat.tetris.game;

import com.example.renat.tetris.Data;
import com.example.renat.tetris.game.upgrade.Upgrade;
import com.example.renat.tetris.game.upgrade.UpgradeHandler;

/**
 * Created by Renat on 24.08.2015.
 */
public class PointHandler {

    private static int POINTS;

    public static void init(){
        POINTS = Data.POINTS;
    }


    public static String buy_upgrade(Upgrade up){
        if(POINTS - up.getCost() < 0)
            return "Nicht genung Punkte!";
        else {
            POINTS -= up.getCost();
            UpgradeHandler.add_upgrade(up);
        }
        return "";
    }

    public static void add_points(int p){
        POINTS += p;
    }

}
