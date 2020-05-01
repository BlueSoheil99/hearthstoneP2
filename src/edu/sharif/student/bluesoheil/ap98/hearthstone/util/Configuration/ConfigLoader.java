package edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class ConfigLoader {
    //todo delete additional fields

    private static ConfigLoader loader;

    private Configs addresses;
    private static String defaultAddress = "src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Properties/MainConfigFile.properties";
    private Configs constants;
    private Configs frameConfigs;
    private Configs panelConfigs;
    private Configs startPanelConfigs;
    private Configs collectionConfigs;
    private Configs cardConfigs;
    private Configs menuConfigs;
    private Configs guiConstants;
    private Configs properties;

    private ConfigLoader(String configsSourceAddress) {
        initialize(configsSourceAddress);
    }

    public static ConfigLoader getInstance() {
        if (loader == null) {
            loader = new ConfigLoader(defaultAddress);
        }
        return loader;
    }

    private void initialize(String address) {
        FileReader reader;
        constants = new Configs();
        frameConfigs = new Configs();
        panelConfigs = new Configs();
        try {
            addresses = new Configs();
            reader = new FileReader(address);
            addresses.load(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("main config file doesn't exist");
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadProperties();
    }

    private void loadProperties() {

        for (Map.Entry<Object, Object> entry : addresses.entrySet()) { //right??

            String adrs = (String) entry.getValue();
            String key = (String) entry.getKey();
            String lowerCase = key.toLowerCase();
            if (!lowerCase.contains("url")) {
                Configs property = new Configs();
                File test = new File(adrs);
                try {
                    FileReader reader = new FileReader(test);
                    property.load(reader);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (lowerCase.contains("frame")) {
//                    System.out.println("frame added : " + key);
                    frameConfigs = property;

                } else if (lowerCase.contains("startpanel")) {
                    startPanelConfigs = property;

                } else if (lowerCase.contains("card")) {
                    cardConfigs = property;

                } else if (lowerCase.contains("menu")) {
                    menuConfigs = property;

                } else if (lowerCase.contains("panel")) {
                    panelConfigs = property;

                } else if (lowerCase.contains("guiconstants")) {
                    guiConstants = property;

                } else if (lowerCase.contains("collection")) {
                    collectionConfigs = property;

                } else if (lowerCase.contains("constants")) {
                    constants = property;

                } else
                    properties = property;


            }
        }
    }

    // due to subPackages, we couldn't have protected methods here

    public String getAddress(String resource_url) {
        return addresses.getProperty(resource_url);
    }

    public Configs getConstantsProperties() {
        return constants;
    }

    public Configs getFrameProperties() {
        return frameConfigs;
    }

    public Configs getPanelProperties() {
        return panelConfigs;
    }

    public Configs getStartPanelProperties() {
        return startPanelConfigs;
    }

    public Configs getCardProperties() {
        return cardConfigs;
    }

    public Configs getMenuProperties() {
        return menuConfigs;
    }

    public Configs getGuiConstantsProperties() {
        return guiConstants;
    }

    public Configs getCollectionProperties() {
        return collectionConfigs;
    }
}
