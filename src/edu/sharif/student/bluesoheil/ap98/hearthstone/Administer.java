package edu.sharif.student.bluesoheil.ap98.hearthstone;

import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.LoginPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.MainFrame;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.MenuPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.SignUpPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.controllers.PlayerController;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Player;

import javax.swing.*;
import java.util.ArrayList;

public class Administer {
    private MainFrame mainFrame;
    private static Administer administer;
    private ArrayList<JPanel> recentPanels;
    private PlayerController playerController;

    private Administer() {
        mainFrame = new MainFrame();
        administer = this;
        recentPanels = new ArrayList<>();
        playerController = PlayerController.getInstance();
    }

    public static Administer getInstance() {
        if (administer == null) {
            administer = new Administer();
        }
        return administer;
    }


    public void runLogin() {
        LoginPanel loginPanel = new LoginPanel();
        mainFrame.initFrame(loginPanel);
    }

    public void runSignUp(){
        SignUpPanel signUpPanel = new SignUpPanel();
        mainFrame.initFrame(signUpPanel);
    }

    public void runMenu(){
        MenuPanel menuPanel = new MenuPanel();
        mainFrame.initFrame(menuPanel);
    }

    public void back(){
        mainFrame.initFrame(getPreviousPanel());
    }

    public JPanel getPreviousPanel(){
        JPanel previousPanel = null;
        previousPanel = recentPanels.get(recentPanels.size()-2);
        return previousPanel;
    }

    public void runLogout(){
        playerController.logOut();
    }

    public void signUp(String username , String password) throws Exception {
        playerController.signUp(username , password);
    }

    public void login(String username , String password) throws Exception{
        playerController.login(username , password);
    }

    public Player getCurrentPlayer(){
        return playerController.getCurrentPlayer();
    }

}
