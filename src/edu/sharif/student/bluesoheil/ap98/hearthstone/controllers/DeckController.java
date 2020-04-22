package edu.sharif.student.bluesoheil.ap98.hearthstone.controllers;

import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Cards.Card;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Deck;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Player;

import java.util.ArrayList;

public class DeckController {
    private static DeckController instance;
    private ArrayList<Deck> playerDecks;
    private Deck currentDeck;
    private Player currentPlayer;

    private DeckController(Player player){
        //todo load all decks of current player
        setCurrentPlayer(player);
        loadPlayerDecks();
    }

    public  static DeckController getInstance(){
        if (instance == null || PlayerController.getInstance().getCurrentPlayer() != instance.getCurrentPlayer() ) { // or use .equals() ?
            instance = new DeckController(PlayerController.getInstance().getCurrentPlayer());
        }
        return instance;
    }


    public ArrayList<Deck> getPlayerDecks() {
        return playerDecks;
    }

    public void setPlayerDecks(ArrayList<Deck> playerDecks) {
        this.playerDecks = playerDecks;
    }

    public Deck getCurrentDeck() {
        return currentDeck;
    }

    public void setCurrentDeck(Deck currentDeck) {
        this.currentDeck = currentDeck;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private void loadPlayerDecks(){

    }

    public void createDeck(String name){
        playerDecks.add(new Deck(name));
    }

    public void addCard(String nameOfDeck , Card card){

    }

    public void removeCard(String nameOfDeck , Card card){

    }

}
