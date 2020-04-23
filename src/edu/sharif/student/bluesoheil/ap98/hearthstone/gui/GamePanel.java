package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration.GuiConfigs.GuiConstants;

import javax.swing.*;
import java.awt.*;

public abstract class GamePanel extends JPanel {
    private int width= GuiConstants.getInstance().getGameWidth();
    private int height= GuiConstants.getInstance().getGameHeight();

    public GamePanel(){

        setSize(width,height);
        setBackground(Color.white);
        //todo read about layouts
        setLayout(null);
        createFields();
        init();

    }

    protected abstract void createFields();
    protected abstract void init();

}
