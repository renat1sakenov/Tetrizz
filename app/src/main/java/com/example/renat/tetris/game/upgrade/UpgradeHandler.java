package com.example.renat.tetris.game.upgrade;

import java.util.ArrayList;

/**
 * Created by Renat on 24.08.2015.
 */
public class UpgradeHandler {

    private static ArrayList<Upgrade> myUpgrades;

    public static void init_empty_upgradelist(){
        myUpgrades = new ArrayList<>();
    }

    public static void add_upgrade(Upgrade up){
        myUpgrades.add(up);
    }

    public ArrayList<Upgrade> get_my_upgrades(){
        return myUpgrades;
    }
}
