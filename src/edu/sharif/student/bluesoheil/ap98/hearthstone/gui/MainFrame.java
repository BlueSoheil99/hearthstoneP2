package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Administer;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
//    LoginPanel loginPanel;
//    Administer administer;

    public MainFrame(){
        setTitle("HearthStone");
        setResizable(false);
        setVisible(true);
//       make it full screen

    }

    public void initFrame(JPanel panel){
        setContentPane(panel);
        setPreferredSize(panel.getSize());
//        getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        setMinimumSize(panel.getSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

}
