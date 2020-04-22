package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class StartPanel extends JPanel {

    private JLabel backGround;
    private JLabel logo;
    private int width = 640;
    private int height = 480;
    private int labelWidth=80;
    private int fieldWidth=150;
    private int X1Inset=width/2-(labelWidth+fieldWidth)/2;
    private int X2Inset=X1Inset+labelWidth;
    private int YInset= 4*height/11;
//    private int;


//todo don't forget to use properties for configuration
    public StartPanel(){
        setSize(new Dimension( width , height));
        setBackground(Color.white);

        //backGround
//        ImageIcon imageIcon = new ImageIcon("src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Images/blizzardApp_background_hearthstone-b10f6f93fbcb858907dcae8dccaa83508833727f3fc3d2bdebd9770944e4ae64fcd82d1423f835ec3a933bb9bb8f37def8f3996e421640573a068fee4b4a88a5.jpg");
//        Image image= imageIcon.getImage();
//        image.getScaledInstance(480,360,Image.SCALE_SMOOTH);
//        imageIcon = new ImageIcon(image);
//        backGround = new JLabel("",imageIcon,JLabel.CENTER);
//        backGround.setBounds(0,0,getWidth(),getHeight());
//        add(backGround);

        //todo handle configuration
        ImageIcon imageIcon = new ImageIcon("src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Images/Hearthstone_2016_logo.png");
        logo = new JLabel("",imageIcon,JLabel.CENTER);
        logo.setBounds(0,0,getWidth(),getHeight()/2);
        add(logo);
//        repaint();
        createFields();
        setLayout(null);
        init();
    }

    public int getLabelWidth() {
        return labelWidth;
    }
    public int getFieldWidth() {
        return fieldWidth;
    }
    public int getX1Inset() {
        return X1Inset;
    }
    public int getX2Inset() {
        return X2Inset;
    }
    public int getYInset() {
        return YInset;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img = ImageLoader.loadImage("src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Images/7ho000lptrr01.jpg");
        g.drawImage(img,0,0,getWidth(),getHeight(),null);
    }

    protected abstract void createFields();
    protected abstract void init();
}
