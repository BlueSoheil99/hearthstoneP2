package edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {

    private final Font font1 = new Font("serif", Font.BOLD, 40);
    private final Font font2 = new Font("serif", Font.PLAIN, 30);


    public SidePanel() {
        setBackground(new Color(168, 118, 94));
        setBorder(BorderFactory.createMatteBorder(20, 5, 40, 5, new Color(0x562C1C)));
    }

    public SidePanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(168, 118, 94));
    setBorder(BorderFactory.createMatteBorder(20, 5, 40, 5, new Color(0x562C1C)));
    }

    public Font getFont1() {
        return font1;
    }

    public Font getFont2() {
        return font2;
    }

}
