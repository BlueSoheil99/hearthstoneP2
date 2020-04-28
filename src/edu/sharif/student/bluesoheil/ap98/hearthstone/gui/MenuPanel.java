package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Administer;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.GuiConfigs.MenuConfig;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MenuPanel extends GamePanel {

    private MenuConfig properties;
    private String bgPath, fontName;
    private int fontSize;
    private JLabel welcomeMsg;
    private JButton playBtn , shopBtn, statusBtn, collectionBtn, settingBtn;
    private JButton exitBtn;

//    private JLabel logo;


    public MenuPanel() {
        super();
    }

    @Override
    protected void loadConfig() {
        properties = MenuConfig.getInstance();
        bgPath = properties.getBgPath();
        fontName = properties.getFontName();
        fontSize = properties.getFontSize();
    }

    @Override
    protected void createFields() {

        welcomeMsg = new JLabel("Hello "+ Administer.getInstance().getCurrentPlayer().getUserName()+" :) ");
        welcomeMsg.setFont(new Font("Arial", Font.ITALIC, 60) );
        welcomeMsg.setForeground(Color.white);

        playBtn = new MenuButton("Play");
        shopBtn = new MenuButton("Shop");
        statusBtn = new MenuButton("Status");
        collectionBtn = new MenuButton("Collection");
        settingBtn = new MenuButton("Setting");
        exitBtn = new MenuButton("Exit");
        exitBtn.setForeground(Color.RED);
//        exitBtn.setIcon(new ImageIcon("src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Images/exit.png"));

    }


    @Override
    protected void init() {
//        add(NavigationPanel.getInstance(), BorderLayout.EAST);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 15, 5, getWidth()*2/3);

        c.gridx=0;
        c.gridy=0;
        c.anchor = GridBagConstraints.SOUTH;
        c.weighty = 6;
        add(welcomeMsg,c);

        c.anchor = GridBagConstraints.CENTER;

        c.gridy = GridBagConstraints.RELATIVE;
        c.weighty = 1;
//        c.gridwidth =1;
        add(playBtn, c);

        add(shopBtn, c);
        add(statusBtn, c);
        add(collectionBtn, c);
        add(settingBtn, c);
        add(exitBtn, c);

        setPlayListener();
        setShopListener();
        setStatusListener();
        setCollectionListener();
        setSettingListener();
        setExitListener();

    }

    private void setPlayListener(){
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administer.getInstance().runPlay();
            }
        });
    }

    private void setShopListener(){
        shopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administer.getInstance().runShop();
            }
        });    }

    private void setStatusListener(){
        statusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administer.getInstance().runStatus();
            }
        });
    }

    private void setCollectionListener(){
        collectionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administer.getInstance().runCollection();
            }
        });
    }

    private void setSettingListener(){
        settingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administer.getInstance().runSetting();
            }
        });
    }

    private void setExitListener(){
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administer.getInstance().runExit();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage backGround = ImageLoader.loadImage(bgPath);
        g.drawImage(backGround, 0, 0, getWidth(), getHeight(), null);
    }


    private class MenuButton extends JButton {
        MenuButton(String name) {
            super(name);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFont(new Font(fontName, Font.BOLD, fontSize));
            setForeground(Color.WHITE);
        }
    }

}
