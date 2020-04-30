package edu.sharif.student.bluesoheil.ap98.hearthstone.gui.collection;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Administer;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Interefaces.FilterListener;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.NavigationPanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.SidePanel;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.Configuration.GuiConfigs.GuiConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterPanel extends SidePanel {
    private static FilterPanel instance;
    private JButton filterBtn;
    private TextField searchFiled;
    private JLabel titleLabel, manaLabel, nameLabel, classLabel, cardsLabel;
    private FilterListener filterListener;

    public FilterPanel(int width, int height) {
        super(width, height);
        createComponents();
        addComponents();
    }


    private void createComponents() {
        createLabels();
        createFilterButton();
    }

    private void addComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 5, 5, 5);
        ///1st row //
        gc.gridx = 0;
        gc.gridy = GridBagConstraints.RELATIVE;
        gc.gridwidth = 2;
        gc.weighty = 3;
        add(titleLabel, gc);
        ///2nd row///
        gc.gridwidth = 1;
        gc.weighty = 1;
//        gc.gridy++;
        add(nameLabel, gc);
        gc.gridx = 1;
        add(searchFiled, gc);
        ///3rd row///
//        gc.gridy ++;
        gc.gridx = 0;
        add(classLabel, gc);
        ///4th row////
//        gc.gridy ++;
        add(manaLabel, gc);
        ///5th row///
//        gc.gridy ++;
        add(cardsLabel, gc);

        ///6th row///
//        gc.gridy ++;
        gc.gridwidth = 2;
        add(filterBtn, gc);
        ///6th row///
//        gc.gridy++;
        add(NavigationPanel.getInstance(), gc);
    }

    private void createLabels() {
        titleLabel = new JLabel(" Filters ");
        titleLabel.setFont(new Font("Serif", Font.ITALIC, 40));

        searchFiled = new TextField(20);
        nameLabel = new JLabel("Name: ");
        manaLabel = new JLabel("Mana: ");
        classLabel = new JLabel("Class: ");
        cardsLabel = new JLabel("Cards: ");
    }

    private void createFilterButton() {
        filterBtn = new JButton("");
        filterBtn.setIcon(new ImageIcon(GuiConstants.getInstance().getFilterIconPath()));
        filterBtn.setContentAreaFilled(false);
        filterBtn.setBorderPainted(false);

        filterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administer.getInstance().getAllCards();
//                String name = searchFiled.getText();
//                boolean owned;
//                boolean notOwned;
//                int manaCost;
//                String hero;
//                FilterEvent filterEvent = new FilterEvent(this,name , owned,notOwned,manaCost, hero );

//                if (filterListener != null) filterListener.filter(filterEvent);
            }
        });
    }

    public void setFilterListener(FilterListener filterListener){
        this.filterListener = filterListener;
    }
}


