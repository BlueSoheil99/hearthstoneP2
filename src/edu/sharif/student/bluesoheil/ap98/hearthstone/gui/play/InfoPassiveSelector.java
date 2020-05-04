package edu.sharif.student.bluesoheil.ap98.hearthstone.gui.play;

import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.CardPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.interefaces.ClickListener;

import javax.swing.*;


public class InfoPassiveSelector extends JFrame {
    private CardPanel panel;

    public InfoPassiveSelector(){
        setTitle("Info Passives");
        setResizable(false);
        setVisible(true);
        setComponents();
        init();

    }

    private void setComponents() {
        panel = new CardPanel();
        panel.setClickListener(new ClickListener() {
            @Override
            public void select(String objName) {
                System.out.println(objName+" selected");
            }
        });
    }

    private void init() {
        setContentPane(panel);
        setPreferredSize(panel.getSize());
        setMinimumSize(panel.getSize());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pack();
        revalidate();
        setLocationRelativeTo(null);
    }




}
