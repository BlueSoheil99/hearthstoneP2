package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Administer;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Interefaces.ClickListener;
import edu.sharif.student.bluesoheil.ap98.hearthstone.controllers.CardControllerException;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.NavigationPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.ConfigLoader;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.GuiConfigs.GuiConstants;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.log.LogTypes;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopPanel extends GamePanel {
    private GuiConstants properties;
    private JPanel controlPanel;
    private CardPanel cardPanel;
    private String selectedCard;

    private JLabel coins, selectLabel, cardLabel, costLabel;
    private JButton sellBtn;
    private JButton buyBtn;
    private Font font;

    @Override
    protected void loadConfig() {
        properties = GuiConstants.getInstance();
    }

    @Override
    protected void createFields() {
        cardPanel = new CardPanel();
        cardPanel.setCards(Administer.getInstance().filterCards());
        cardPanel.setClickListener(new ClickListener() {
            @Override
            public void select(String selectedCardName) {
                selectedCard = selectedCardName;
                revalidateController();
            }
        });
        createControlPanel();
    }

    @Override
    protected void init() {
        setLayout(new BorderLayout());
        add(new JScrollPane(cardPanel), BorderLayout.CENTER);
        add(controlPanel, BorderLayout.WEST);

    }

    private void createControlPanel() {
        font = new Font("serif", Font.BOLD, 40);
        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(getWidth() / 5, getHeight()));
        controlPanel.setBackground(new Color(168, 118, 94));
        controlPanel.setBorder(BorderFactory.createMatteBorder(20, 5, 40, 5, new Color(0x562C1C)));
        createControllerComponents();
        initControllerComponents();
    }

    private void createControllerComponents() {
        int playerCoins = Administer.getInstance().getPlayerCoins();
        coins = new JLabel(Integer.toString(playerCoins));
        coins.setIcon(new ImageIcon(properties.getCoinsIconPath()));
        coins.setFont(font);

        selectLabel = new JLabel("selected card: ");
        selectLabel.setFont(font);
        cardLabel = new JLabel(selectedCard);
        cardLabel.setFont(font);

        costLabel = new JLabel("card cost: " + getSelectedCardCost());
        costLabel.setFont(font);

        sellBtn = new JButton("Sell");
        sellBtn.setFont(font);
        sellBtn.setContentAreaFilled(false);

        buyBtn = new JButton("Buy");
        buyBtn.setFont(font);
        buyBtn.setContentAreaFilled(false);

        setControllerActionListeners();
    }

    private void initControllerComponents() {
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 5, 5, 5);
        gc.gridy = 0;
//        gc.weighty=1;
        gc.weighty = 2;
        controlPanel.add(coins, gc);
        /////////
        gc.gridy++;
        gc.weighty = 1;
        controlPanel.add(selectLabel, gc);
        //////////
        gc.gridy++;
        controlPanel.add(cardLabel, gc);
        //////////
        gc.gridy++;
        controlPanel.add(costLabel, gc);
        //////////
        gc.weighty = 2;
        gc.gridy++;
        controlPanel.add(sellBtn, gc);
        ////////
        gc.gridy++;
        gc.weighty = 2;
        controlPanel.add(buyBtn, gc);
        ///////
        gc.gridy++;
        gc.weighty = 0.5;
        gc.anchor = GridBagConstraints.PAGE_END;
        //in the end add exit and back button
        controlPanel.add(NavigationPanel.getInstance(), gc);
    }

    private int getSelectedCardCost() {
        if (selectedCard == null) return 0;
        return Administer.getInstance().getCardCost(selectedCard);
    }

    private void revalidateController() {
        cardLabel.setText(selectedCard);
        costLabel.setText("card cost: " + getSelectedCardCost());
        int playerCoins = Administer.getInstance().getPlayerCoins();
        coins.setText(Integer.toString(playerCoins));
    }

    private void setControllerActionListeners() {

        /////////////////////////////////////////////////////
        //////////////////   SELL BUTTON  ///////////////////
        /////////////////////////////////////////////////////

        sellBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logger.log(LogTypes.CLICK_BUTTON , "Sell Button selected");

                if (selectedCard != null) {
                    //todo add image to confirm msg
                    int result = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to sell " + selectedCard + " card?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            Administer.getInstance().sellCard(selectedCard);
                            JOptionPane.showMessageDialog(null, "You sold the card successfully");
                            Logger.log(LogTypes.SHOP , "card: "+selectedCard+" sold");
                            revalidateController();
                            selectedCard = null;

                        } catch (CardControllerException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            Logger.logError(LogTypes.SHOP , ex);
                        }

                    }else Logger.log(LogTypes.CLICK_BUTTON , " selling canceled");

                } else {
                    JOptionPane.showMessageDialog(null, "No card is selected");
                    Logger.logError(LogTypes.SHOP , new GuiException("No card is selected"));
                }

            }
        });

        /////////////////////////////////////////////////////
        /////////////////  BUY BUTTON  //////////////////////
        /////////////////////////////////////////////////////

        buyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logger.log(LogTypes.CLICK_BUTTON , "Buy Button selected");

                if (selectedCard != null) {
                    int result = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to buy " + selectedCard + " card?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            Administer.getInstance().buyCard(selectedCard);
                            JOptionPane.showMessageDialog(null, "You bought the card successfully");
                            revalidateController();
                            Logger.log(LogTypes.SHOP , "card: "+selectedCard+" purchased");
                            selectedCard = null;

                        } catch (CardControllerException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            Logger.logError(LogTypes.SHOP , ex);

                        }
                    }else Logger.log(LogTypes.CLICK_BUTTON , " Buying canceled");

                } else  {
                    JOptionPane.showMessageDialog(null, "No card is selected");
                    Logger.logError(LogTypes.SHOP , new GuiException("No card is selected"));
                }
            }
        });

    }


}

