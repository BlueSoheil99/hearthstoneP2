package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration.GuiConfigs.StartPanelConfig;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class StartPanel extends JPanel {
//    private JLabel backGround;
    private JLabel logo;
    private StartPanelConfig config;
    private String logoPath, backGroundPath;
    private int width, height;
    private int labelWidth , fieldWidth;
    private int X1Inset,X2Inset,YInset;
    protected int x1, x2, y0;

    public StartPanel(){
        loadConfig();
//        loadNormally();
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

        ImageIcon imageIcon = new ImageIcon(logoPath);
        logo = new JLabel("",imageIcon,JLabel.CENTER);
        logo.setBounds(0,0,getWidth(),getHeight()/2);
        add(logo);
        createFields();
        setLayout(null);
        init();
    }

    private void loadConfig(){
        config = StartPanelConfig.getInstance();
        logoPath = config.getLogoURL();
        backGroundPath = config.getBackGroundURL();
        width = config.getWidth();
        height = config.getHeight();
        labelWidth = config.getLabelWidth();
        fieldWidth = config.getFieldWidth();
        X1Inset=width/2-(labelWidth+fieldWidth)/2;
        X2Inset=X1Inset+labelWidth;
        YInset= 4*height/11;
    }
    private void loadNormally(){
        logoPath = "src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Images/Hearthstone_2016_logo.png";
        backGroundPath = "src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Images/7ho000lptrr01.jpg";
        width = 640;
        height = 480;
        labelWidth = 80;
        fieldWidth = 150;
        X1Inset=width/2-(labelWidth+fieldWidth)/2;
        X2Inset=X1Inset+labelWidth;
        YInset= 4*height/11;
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
        BufferedImage img = ImageLoader.loadImage(backGroundPath);
        g.drawImage(img,0,0,width,height,null);
    }

    protected abstract void createFields();

    protected void init(){
        x1 = getX1Inset();
        x2 = getX2Inset();
        y0 = getYInset();
    }

}
