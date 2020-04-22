package edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration;

import java.util.Properties;

public class Configs extends Properties {

    boolean readBoolean(String field){
        return Boolean.parseBoolean(this.getProperty(field));
    }

    int readInt(String field){
        return Integer.parseInt(this.getProperty(field));
    }

}
