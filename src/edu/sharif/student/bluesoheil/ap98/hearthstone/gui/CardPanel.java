package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Interefaces.ClickListener;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.CardShape;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.SidePanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.cards.Card;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.GuiConfigs.GuiConstants;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.log.LogTypes;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.log.Logger;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CardPanel extends SidePanel implements ActionListener {

    private ArrayList<CardShape> cards;
    private CardShape selectedCard;
    private ClickListener clickListener;

    public CardPanel() {
        super();
    }

    void setCards(ArrayList<CardShape> cardShapes, int cardsInRow) {
        cards = cardShapes;
        paintCardsInPanel(cardsInRow);
        for (CardShape cardShape : cards) cardShape.addActionListener(this);
    }
    void setCards(ArrayList<CardShape> cardShapes) {
//        cards = cardShapes;
//        paintCardsInPanel(GuiConstants.getInstance().getNumberOfCardsInRow());
//        for (CardShape cardShape : cards) cardShape.addActionListener(this);
        setCards(cardShapes , GuiConstants.getInstance().getNumberOfCardsInRow());

    }

    private void paintCardsInPanel(int numberOfCardsInRow) {
        removeAll();
        revalidate();
        repaint();
//        int numberOfCardsInRow = GuiConstants.getInstance().getNumberOfCardsInRow();
//        int numberOfCardsInRow = getWidth() / CardShape.getCardWidth() - 1;
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 0;
        gc.gridx = 0;
        int x = 0;
        for (CardShape cardShape : cards) {
            if (x > numberOfCardsInRow - 1) {
                x = 0;
                gc.gridy++;
                gc.gridx = 0;
            }
            add(cardShape, gc);
            x++;
            gc.gridx = x;
        }
//        revalidate();
    }

    public void setClickListener(ClickListener listener) {
        this.clickListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        selectedCard = (CardShape) e.getSource();
        if (clickListener != null) {
            clickListener.select(selectedCard.getCardName());
        }
        Logger.log(LogTypes.CLICK_BUTTON, "card: " + selectedCard.getCardName() + "  selected.");
    }
}
