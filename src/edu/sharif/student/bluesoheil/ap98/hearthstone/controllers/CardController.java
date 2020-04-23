package edu.sharif.student.bluesoheil.ap98.hearthstone.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration.Constants;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Cards.Card;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


public class CardController {

    private static CardController instance;
    private ArrayList<Card> gameTotalCards;
    private ArrayList<Card> playerTotalCards;

    private CardController(){
    }

    public static CardController getInstance() {
        if (instance == null){
            instance = new CardController();
        }
        return instance;
    }

    public void createCard(){
//        Card card = new Card();
//        saveCard(card);
    }

    public void setInstance(CardController instance) {
        this.instance = instance;
    }

    public ArrayList<Card> getGameTotalCards() {
        return gameTotalCards;
    }

    public void setGameTotalCards(ArrayList<Card> gameTotalCards) {
        this.gameTotalCards = gameTotalCards;
    }

    public ArrayList<Card> getPlayerTotalCards() {
        return playerTotalCards;
    }

    public void setPlayerTotalCards(ArrayList<Card> playerTotalCards) {
        this.playerTotalCards = playerTotalCards;
    }

    private void saveCard(Card card) {
        try {
            FileWriter writer = new FileWriter(Constants.getCardsPath()+"/" + card.getName() + ".json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            PrintWriter printer = new PrintWriter(writer);
            printer.println(gson.toJson(card));
            printer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


// todo make a new psvm as cardCreator and use cardPanel and this CardController.createCard() method
//class CardCreator{
//    create(all information about the card){
//      CardPanel.createCard(...);
//      CardController.createCard(...);
//    }
//    public static void main(String[] args) {
//        create(...)
//    }
//}
