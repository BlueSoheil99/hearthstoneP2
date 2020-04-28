package edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class CardShape  extends JLabel {
    private String cardName;
    private BufferedImage image;

    public  CardShape(String cardName , BufferedImage image){
        this.cardName = cardName;
        this.image = image;
    }
    // todo set CardListener
}
