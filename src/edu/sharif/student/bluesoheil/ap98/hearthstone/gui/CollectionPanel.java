package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Administer;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Interefaces.ClickListener;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Interefaces.DeckHandlerListener;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Interefaces.FilterListener;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.collection.CollectionHandler;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.CardShape;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.DeckPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.collection.FilterEvent;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.collection.FilterPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Heroes.HeroTypes;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.GuiConfigs.CollectionConfig;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.log.LogTypes;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.log.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class CollectionPanel extends GamePanel {
    private CollectionConfig properties;
    private FilterPanel filterPanel;
    private CardPanel cardPanel;
    private CardPanel deckCardsPanel;
    private DeckPanel deckPanel;
    private CollectionHandler collectionHandler;
    private String selectedCard, selectedDeck;


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
        deckCardsPanel = new CardPanel();
        cardPanel.setCards(collectionHandler.filterCards("", true, true, -1, "All")
                , properties.getNumberOfCardsInRow());
        deckPanel.setDecks(collectionHandler.getDecks());
        filterPanel.setDeckHandlerEditable(false);
        addListenersToPanels();
    }

    @Override
    protected void init() {
        setLayout(new BorderLayout());
        add(new JScrollPane(cardPanel), BorderLayout.CENTER);
        add(new JScrollPane(deckCardsPanel), BorderLayout.SOUTH);
        add(filterPanel, BorderLayout.WEST);
        add(deckPanel, BorderLayout.EAST);
//        add(new JScrollPane(deckPanel), BorderLayout.EAST);

    }

    private void addListenersToPanels() {
        cardPanel.setClickListener(new ClickListener() {
            @Override
            public void select(String objName) {
                selectedCard = objName;
                if (selectedDeck != null) filterPanel.isSelectedCardFromDeck(false);
                deckCardsPanel.unselectCard();
            }
        });
        //////////////////////////////
        //////////////////////////////
        deckCardsPanel.setClickListener(new ClickListener() {
            @Override
            public void select(String objName) {
                selectedCard = objName;
                if (selectedDeck != null) filterPanel.isSelectedCardFromDeck(true);
                cardPanel.unselectCard();
            }
        });
        //////////////////////////////
        //////////////////////////////
        deckPanel.setClickListener(new ClickListener() {
            @Override
            public void select(String objName) {
                selectedDeck = objName;
                selectedCard = null;
                cardPanel.unselectCard();
                filterPanel.setDeckHandlerEditable(true);
                deckCardsPanel.setCards(collectionHandler.getDeckCards(selectedDeck), 20);
                //20 is maximum of cards in deck
                revalidate();
            }
        });
        /////////////////////////////
        /////////////////////////////
        filterPanel.setFilterListener(new FilterListener() {
            @Override
            public void filter(FilterEvent filter) {
                ArrayList<CardShape> cards = collectionHandler.filterCards(filter.getRegex(), filter.isOwned(),
                        filter.isNotOwned(), filter.getManaCost(), filter.getHero());

                cardPanel.setCards(cards, properties.getNumberOfCardsInRow());
                Logger.log(LogTypes.COLLECTION, "cards filtered with {regex,owned,notOwned,manaCost,hero}= {" +
                        filter.getRegex() + "," + Boolean.toString(filter.isOwned()) + "," + Boolean.toString(filter.isNotOwned())
                        + "," + Integer.toString(filter.getManaCost()) + "," + filter.getHero() + "}");
            }
        });
        ////////////////////////////
        ////////////////////////////
        filterPanel.setDeckHandlerListener(new DeckHandlerListener() {
            @Override
            public void renameDeck(String newName) {
                try {
                    collectionHandler.renameDeck(selectedDeck, newName);
                    JOptionPane.showMessageDialog(null, selectedDeck + " has been renamed to " + newName
                            , "Done!", JOptionPane.INFORMATION_MESSAGE);
                    revalidateSelections();
                } catch (Exception e) {
                    handleException(e);
                }
            }

            @Override
            public void deleteDeck() {
                try {
                    collectionHandler.deleteDeck(selectedDeck);
                    JOptionPane.showMessageDialog(null, selectedDeck + " has been deleted from player's decks"
                            , "Done!", JOptionPane.INFORMATION_MESSAGE);
                    revalidateSelections();
                } catch (Exception e) {
                    handleException(e);
                }
            }

            @Override
            public void removeCard() {
                try {
                    collectionHandler.removeCardFromDeck(selectedDeck, selectedCard);
                    JOptionPane.showMessageDialog(null, selectedCard + " has been removed from " + selectedDeck
                            , "Done!", JOptionPane.INFORMATION_MESSAGE);
                    revalidateSelections();
                } catch (Exception e) {
                    handleException(e);
                }
            }

            @Override
            public void addCard() {
                if (collectionHandler.playerHas(selectedCard)) {
                    try {
                        collectionHandler.addCardToDeck(selectedDeck, selectedCard);
                        JOptionPane.showMessageDialog(null, selectedCard + " has been added to " + selectedDeck
                                , "Done!", JOptionPane.INFORMATION_MESSAGE);
                        revalidateSelections();
                    } catch (Exception e) {
                        handleException(e);
                    }
                } else {
                    Logger.log(LogTypes.COLLECTION, "player hasn't this card");
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "You must purchase " + selectedCard + " first.\n Redirect to shop??");
                    if (confirm == JOptionPane.YES_OPTION) {
                        Administer.getInstance().runShop();
                    }

                }

            }

            @Override
            public void setCurrentDeck() {
                try {
                    collectionHandler.setCurrentDeck(selectedDeck);
                    JOptionPane.showMessageDialog(null, selectedDeck + " has been set as current deck"
                            , "Done!", JOptionPane.INFORMATION_MESSAGE);
                    revalidateSelections();
                } catch (Exception e) {
                    handleException(e);
                }
            }

            @Override
            public void changeHero(HeroTypes heroName) {
                try {
                    collectionHandler.changeDeckHero(selectedDeck, heroName);
                    JOptionPane.showMessageDialog(null, selectedDeck + "'s hero has been changed to " + heroName.toString()
                            , "Done!", JOptionPane.INFORMATION_MESSAGE);
                    revalidateSelections();
                } catch (Exception e) {
                    handleException(e);
                }
            }

            @Override
            public void createNewDeck(String newDeckName, String newDeckHero) {
                try {
                    collectionHandler.createNewDeck(newDeckName, newDeckHero);
                    JOptionPane.showMessageDialog(null, newDeckName + " is now available with " + newDeckHero
                            , "Done!", JOptionPane.INFORMATION_MESSAGE);
                    revalidateSelections();
                } catch (Exception e) {
                    handleException(e);
                }
            }

            @Override
            public void cancel() {
                revalidateSelections();
            }

        });
    }

    private void revalidateSelections() {
        filterPanel.setDeckHandlerEditable(false);
        cardPanel.unselectCard();
        deckCardsPanel.unselectCard();
        selectedDeck = null;
        deckCardsPanel.setEmpty();
        deckPanel.setDecks(collectionHandler.getDecks());
    }

    private void handleException(Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        Logger.logError(LogTypes.COLLECTION, e);
    }


}
