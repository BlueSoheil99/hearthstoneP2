package edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration.GuiConfigs;


import edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration.ConfigLoader;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration.Configs;

public class MenuConfig extends GuiConfig {
    private static MenuConfig config;
    private Configs properties;
    private int width, height;

    private MenuConfig(){
        super();
    }

    public static MenuConfig getInstance(){
        if (config == null){
            config = new MenuConfig();
        }
        return config;
    }

    @Override
    public void setProperties() {
        properties = ConfigLoader.getInstance().getMenuProperties();
    }

    @Override
    public void initialize() {
//        width = properties.readInt("width");
//        height = properties.readInt("height");
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
