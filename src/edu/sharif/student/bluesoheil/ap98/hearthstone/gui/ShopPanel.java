package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends GamePanel {
    private JPanel shopFilter;
    private CardPanel cardPanel;

    @Override
    protected void loadConfig() {

    }

    @Override
    protected void createFields() {
        cardPanel = new CardPanel(getWidth()*4/5 , getHeight());
        shopFilter = new JPanel();
        shopFilter.setPreferredSize(new Dimension(getWidth()/5 , getHeight()));
        setBackground(new Color(168, 118, 94));
        setBorder(BorderFactory.createMatteBorder(20,5,40,5,new Color(0x562C1C)));
        createFilterComponents();
        initFilterComponents();
    }

    @Override
    protected void init() {
        setLayout(new BorderLayout());
        add(cardPanel , BorderLayout.EAST);
        add(shopFilter,BorderLayout.WEST);

    }

    private void createFilterComponents(){
        

    }

    private void initFilterComponents(){



        //in the end add exit and back button
    }

}

