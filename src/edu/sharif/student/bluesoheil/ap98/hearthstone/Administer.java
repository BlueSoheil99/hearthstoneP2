package edu.sharif.student.bluesoheil.ap98.hearthstone;

import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.*;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.CollectionPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.CardShape;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.starter.LoginPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.starter.SignUpPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.controllers.*;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Deck;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Heroes.HeroTypes;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Player;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.cards.Card;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.log.LogTypes;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.log.Logger;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Administer {
    private MainFrame mainFrame;
    private static Administer administer;
    private ArrayList<JPanel> recentPanels;
    private PlayerController playerController;
    private CardController cardController;
    private DeckController deckController;

    private Administer() {
        mainFrame = new MainFrame();
        administer = this;
        recentPanels = new ArrayList<>();
        playerController = PlayerController.getInstance();
        cardController = CardController.getInstance();
        deckController = DeckController.getInstance();
    }

    public static Administer getInstance() {
        if (administer == null) {
            administer = new Administer();
        }
        return administer;
    }

    public Player getCurrentPlayer() {
        return playerController.getCurrentPlayer();
    }

    /*
     **
     ***
     * GUI Managing
     ***
     **
     */

    public void runLogin() {
        LoginPanel loginPanel = new LoginPanel();
        mainFrame.initFrame(loginPanel);
    }

    public void runSignUp() {
        SignUpPanel signUpPanel = new SignUpPanel();
        mainFrame.initFrame(signUpPanel);
    }

    public void runMenu() {
        MenuPanel menuPanel = new MenuPanel();
        recentPanels.add(menuPanel);
        mainFrame.initFrame(menuPanel);
    }

    public void runPlay() {
        if (DeckController.getInstance().getCurrentDeck() == null) {
            //when current deck is not selected
        }
        //todo think about that where shall we put passives, here or in playPanel
        GameController game = new GameController();
        PlayPanel playPanel = new PlayPanel();
        recentPanels.add(playPanel);
        Logger.log(LogTypes.NAVIGATION, "To Play");
        mainFrame.initFrame(playPanel);
    }

    public void runShop() {
        ShopPanel shopPanel = new ShopPanel();
        recentPanels.add(shopPanel);
        Logger.log(LogTypes.NAVIGATION, "To Shop");
        mainFrame.initFrame(shopPanel);
    }

    public void runStatus() {
        StatusPanel statusPanel = new StatusPanel();
        recentPanels.add(statusPanel);
        Logger.log(LogTypes.NAVIGATION, "To Status");
        mainFrame.initFrame(statusPanel);
    }

    public void runCollection() {
        CollectionPanel collectionPanel = new CollectionPanel();
        recentPanels.add(collectionPanel);
        Logger.log(LogTypes.NAVIGATION, "To Collection");
        mainFrame.initFrame(collectionPanel);
    }

    public void runSetting() {
        SettingPanel settingPanel = new SettingPanel();
        recentPanels.add(settingPanel);
        Logger.log(LogTypes.NAVIGATION, "To Setting");
        mainFrame.initFrame(settingPanel);
    }

    public void runExit() {
        //i've put confirmDialog so I didn't duplicate code in exit button class and exit button in menuPanel
        int ans = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Exit?",
                "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (ans == JOptionPane.YES_OPTION) {
            playerController.logOut();
            System.exit(0);
        }
    }

    public void back() {
        Logger.log(LogTypes.NAVIGATION, "Back to previous panel");
        mainFrame.initFrame(getPreviousPanel());
    }

    public JPanel getPreviousPanel() {
        JPanel previousPanel = null;
        recentPanels.remove(recentPanels.size() - 1); //i don't know how to explain! it's like closing the last panel...
        // ... if we don't do this we will have logical errors.
        previousPanel = recentPanels.get(recentPanels.size() - 1);
        return previousPanel;
    }

    public void runLogout() {
        playerController.logOut();
        Main.main(null);
    }

    public void deletePlayer(String password) throws PlayerControllerException {
        playerController.deletePlayer(password);
        JOptionPane.showMessageDialog(null , "You deleted your profile successfully");
        System.exit(0);
    }

    /*
     **
     ****
     * Logic Managing
     ****
     **
     */

    public void signUp(String username, String password) throws PlayerControllerException {
        playerController.signUp(username, password);
        //maybe you'd better create log file here , not in the controller
        // but i'm won't do it because i can't refer to the new player in  Logger.createLogFile(player); method.
    }

    public void login(String username, String password) throws PlayerControllerException {
        playerController.login(username, password);
        //if the method above doesn't throw exception :

//        cardController.initGameTotalCards();
//        cardController.loadPlayerCards();
//        deckController.loadPlayerDecks();
        Logger.initLogger(PlayerController.getInstance().getCurrentPlayer());
        Logger.log(LogTypes.PLAYER, PlayerController.getInstance().getCurrentPlayer().getUserName() + " logged in");

    }

    public ArrayList<CardShape> getAllCards() {
        ArrayList<CardShape> cardShapes = new ArrayList<>();
        for (Map.Entry<String, BufferedImage> entry : cardController.getCardsAndImagesMap().entrySet()) {
            cardShapes.add(new CardShape(entry.getKey(), entry.getValue() , true));
        }
        return cardShapes;
    }

    public ArrayList<String> getDeckCards(String deckName){
        ArrayList<Card> cards = deckController.getDeck(deckName).getCards();
        ArrayList<String> cardNames = new ArrayList<>();
        for (Card card : cards) cardNames.add(card.getName().toUpperCase());
        return cardNames;
    }

    public int getPlayerCoins() {
        return playerController.getPlayerCoins();
    }

    public int getCardCost(String selectedCard) {
        return cardController.getCardCost(selectedCard);
    }

    public void buyCard(String cardName) throws CardControllerException {
        cardController.buyCard(cardName);
    }

    public void sellCard(String cardName) throws CardControllerException {
        cardController.sellCard(cardName);
    }

    public void createDeck(String deckName) {

    }

    public HashMap<String, String> getPlayerDecks() {
        // returns all decks of player
        return getPlayerDecks(deckController.getPlayerDecks().size());
    }

    public HashMap<String, String> getPlayerDecks(int numberOfDecks) {
        HashMap<String, String> decksInfo = new HashMap<>();
        int x = 0;
        for (Deck deck : deckController.getPlayerDecks()) {
            if (x < numberOfDecks) {
                decksInfo.put(deck.getName(), deck.getHero().toString());
            }
        }
        return decksInfo;
    }

    public String[] getDeckState(String deckName) {
        return deckController.getDeckStates(deckName);
    }

}
