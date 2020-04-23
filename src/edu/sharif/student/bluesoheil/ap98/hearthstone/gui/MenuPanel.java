package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration.GuiConfigs.MenuConfig;

public class MenuPanel extends GamePanel {
//    private static MenuPanel instance;
    private MenuConfig properties;
//    private int width, height;

    public MenuPanel(){
        super();
        loadConfig();
//        setSize(width, height);
    }


//    public MenuPanel getInstance(){
//        if (instance == null){
//            instance = new MenuPanel();
//        }
//        return instance;
//    }
    private void loadConfig() {
        properties = MenuConfig.getInstance();
//        width = properties.getWidth();
//        height = properties.getHeight();
    }

    @Override
    protected void createFields() {

    }


    @Override
    protected void init() {

    }

    private void initialize(){

    }

}
