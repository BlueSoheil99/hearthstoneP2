package edu.sharif.student.bluesoheil.ap98.hearthstone.Util;

public class Constants {
    // todo make configurations
    public static String profilesPath = "src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Profiles";
    private static String logsPath = "src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Logs" ;
    private static String cardsPath = "src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Cards";

    private Constants(){}

    public static String getProfilesPath() {
        return profilesPath;
    }
    public static String getLogsPath(){
        return logsPath;
    }
    public static String getCardsPath(){
        return cardsPath;
    }

}
