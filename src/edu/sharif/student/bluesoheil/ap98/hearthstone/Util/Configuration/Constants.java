package edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration;


public class Constants {


    // todo make configurations
    private static Constants constants;
    private static Configs properties;

    public static String profilesPath = "src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Profiles";
    private static String logsPath = "src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Logs" ;
    private static String cardsPath = "src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Cards";

    private Constants(){
        properties = ConfigLoader.getInstance().getConstantsProperties();
    }

    public static Constants getInstance(){
        if (constants == null){
            constants = new Constants();
        }
        return constants;
    }


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
