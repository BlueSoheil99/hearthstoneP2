package edu.sharif.student.bluesoheil.ap98.hearthstone;

import edu.sharif.student.bluesoheil.ap98.hearthstone.controllers.*;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.starter.LoginPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.starter.SignUpPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Deck;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.cards.Card;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.log.LogTypes;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.log.Logger;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.*;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

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
        //todo save everything, ask for confirm,
        Logger.log(LogTypes.PLAYER, "logged out");
        Logger.closeLogfile();
        System.exit(0); //or runLogin?

    }

    public void back() {
        Logger.log(LogTypes.CLICK_BUTTON, "Back selected");
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
        Administer.getInstance().runLogin();

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

    public ArrayList<Card> filterCards() {
        // CardController.getInsatnce().filterWithSpecificProperties :)
        return null;

    }

    public void buyCard(String cardName) throws CardControllerException {
        cardController.buyCard(cardName);
    }

    public void sellCard(String cardName) throws CardControllerException {
        cardController.sellCard(cardName);
    }

    public void createDeck(String deckName) {

    }

    public HashMap<String,String> getPlayerDecks(){
        ArrayList<Deck> decks =deckController.getPlayerDecks();
        HashMap<String,String> decksInfo = new HashMap<>();
        for (Deck deck : decks ) {
            decksInfo.put(deck.getName() , deck.getHero().toString());
        }
        return decksInfo;
    }


}
