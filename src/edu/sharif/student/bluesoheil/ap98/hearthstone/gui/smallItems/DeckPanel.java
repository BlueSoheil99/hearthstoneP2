package edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems;

import javax.swing.*;
import java.awt.*;

public class DeckPanel extends JPanel {
    public DeckPanel(int width, int height){
        setPreferredSize(new Dimension(width,height));
        setBackground(new Color(168, 118, 94));
        createComponents();
        addComponents();
        setBorder(BorderFactory.createMatteBorder(20,5,40,5,new Color(0x562C1C)));
    }

//    public DeckPanel
    private void addComponents() {
    }

    private void createComponents() {
    }
}
