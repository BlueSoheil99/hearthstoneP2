package edu.sharif.student.bluesoheil.ap98.hearthstone.connectors;

import edu.sharif.student.bluesoheil.ap98.hearthstone.util.log.Logger;

import java.util.ArrayList;

public class PlayHandler {
    private Administer administer;
    private static PlayHandler instance;
//    private

    public PlayHandler(){

    }

    public static PlayHandler getInstance(){
        if (instance == null) instance = new PlayHandler();
        return instance;
    }

    public ArrayList<String> getEvents(){
        return Logger.getEventLogs();
    }
}
