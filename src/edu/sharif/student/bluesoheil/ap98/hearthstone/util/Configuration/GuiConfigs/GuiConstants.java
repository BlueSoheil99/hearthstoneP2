package edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.GuiConfigs;

import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.ConfigLoader;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.Configs;

public class GuiConstants extends GuiConfig {
    private static GuiConstants instance;
    private Configs properties;
    private int gameWidth, gameHeight;

    private GuiConstants(){
        super();
    }

    public static GuiConstants getInstance(){
        if (instance == null) instance = new GuiConstants();
        return instance;
    }

    @Override
    protected void setProperties() {
        properties = ConfigLoader.getInstance().getGuiConstantsProperties();
    }

    @Override
    protected void initialize() {
        gameHeight = properties.readInt("gameHeight");
        gameWidth = properties.readInt("gameWidth");

    }


    public int getGameWidth(){
        return gameWidth;
    }
    public int getGameHeight(){
        return gameHeight;
    }


}
