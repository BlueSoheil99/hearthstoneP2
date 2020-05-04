package edu.sharif.student.bluesoheil.ap98.hearthstone.gui.play;

import edu.sharif.student.bluesoheil.ap98.hearthstone.connectors.Administer;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.GamePanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.CardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayPanel extends GamePanel {

    private PauseMenu pauseMenu;
    private CardPanel playerCards;
    private CardPanel opponentCards;


    @Override
    protected void loadConfig() {

    }

    @Override
    protected void createFields() {
        pauseMenu = PauseMenu.getInstance();
    }

    @Override
    protected void init() {

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy=0;
        c.gridx=0;

        ImageIcon icon = new ImageIcon("src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Images/back.png");
        JButton button = new JButton("", icon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        c.gridy=1;
        add(button,c);
        c.gridx=1;
        setLayout(new BorderLayout());
        add(new EventBox() , BorderLayout.SOUTH);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administer.getInstance().back();
            }
        });
    }
}
