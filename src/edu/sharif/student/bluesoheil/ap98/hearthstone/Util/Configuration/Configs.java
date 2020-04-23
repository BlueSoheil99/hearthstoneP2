package edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration;

import java.util.Properties;

public class Configs extends Properties {

    public boolean readBoolean(String field){
        return Boolean.parseBoolean(this.getProperty(field));
    }

    public int readInt(String field){
        return Integer.parseInt(this.getProperty(field));
    }

    public String[] readArrays(String field){
        String str = this.getProperty(field);
        return str.split(",");
    }

}
