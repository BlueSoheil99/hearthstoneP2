package edu.sharif.student.bluesoheil.ap98.hearthstone.controllers;

import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Heroes.HeroTypes;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.cards.Card;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Deck;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.LogicConfigs.CardConfig;

import java.util.ArrayList;

public class DeckController {
    private static final int MAXIMUM_NUMBER_OF_DECKS = 25; //i'm not sure whether it's important or not!

    private static DeckController instance;
    private ArrayList<Deck> playerDecks;
    private Deck currentDeck;

    private DeckController() {

    }

    /*
    ***** Getters and Setters
     */
    public static DeckController getInstance() {
        if (instance == null) instance = new DeckController();
        return instance;
    }

    public static ArrayList<Deck> getDefaultDecks() {
        CardConfig configs = CardConfig.getInstance();
        ArrayList<Deck> defaultDecks = new ArrayList<>();

        String[] mageCards = configs.getDefaultMageDeckCards();
        String[] warlockCards = configs.getDefaultWarlockDeckCards();
        String[] rogueCards = configs.getDefaultRogueDeckCards();
        String[] priestCards = configs.getDefaultPriestDeckCards();
        String[] hunterCards = configs.getDefaultHunterDeckCards();

        ArrayList<Card> mageDeckCards = new ArrayList<>();
        ArrayList<Card> warlockDeckCards= new ArrayList<>();
        ArrayList<Card> rogueDeckCards= new ArrayList<>();
        ArrayList<Card> priestDeckCards = new ArrayList<>();
        ArrayList<Card> hunterDeckCards = new ArrayList<>();
//todo howMany decks we need for signUp??
        for (String cardName: mageCards ) mageDeckCards.add(CardController.getInstance().getCard(cardName));
        for (String cardName: warlockCards ) warlockDeckCards.add(CardController.getInstance().getCard(cardName));
        for (String cardName: rogueCards ) rogueDeckCards.add(CardController.getInstance().getCard(cardName));
        for (String cardName: priestCards ) priestDeckCards.add(CardController.getInstance().getCard(cardName));
        for (String cardName: hunterCards ) hunterDeckCards.add(CardController.getInstance().getCard(cardName));

        defaultDecks.add(new Deck("MageDefaultDeck", HeroTypes.MAGE, mageDeckCards));
        defaultDecks.add(new Deck("WarlockDefaultDeck", HeroTypes.WARLOCK, warlockDeckCards));
        defaultDecks.add(new Deck("RogueDefaultDeck", HeroTypes.ROGUE, rogueDeckCards));
        defaultDecks.add(new Deck("PriesDefaultDeck", HeroTypes.PRIEST, priestDeckCards));
        defaultDecks.add(new Deck("HunterDefaultDeck", HeroTypes.HUNTER, hunterDeckCards));

        return defaultDecks;
    }

    public ArrayList<Deck> getPlayerDecks() {
        return playerDecks;
    }
    public Deck getCurrentDeck() {
        return currentDeck;
    }
    public void setCurrentDeck(Deck currentDeck) { //or do it with deckName given
        this.currentDeck = currentDeck;
    }

    /*
     ***** Methods
     */
    public void loadPlayerDecks() {
        playerDecks = PlayerController.getInstance().getPlayerDecks();
        //revalidate decks
        //we should revalidate our cards. current cards are different from the cards in the gameTotalCards and playerTotalCards
        for (Deck deck : playerDecks){
            CardController.getInstance().revalidateCards(deck.getCards());
        }
    }

    public ArrayList<String> getDeckNamesWithCertainCard(Card card) {
        ArrayList<String> deckNamesIncludingThisCard = new ArrayList<>();
        for (Deck deck : playerDecks) {
            if (deckContains(deck, card)) {
                deckNamesIncludingThisCard.add(deck.getName());
            }
        }

        if (deckNamesIncludingThisCard.size() > 0) {
            return deckNamesIncludingThisCard;
        } else return null;

    }

    private boolean deckContains(Deck deck, Card card) {
        return deck.getCards().contains(card);
    }

    public void createDeck(String name , HeroTypes hero) throws DeckControllerException {  //what should be the given cards? we can have default cards for each hero
        if (playerDecks.size() < MAXIMUM_NUMBER_OF_DECKS){
            playerDecks.add(new Deck(name,hero,  new ArrayList<>()));
        }else  throw new DeckControllerException(" You can't hava more decks! ");
    }

    public void addCard(String nameOfDeck, Card card) throws DeckControllerException {
        for(Deck deck : playerDecks){
            if (deck.getName().equals(nameOfDeck)) {
                deck.addCard(card);
                break;
            }
        }
    }

    public void removeCard(String nameOfDeck, Card card) throws DeckControllerException {
        for(Deck deck : playerDecks){
            if (deck.getName().equals(nameOfDeck)) {
                deck.removeCard(card);
                break;
            }
        }
    }


}
