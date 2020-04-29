package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Interefaces.ClickListener;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.CardShape;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.cards.Card;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.GuiConfigs.GuiConstants;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CardPanel  extends JPanel implements ActionListener {

    private ArrayList<CardShape> cards;
    private CardShape selectedCard;
    private ClickListener clickListener;

    public CardPanel (){
        setBackground(new Color(168, 118, 94));
        setBorder(BorderFactory.createMatteBorder(5,5,5,5,new Color(0x562C1C)));

    }

    void setCards(ArrayList<CardShape> cardShapes){
        cards = cardShapes;
        paintCardsInPanel();
        for (CardShape cardShape : cards) cardShape.addActionListener(this);

    }

    void paintCardsInPanel(){
        int numberOfCardsInRow = GuiConstants.getInstance().getNumberOfCardsInRow();
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy=0;
        gc.gridx=0;
        int x =0;
        for (CardShape cardShape : cards) {
            if (x > numberOfCardsInRow - 1) {
                x = 0;
                gc.gridy++;
                gc.gridx =0;
            }
            add(cardShape, gc);
            x++;
            gc.gridx = x;
        }
    }

    public void setClickListener(ClickListener listener){
        this.clickListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        selectedCard = (CardShape)e.getSource();
        if (clickListener != null){
            clickListener.select(selectedCard.getCardName());
        }
    }
}
