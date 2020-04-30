package edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems;

import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.GuiConfigs.GuiConstants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

//todo change JButton to something less complicated
public class CardShape extends JButton {
    private static final int CARD_WIDTH = GuiConstants.getInstance().getCardWidth();
    private static final int CARD_HEIGHT = GuiConstants.getInstance().getCardHeight();
    private String cardName;
    private ImageIcon icon;

    public CardShape(String cardName, BufferedImage image) {
        this.cardName = cardName;
        icon = new ImageIcon(image);
        Image img = icon.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        setIcon(icon);
        setOpaque(false);
        setContentAreaFilled(false);

    }

    public CardShape(String cardName, BufferedImage image, boolean owned) {
      this(cardName , image);
      if (!owned){
          Border border = getBorder();
          Border border1 = BorderFactory.createMatteBorder(5,10,10,5,Color.RED);
          setBorder(BorderFactory.createCompoundBorder(border1 , border));
      }
    }

    public String getCardName() {
        return cardName;
    }

    public static int getCardWidth() {
        return CARD_WIDTH;
    }

    public static int getCardHeight() {
        return CARD_HEIGHT;
    }
}
