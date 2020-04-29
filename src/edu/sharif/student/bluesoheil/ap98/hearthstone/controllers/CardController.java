package edu.sharif.student.bluesoheil.ap98.hearthstone.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.cards.*;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.ConfigLoader;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.Configs;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.Constants;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.LogicConfigs.CardConfig;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


public class CardController {

    private static CardController instance;
    private static String cardsPath = Constants.getCardsPath();
    private static String cardsImagesPath = Constants.getCardsImagesPath();

    private PlayerController playerController;
    private CardConfig properties;

    private HashMap<String, Card> gameTotalCards;
    private HashMap<String, BufferedImage> cardsAndImagesMap;
    private ArrayList<Card> playerTotalCards;

    private CardController() {
        playerController = PlayerController.getInstance();
        properties = CardConfig.getInstance();
        initGameTotalCards();
    }

    /*
    ******* Getters and Setters ... some of them are useless and must be deleted
     */
    public static CardController getInstance() {
        if (instance == null) {
            instance = new CardController();
        }
        return instance;
    }

    public HashMap<String, Card> getGameTotalCards() {
        return gameTotalCards;
    }

    public HashMap<String, BufferedImage> getCardsAndImagesMap() {
        return cardsAndImagesMap;
    }

    public void setGameTotalCards(HashMap<String, Card> gameTotalCards) {
        this.gameTotalCards = gameTotalCards;
    }

    public ArrayList<Card> getPlayerTotalCards() {
        return playerTotalCards;
    }

    public void setPlayerTotalCards(ArrayList<Card> playerTotalCards) {
        this.playerTotalCards = playerTotalCards;
    }

    /*
    ****** Methods
     */
    public void initGameTotalCards() {
        Card card;
        gameTotalCards = new HashMap<String, Card>();
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        List spells = Arrays.asList(properties.getSpellCards());
        List minions =Arrays.asList( properties.getMinionCards());
        List beasts =Arrays.asList( properties.getBeastCards());
        List weapons = Arrays.asList(properties.getWeaponCards());
        List questAndRewards = Arrays.asList(properties.getQuestAndRewardCards());
        String[] allCardNames = new File(cardsPath).list();
        try {
            FileReader reader;
            for (String cardName : allCardNames) {
                cardName = cardName.substring(0, cardName.length() - 5); //because of '.json' thing we have '-5'
                reader = new FileReader(cardsPath +"/"+ cardName + ".json");

                if (spells.contains(cardName)){
                    card = gson.fromJson(jsonParser.parse(reader), Spell.class); //here we make a jsonElement and then turn it into a Card object

                } else if (weapons.contains(cardName)) {
                    card = gson.fromJson(jsonParser.parse(reader), Weapon.class);

                }else if (minions.contains(cardName)){
                    card = gson.fromJson(jsonParser.parse(reader), Minion.class); //here we make a jsonElement and then turn it into a Card object

                }else if (beasts.contains(cardName)){
                    card = gson.fromJson(jsonParser.parse(reader), Beast.class); //here we make a jsonElement and then turn it into a Card object

                }else if (questAndRewards.contains(cardName)){
                    card = gson.fromJson(jsonParser.parse(reader), QuestAndReward.class); //here we make a jsonElement and then turn it into a Card object

                }else  card = gson.fromJson(jsonParser.parse(reader), Card.class); //here we make a jsonElement and then turn it into a Card object

                gameTotalCards.put(card.getName().toUpperCase(), card);
                reader.close();
            }

            loadCardsAndImagesMap();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCardsAndImagesMap() {
        cardsAndImagesMap = new HashMap<>();
        String path;
        for (String cardName : gameTotalCards.keySet()) {
            path = cardsImagesPath +"/"+ cardName.toLowerCase()+".png";
            BufferedImage img = ImageLoader.loadImage(path);
            cardsAndImagesMap.put(cardName, img);
        }
    }

    public  ArrayList<Card> getDefaultCards(){ //we can't make it static because we need to have gameTotalCards initialized
        String[] defaultPlayerTotalCards = CardConfig.getInstance().getDefaultCards();
        ArrayList<Card> defaultCards = new ArrayList<>();

        for (String cardName : defaultPlayerTotalCards ) {
            cardName = cardName.toUpperCase();
            if (gameTotalCards.containsKey(cardName)){
                defaultCards.add(gameTotalCards.get(cardName));
            }
        }
        return defaultCards;
    }

    public void  loadPlayerCards(){
        playerTotalCards = playerController.getCurrentPlayer().getPlayerTotalCards();
        //we should revalidate our cards. current cards are different from the cards in the gameTotalCards
        revalidateCards(playerTotalCards);
    }

    void revalidateCards(  ArrayList<Card> givenCards ){
        for (Card card : givenCards){
            givenCards.set(givenCards.indexOf(card) , gameTotalCards.get(card.getName().toUpperCase()));
        }
    }

    public Card getCard(String cardName){
        return gameTotalCards.get(cardName.toUpperCase());
    }

    public boolean playerCardsContain(String cardName){
        //maybe we should run this method with Card.class
        if (playerTotalCards.contains(getCard(cardName))) return true;
        return false;
    }

    public void buyCard(String cardName) throws CardControllerException {
        cardName = cardName.toUpperCase();
        if (gameTotalCards.containsKey(cardName)) {
            Card card = gameTotalCards.get(cardName);
            int playerCoins = playerController.getPlayerCoins();
            if (playerCoins >= card.getCost()) {
                if (Collections.frequency(playerTotalCards, card) < 2) {
                    playerTotalCards.add(card);

                    playerController.setPlayerCoins(playerCoins - card.getCost());
                    playerController.setPlayerCards(playerTotalCards);//check if it's useful or not

                } else throw new CardControllerException("you can't have more than 2 cards of " + cardName);
            } else throw new CardControllerException("not enough money to buy : " + cardName);
        } else throw new CardControllerException("card( " + cardName + " ) doesn't exist");

    }

    public void sellCard(String cardName) throws CardControllerException {
        cardName=cardName.toUpperCase();
        if( gameTotalCards.containsKey(cardName) ) {
            Card card = gameTotalCards.get(cardName);

            if (playerTotalCards.contains(card)){
                ArrayList<String> decksWithThisCard = DeckController.getInstance().getDeckNamesWithCertainCard(card);
                if (decksWithThisCard == null){
                    int playerCoins = playerController.getPlayerCoins();
                    playerTotalCards.remove(card);

                    playerController.setPlayerCoins( playerCoins + card.getCost() );
                    playerController.setPlayerCards(playerTotalCards);//check if it's useful or not
                }else throw new CardControllerException("unsalable card: "+cardName+
                        " is in these decks:\t"+decksWithThisCard.toString());
            }else throw new CardControllerException("players hasn't "+cardName+" card");
        }else throw new CardControllerException("card( "+cardName + " ) doesn't exist");
    }


    public int getCardCost(String selectedCard) {
        int cost = 0;
        Card card = gameTotalCards.get(selectedCard.toUpperCase());
        if (card != null) cost = card.getCost();
        return cost;
    }
}
