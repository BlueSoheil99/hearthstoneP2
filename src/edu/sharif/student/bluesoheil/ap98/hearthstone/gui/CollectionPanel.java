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
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.GuiConfigs.CollectionConfig;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.GuiConfigs.GuiConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class CollectionPanel extends GamePanel {
    private CollectionConfig properties;
    private FilterPanel filterPanel;
    private CardPanel cardPanel;
    private DeckPanel deckPanel;
    private CollectionHandler collectionHandler;

    public CollectionPanel() {
        super();
    }

    @Override
    protected void loadConfig() {
        properties = CollectionConfig.getInstance();
    }

    @Override
    protected void createFields() {
        collectionHandler = CollectionHandler.getInstance();
        filterPanel = new FilterPanel(properties.getFilterWidth(), getHeight());
        deckPanel = new DeckPanel(properties.getDeckWidth(), getHeight());
        cardPanel = new CardPanel();
        cardPanel.setCards( collectionHandler.filterCards("" , true , true , -1 , "All")
                ,  properties.getNumberOfCardsInRow());
        addListenersToPanels();
    }

    @Override
    protected void init() {
        setLayout(new BorderLayout());
        add(filterPanel, BorderLayout.WEST);
        add(new JScrollPane(cardPanel), BorderLayout.CENTER);
        add(deckPanel, BorderLayout.EAST);
////        revalidate();
//        cardPanel.setCards( collectionHandler.filterCards("" , true , true , -1 , "All"));

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
                cardPanel.setCards(cards , properties.getNumberOfCardsInRow());
            }
        });


        deckPanel.setClickListener(new ClickListener() {
            @Override
            public void select(String objName) {

            }
        });

    }


}
