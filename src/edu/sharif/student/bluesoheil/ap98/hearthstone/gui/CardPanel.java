package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;

import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.CardShape;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardPanel  extends JPanel {

    private CardShape selectedCard;

    public CardPanel (ArrayList<Card> givenCards , int width, int height){
        setPreferredSize(new Dimension(width, height));

    }
    public CardPanel (int width, int height){
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(143, 100, 80));
        setBorder(BorderFactory.createMatteBorder(5,5,5,5,new Color(0x562C1C)));

    }

}
