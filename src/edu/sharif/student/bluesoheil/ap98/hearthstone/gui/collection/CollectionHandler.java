package edu.sharif.student.bluesoheil.ap98.hearthstone.gui.collection;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Administer;
import edu.sharif.student.bluesoheil.ap98.hearthstone.CardFilter;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.CardShape;

import java.util.ArrayList;

public class CollectionHandler {
    private static CollectionHandler instance;
    private CardFilter cardFilter;

    private CollectionHandler() {
        cardFilter = CardFilter.getInstance();
    }

    public static CollectionHandler getInstance() {
        if (instance != null) instance = new CollectionHandler();
        return instance;
    }

    public ArrayList<CardShape> filterCards(String regex, boolean owned, boolean notOwned, int manaCost, String hero) {
        return cardFilter.filter(regex, owned, notOwned, manaCost, hero);
    }

    public ArrayList<CardShape> getDeckCards(String deckName) {
        ArrayList<String> deckCards = Administer.getInstance().getDeckCards(deckName);
        ArrayList<CardShape> allCards = Administer.getInstance().getAllCards();
        ArrayList<CardShape> cardShapes = new ArrayList<>();

        for (CardShape shape : allCards) {
            if (deckCards.contains(shape.getCardName())) cardShapes.add(shape);
        }
        return cardShapes;
    }
}
