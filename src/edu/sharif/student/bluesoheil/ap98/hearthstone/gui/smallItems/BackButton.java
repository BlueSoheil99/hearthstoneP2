package edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Administer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends JButton {
    private static BackButton instance;

    private BackButton() {
        super("", new ImageIcon("src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Images/back.png"));
        setBorderPainted(false);
        setContentAreaFilled(false);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administer.getInstance().back();
            }
        });
    }
    public static BackButton getInstance(){
        if (instance == null) instance = new BackButton();
        return instance;
    }

}
