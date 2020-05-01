package edu.sharif.student.bluesoheil.ap98.hearthstone.gui.collection;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Administer;
import edu.sharif.student.bluesoheil.ap98.hearthstone.Interefaces.FilterListener;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.BackButton;
import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.ExitButton;
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
    private JLabel titleLabel, manaLabel, nameLabel, classLabel, cardsLabel;
    private TextField searchFiled;
    private JComboBox heroBox;
    private JComboBox manaBox;
    private JCheckBox ownedBox, notOwnedBox;
    private FilterListener filterListener;

    public FilterPanel(int width, int height) {
        super(width, height);
        createComponents();
        addComponents();
    }


    private void createComponents() {
        createLabels();
        createComboBoxes();
        createCheckBoxes();
        createFilterButton();
    }

    private void addComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 5, 5, 5);
        ///1st row //
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.weighty = 3;
        add(titleLabel, gc);
        /// next row///
        gc.gridy++;
        gc.gridwidth = 1;
        gc.weighty = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(nameLabel, gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(searchFiled, gc);
        ///next row///
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(classLabel, gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(heroBox, gc);
        ///next row////
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(manaLabel, gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(manaBox, gc);
        ///next row///
        gc.gridy++;
        gc.gridx = 0;
        gc.weighty=0.5;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(cardsLabel, gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(ownedBox, gc);
        ///next row///
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(notOwnedBox, gc);
        ///next row///
        gc.gridy ++;
        gc.gridx =0;
        gc.gridwidth = 2;
        gc.weighty=2;
        gc.anchor = GridBagConstraints.NORTH;
        add(filterBtn, gc);
        ///next row///
        gc.gridy++;
        gc.gridwidth = 1;
        gc.gridx =0;
        gc.anchor = GridBagConstraints.SOUTH;
        add(ExitButton.getInstance(), gc);
        gc.gridx =1;
        add(BackButton.getInstance(), gc);
    }

    private void createLabels() {
        titleLabel = new JLabel(" Filters ");
        titleLabel.setFont(new Font("Serif", Font.ITALIC, 40));
        searchFiled = new TextField(15);
        nameLabel = new JLabel("Name: ");
        manaLabel = new JLabel("Mana: ");
        classLabel = new JLabel("Class: ");
        cardsLabel = new JLabel("Cards: ");
    }

    private void createComboBoxes() {
        heroBox = new JComboBox();
        DefaultComboBoxModel heroModel = new DefaultComboBoxModel();
        heroModel.addElement("All");
        heroModel.addElement("Neutral");
        heroModel.addElement("Mage");
        heroModel.addElement("Rogue");
        heroModel.addElement("Warlock");
        heroModel.addElement("Hunter");
        heroModel.addElement("Priest");
        heroBox.setModel(heroModel);
        heroBox.setSelectedIndex(0);

        manaBox = new JComboBox();
        DefaultComboBoxModel manaModel = new DefaultComboBoxModel();
        manaModel.addElement("Any");
        for (int i = 0; i < 11; i++) manaModel.addElement(Integer.toString(i));
        manaBox.setModel(manaModel);
        manaBox.setSelectedIndex(0);
    }

    private void createCheckBoxes() {
        ownedBox = new JCheckBox("Owned ");
        notOwnedBox = new JCheckBox("Not Owned ");
        ownedBox.setSelected(true);
        notOwnedBox.setSelected(true);
        ownedBox.setOpaque(false);
        notOwnedBox.setOpaque(false);
    }

    private void createFilterButton() {
        filterBtn = new JButton("");
        filterBtn.setIcon(new ImageIcon(GuiConstants.getInstance().getFilterIconPath()));
        filterBtn.setContentAreaFilled(false);
        filterBtn.setBorderPainted(false);

        filterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = searchFiled.getText();
                boolean owned = ownedBox.isSelected();
                boolean notOwned = notOwnedBox.isSelected();
                String hero = (String) heroBox.getSelectedItem();
                int manaCost;
                if (((String) manaBox.getSelectedItem()).equals("Any")) {
                    manaCost = -1;
                } else manaCost = Integer.parseInt((String) manaBox.getSelectedItem());

                FilterEvent filterEvent = new FilterEvent(this, name, owned, notOwned, manaCost, hero);
                if (filterListener != null) filterListener.filter(filterEvent);
            }
        });
    }

    public void setFilterListener(FilterListener filterListener) {
        this.filterListener = filterListener;
    }
}


