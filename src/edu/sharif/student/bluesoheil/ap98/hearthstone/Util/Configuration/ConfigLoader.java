package edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class ConfigLoader {
    //todo delete additional fields

    private static ConfigLoader loader;
//    private String addressName;
//    private HashMap<String, Configs> addressess;
    private Configs addresses;
    private static String defaultAddress = "src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Properties/MainConfigFile.properties";
    private Configs constants;
    private Configs frameConfigs;
    private Configs panelConfigs;
    private Configs startPanelConfigs;
    private Configs playerConfigs;
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
//        addressName = "RESOURCE_URL";
        constants = new Configs();
        frameConfigs = new Configs();
        panelConfigs = new Configs();
//        addressess = new HashMap<>();
        try {
            addresses = new Configs();
            reader = new FileReader(address);
            addresses.load(reader);
//            this.addressess.put(addressName, addresses);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("main config file doesn't exist");
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadProperties();
    }

    private void loadProperties() {
//        Set<Map.Entry<Object, Object>> entries = addressess.get("RESOURCE_URL").entrySet();
//        for (Map.Entry entry : entries) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }

        for (Map.Entry<Object, Object> entry : addresses.entrySet()) { //right??

            String adrs = (String) entry.getValue();
            String key = (String) entry.getKey();
            String lowerCase = key.toLowerCase();
            if (!lowerCase.contains("url")) {
                Configs property = new Configs();
                File test = new File(adrs);
                try {
                    FileReader reader = new FileReader(test);
//                    System.out.println(test.getAbsolutePath());
                    property.load(reader);

                } catch (FileNotFoundException e) {
//                    System.out.println(entry.getKey() + " file doesn't exist");
                    e.printStackTrace();
                } catch (IOException e) {
//                    System.out.println(entry.getKey() + " load failed");
                    e.printStackTrace();
                }

                if (lowerCase.contains("frame")) {
//                    System.out.println("frame added : " + key);
                    frameConfigs = property;

                } else if (lowerCase.contains("startpanel")) {
                    startPanelConfigs = property;

                } else if (lowerCase.contains("player")) {
                    playerConfigs = property;

                }else if (lowerCase.contains("menu")){
                    menuConfigs = property;

                }else if (lowerCase.contains("panel")) {
//                    System.out.println("panels added : " + key);
                    panelConfigs = property;
                }else if (lowerCase.contains("guiconstants")) {
                    guiConstants = property;

                }else if (lowerCase.contains("constants")) {
//                    System.out.println("constants added : " + key);
                    constants = property;

                } else
                    properties = property ;


            }
        }
//        System.out.println("loading finished! ");
    }

    // due to subPackages we couldn't have protected methods here

    public String getAddress( String resource_url) {
//        return addressess.get(type).getProperty(resource_url);
        return addresses.getProperty(resource_url);
    }

    public Configs getConstantsProperties() {
//        System.out.println("in get properties with name : " + name);
//        return constants.get(name);
        return constants;
    }

    public Configs getFrameProperties() {
//        return frameConfigs.get(name);
        return frameConfigs;
    }

    public Configs getPanelProperties() {
        return panelConfigs;
    }

    public Configs getStartPanelProperties() {
        return startPanelConfigs;
    }

    public Configs getPlayerProperties(){
        return playerConfigs;
    }

    public Configs getMenuProperties(){
        return menuConfigs;
    }

    public Configs getGuiConstantsProperties(){
        return guiConstants;
    }
}
