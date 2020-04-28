package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Administer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayPanel extends GamePanel {
    @Override
    protected void loadConfig() {

    }

    @Override
    protected void createFields() {

    }

    @Override
    protected void init() {
        JLabel label = new JLabel("under construction !");
        label.setFont(new Font("arial", Font.BOLD, 80));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy=0;
        c.gridx=0;
        add(label , c);


        ImageIcon icon = new ImageIcon("src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Images/back.png");
        JButton button = new JButton("", icon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        c.gridy=1;
        add(button,c);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administer.getInstance().back();
            }
        });
    }
}
