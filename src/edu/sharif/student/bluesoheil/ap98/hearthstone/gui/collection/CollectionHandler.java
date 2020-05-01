package edu.sharif.student.bluesoheil.ap98.hearthstone.gui.collection;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Administer;
import edu.sharif.student.bluesoheil.ap98.hearthstone.CardFilter;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.CardShape;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Heroes.HeroTypes;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectionHandler {
    private static CollectionHandler instance = new CollectionHandler();
    private CardFilter cardFilter;
    private Administer administer;

    private CollectionHandler() {
        cardFilter = CardFilter.getInstance();
        administer = Administer.getInstance();
    }

    public static CollectionHandler getInstance() {
        return instance;
    }

    public ArrayList<CardShape> filterCards(String regex, boolean owned, boolean notOwned, int manaCost, String hero) {
        return cardFilter.filter(regex, owned, notOwned, manaCost, hero);
    }

    public ArrayList<CardShape> getDeckCards(String deckName) {
        ArrayList<String> deckCards = administer.getDeckCards(deckName);
        ArrayList<CardShape> allCards = administer.getAllCards();
        ArrayList<CardShape> cardShapes = new ArrayList<>();

        for (CardShape shape : allCards) {
            if (deckCards.contains(shape.getCardName())) cardShapes.add(shape);
        }
        return cardShapes;
    }

    public HashMap<String, String> getDecks() {
        return administer.getPlayerDecks();
    }

    public void renameDeck(String selectedDeck, String newName) {
//        administer.renameDeck
    }

    public void deleteDeck(String selectedDeck) {
    }

    public void removeCardFromDeck(String selectedDeck, String selectedCard) {
    }

    public void setCurrentDeck(String selectedDeck) {
    }

    public void addCardToDeck(String selectedDeck , String selectedCard) {
    }

    public void changeDeckHero(String selectedDeck, HeroTypes heroName) {
    }

    public void createNewDeck(String newDeckName , String heroName) {
    }
}
