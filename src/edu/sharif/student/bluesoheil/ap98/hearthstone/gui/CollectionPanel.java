package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;

import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.DeckPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.FilterPanel;

import java.awt.*;


public class CollectionPanel extends GamePanel {
    private FilterPanel filterPanel;
    private CardPanel cardPanel;
    private DeckPanel deckPanel;

    @Override
    protected void loadConfig() {

    }

    @Override
    protected void createFields() {
        filterPanel = new FilterPanel(getWidth()*2/9 , getHeight());
        cardPanel = new CardPanel(getWidth()*5/9,getHeight());
        deckPanel = new DeckPanel(getWidth()*2/9 , getHeight());
    }

    @Override
    protected void init() {
        setLayout(new BorderLayout());
        add(filterPanel , BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);
        add(deckPanel, BorderLayout.EAST);
//        add(new JScrollPane(cardPanel), BorderLayout.CENTER);
//        add(new JScrollPane(deckPanel), BorderLayout.EAST);
    }
}
