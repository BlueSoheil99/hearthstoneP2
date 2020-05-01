package edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems;

import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.GuiConfigs.GuiConstants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.nio.file.Files;

//todo change JButton to something less complicated
public class CardShape extends JButton {
    private static final int CARD_WIDTH = GuiConstants.getInstance().getCardWidth();
    private static final int CARD_HEIGHT = GuiConstants.getInstance().getCardHeight();
    private String cardName;
    private ImageIcon icon;

    public CardShape(String cardName, BufferedImage image) {
        this.cardName = cardName;
//        setPreferredSize(new Dimension(200,300));
        if (image != null) icon = new ImageIcon(image);
        setPreferredSize(new Dimension(CARD_WIDTH , CARD_HEIGHT));
//        Image img = icon.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
//        icon = new ImageIcon( icon.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH)); //todo make it faster!
        setIcon(icon);
        setOpaque(false);
        setContentAreaFilled(false);

    }

    public CardShape(String cardName, BufferedImage image, boolean owned) {
        this(cardName, image);
        if (!owned) {
            // 1
//          Border border = getBorder();
//          Border border1 = BorderFactory.createMatteBorder(5,10,10,5,Color.RED);
//          setBorder(BorderFactory.createCompoundBorder(border1 , border));
            // 2
//            BufferedImage img =copyImage(image);
//            icon = new ImageIcon(colorImage(img));
            // 3
            setContentAreaFilled(true);
            setBackground(new Color(192, 94, 78));
            setOpaque(true);
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

    private BufferedImage colorImage(BufferedImage image) {
//        BufferedImage image = copyImage(oldImage);
        int width = image.getWidth();
        int height = image.getHeight();

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int p = image.getRGB(xx, yy);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                int avg = (r + g + b) / 3;
                p = (a << 24) | (avg << 16) | (avg << 8) | avg;
                image.setRGB(xx, yy, p);
//                image.setRGB();
            }
        }
        return image;
    }

    public BufferedImage copyImage(BufferedImage coverImage) {
        ColorModel colorModel = coverImage.getColorModel();
        boolean isAlphaPremultiplied = coverImage.isAlphaPremultiplied();
        WritableRaster raster = coverImage.copyData(null);
        BufferedImage newImage = new BufferedImage(colorModel, raster,
                isAlphaPremultiplied, null);
        return newImage;
    }
}
