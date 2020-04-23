package edu.sharif.student.bluesoheil.ap98.hearthstone.models;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration.Constants;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration.LogicConfigs.PlayerConfig;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Player {
    private String   userName;
    private String   password; //or make a inner class named password
    private int userId;
    private int coins =50;
    private String profilePath;
    private String logPath;
    private String currentHero;
    private ArrayList<String> playerTotalCards = new ArrayList<>();
    // todo make a list of decks(like: arraylist<> decks = {deck1,deck2, ...} and each deck must contain name,cards,states of deck})
    private HashMap<String , ArrayList<String>> heroesAllDeckCards;


    //todo next 20 lines must be in configuration
    private static String[] defaultPlayerTotalCards = PlayerConfig.getInstance().getDefaultCards();
//            = { "Super Collider"  , "Friendly Smith" , "Polymorph" ,
//            "Humility" , "Innervate" , "Omega Medic" , "Waterboy" , "Voodoo doctor" , "Slam" ,"Dreadscale"
//            , "Abomination" , "Murloc Tidehunter"  };
    private static HashMap<String , ArrayList<String>>  defaultDeckCards = new HashMap<String , ArrayList<String>>() ;
    {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList( "Murloc Tidehunter","Super Collider","Abomination","Humility", "Innervate", "Omega Medic", "Waterboy", "Voodoo doctor", "Slam"));
        defaultDeckCards.put("Neutral", list);
        list = new ArrayList<>();
        list.addAll(Arrays.asList("Polymorph"));
        defaultDeckCards.put("Mage", list);
        list = new ArrayList<>();
        list.addAll(Collections.singletonList("Dreadscale"));
        defaultDeckCards.put("Warlock", list);
        list = new ArrayList<>();
        list.addAll(Collections.singletonList("Friendly Smith"));
        defaultDeckCards.put("Rogue", list);
    }


    public Player(String newUser , String newPass ) {
        //todo use configurations here
        userId = new File(Constants.getProfilesPath()).list().length + 1 ;
        this.userName = newUser;
        this.password = newPass ;  // todo try to make password as a hashcode( by an inner class or a method)
        currentHero = "Mage"; // todo check whether it must be initialized as null or not

        playerTotalCards.addAll(Arrays.asList(defaultPlayerTotalCards));
        heroesAllDeckCards = defaultDeckCards;
        profilePath = Constants.getProfilesPath()+"/"+ this.getUserName()+".json";
        logPath     = Constants.getLogsPath()+"/"+ this.getUserName()+"-"+this.getUserId()+".log";

    }

    //todo delete useless methods
    private int getUserId(){
        return userId;
    }

    public String getUsername(){
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getLogPath(){
        return logPath;
    }

    public String getProfilePath(){
        return profilePath;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getCurrentHero() {
        return currentHero;
    }

    public void setCurrentHero(String currentHero) {
        this.currentHero = currentHero;
    }

    public ArrayList<String> getPlayerTotalCards() {
        return playerTotalCards;
    }

    public void setPlayerTotalCards(ArrayList<String> playerTotalCards) {
        this.playerTotalCards = playerTotalCards;
    }

    public HashMap<String, ArrayList<String>> getHeroesAllDeckCards() {
        return heroesAllDeckCards;
    }

    public void setHeroesAllDeckCards(HashMap<String, ArrayList<String>> heroesAllDeckCards) {
        this.heroesAllDeckCards = heroesAllDeckCards;
    }

    public static String[] getDefaultPlayerTotalCards() {
        return defaultPlayerTotalCards;
    }

    public static void setDefaultPlayerTotalCards(String[] defaultPlayerTotalCards) {
        Player.defaultPlayerTotalCards = defaultPlayerTotalCards;
    }

    public static HashMap<String, ArrayList<String>> getDefaultDeckCards() {
        return defaultDeckCards;
    }

    public static void setDefaultDeckCards(HashMap<String, ArrayList<String>> defaultDeckCards) {
        Player.defaultDeckCards = defaultDeckCards;
    }

}

