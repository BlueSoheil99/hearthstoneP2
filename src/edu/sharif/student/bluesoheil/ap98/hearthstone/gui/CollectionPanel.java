package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;

import edu.sharif.student.bluesoheil.ap98.hearthstone.CardFilter;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Interefaces.ClickListener;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Interefaces.FilterListener;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.CardPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.GamePanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.collection.CollectionHandler;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.CardShape;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.DeckPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.collection.FilterEvent;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.collection.FilterPanel;

import java.awt.*;
import java.util.ArrayList;


public class CollectionPanel extends GamePanel {
    private FilterPanel filterPanel;
    private CardPanel cardPanel;
    private DeckPanel deckPanel;
    private CollectionHandler collectionHandler;

    public CollectionPanel() {
        super();
        collectionHandler = CollectionHandler.getInstance();
    }

    @Override
    protected void loadConfig() {

    }

    @Override
    protected void createFields() {
        filterPanel = new FilterPanel(getWidth() * 2 / 9, getHeight());
        deckPanel = new DeckPanel(getWidth() * 2 / 9, getHeight());
        cardPanel = new CardPanel();
//        cardPanel.setCards( collectionHandler.filterCards());
        addListenersToPanels();
    }

    @Override
    protected void init() {
        setLayout(new BorderLayout());
        add(filterPanel, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);
        add(deckPanel, BorderLayout.EAST);
    }

    private void addListenersToPanels() {
        cardPanel.setClickListener(new ClickListener() {
            @Override
            public void select(String objName) {

            }
        });


        filterPanel.setFilterListener(new FilterListener() {
            @Override
            public void filter(FilterEvent filter) {
                ArrayList<CardShape> cards = collectionHandler.filterCards(filter.getRegex(), filter.isOwned(),
                        filter.isNotOwned() , filter.getManaCost() , filter.getHero());
                cardPanel.setCards(cards);
            }
        });


        deckPanel.setClickListener(new ClickListener() {
            @Override
            public void select(String objName) {

            }
        });

    }


}
