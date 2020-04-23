package edu.sharif.student.bluesoheil.ap98.hearthstone.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Administer;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration.Constants;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Util.log.LogTypes;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Util.log.Logger;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Player;

import java.io.*;

public class PlayerController {
    private static PlayerController playerController;
    private static String profilesPath = Constants.getProfilesPath();
    private Player currentPlayer;

    private PlayerController(){
    }

    public static PlayerController getInstance(){
        if (playerController != null) return playerController;
        playerController = new PlayerController();
        return playerController;
    }

    // getters and setters
    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    // methods
    public void signUp(String username , String password) throws Exception {
        if (new File(profilesPath +"/" + username + ".json").exists()) {
            throw new Exception("USERNAME IS INVALID");
        } else {
            Player player = new Player(username, password);
            saveDataForTheFirstTime(player);
            Logger.createLogFile(player);
        }
    }

    public void login(String username , String password) throws Exception{
        if (allPlayersContain(username, password)) {
//                    CardManagement.initGameTotalCards(); //we'd better use this method at the beginning of whole RUN
            setCurrentPlayer(getPlayer(username));
            //todo load the carts here or in setCurrentPlayer
        } else {
            throw new  Exception("username or password is incorrect");
        }
    }

    public void deletePlayer(String password){
        if(password.equals(currentPlayer.getPassword())){
            Logger.finalizeLogfile(currentPlayer);
            // todo deletePlayer
        }
    }

    public void logOut(){
        //todo logOut
        saveData();
        Logger.log(LogTypes.PLAYER ,currentPlayer.getUserName()+" logged out" );
        Logger.closeLogfile();
        Administer.getInstance().runLogin();

    }

    private boolean allPlayersContain(String username , String password) {
        boolean ans = false;

        try {
            JsonParser jsonParser = new JsonParser();

            FileReader reader = new FileReader(Constants.getProfilesPath()+"/"+ username + ".json");
            // if this file doesn't exist, we handle the exception with no message and we print an error in signUP panel instead
            // , because this method is designed to give us true or false and exception is not expected from allPlayersContain
            JsonObject json = (JsonObject) jsonParser.parse(reader);
            reader.close();

            String playerRealPassword = json.get("password").toString();   //it gives us a string with additional quotation marks
            playerRealPassword = playerRealPassword.substring(1, playerRealPassword.length() - 1);  //we remove extra quotation marks
            if (playerRealPassword.equals(password)) {
                ans = true;
            }
            // if passwords don't match, we handle the exception with no message and we print an error in CLIRunner instead
            // , because this method is designed to give us true or false and "exception" is not expected from allPlayersContain

        }catch(FileNotFoundException e){       } //we display a message instead in CLI_Runner
        catch (IOException e) {
            e.printStackTrace();
        }

        return ans;
    }

    private void setCurrentPlayer(Player player) {
        currentPlayer = player;
//        CardManagement.loadPlayerTotalCards();
//        CardManagement.loadPlayerHeroesAllDeckCards();
//        try {
//            CardManagement.setCurrentHero(player.getCurrentHero());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try {
//            CardManagement.setCurrentHero(player.getCurrentHero());
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        Logger.initLogger(player);
        Logger.log(LogTypes.PLAYER , currentPlayer.getUserName()+" logged in");

    }

    private Player getPlayer(String username){
        Player player =null ;
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        try {
            FileReader reader = new FileReader(profilesPath+ "/" + username+".json");
            player = gson.fromJson(jsonParser.parse(reader ), Player.class); //here we make a json element and then turn it into a Player object
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return player;
    }

    private void saveDataForTheFirstTime(Player player){
        try{
            FileWriter writer = new FileWriter(player.getProfilePath());
            PrintWriter printer = new PrintWriter(writer);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(player);
            printer.println( json );
            printer.close();
        }catch (IOException e ){
            System.out.println(e.getMessage());
        }
    }

    public void saveData(){
//        setPlayerTotalCards(CardManagement.getPlayerTotalCards() );
//        setHeroesAllDeckCards(CardManagement.getHeroesAllDeckCards() );
//        saveDataForTheFirstTime();
    }


}
