package edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration.LogicConfigs;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration.ConfigLoader;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration.Configs;

import java.util.ArrayList;

public class PlayerConfig {
    private static PlayerConfig instance;
    private Configs config;
    private String[] defaultCards;

    private PlayerConfig(){
        config = ConfigLoader.getInstance().getPlayerProperties();
        defaultCards = config.readArrays("defaultPlayerTotalCards");
    }

    public static PlayerConfig getInstance(){
        if (instance==null)  instance = new PlayerConfig();
        return instance;
    }

    public String[] getDefaultCards(){
        return defaultCards;
    }

    public ArrayList<String> getDefaultDeckCards(){
        return null;
    }


}
