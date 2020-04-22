package edu.sharif.student.bluesoheil.ap98.hearthstone.Util.log;

public class Log {

    private LogTypes type;
    private String description;
    //you may need to add the time parameter

    protected Log(LogTypes type , String description){
        this.description = description;
        this.type = type;
    }
}
