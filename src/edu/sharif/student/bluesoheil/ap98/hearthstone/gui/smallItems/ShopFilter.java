//package edu.sharif.student.bluesoheil.ap98.hearthstone.gui;
//
//import edu.sharif.student.bluesoheil.ap98.hearthstone.Administer;
//import edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems.NavigationPanel;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class ShopFilter extends JPanel {
//
//    private static FilterPanel instance;
//    private JButton filterBtn;
//    private TextField searchFiled;
//    private JLabel titleLabel, manaLabel, nameLabel, classLabel, cardsLabel;
//
//    public FilterPanel (int width, int height){
//        setPreferredSize(new Dimension(width,height));
//        setBackground(new Color(168, 118, 94));
//        createComponents();
//        addComponents();
//        setBorder(BorderFactory.createMatteBorder(20,5,40,5,new Color(0x562C1C)));
//    }
//
//
//    private void createComponents() {
//        createLabels();
//        createFilterButton();
//    }
//
//    private void addComponents() {
//        setLayout(new GridBagLayout());
//        GridBagConstraints gc = new GridBagConstraints();
//        gc.insets = new Insets(5,5,5,5);
//        ///1st row //
//        gc.gridx =0;
//        gc.gridy = GridBagConstraints.RELATIVE;
//        gc.gridwidth = 2;
//        gc.weighty = 3;
//        add(titleLabel,gc);
//        ///2nd row///
//        gc.gridwidth = 1;
//        gc.weighty = 1;
////        gc.gridy++;
//        add(nameLabel,gc);
//        gc.gridx =1;
//        add(searchFiled,gc);
//        ///3rd row///
////        gc.gridy ++;
//        gc.gridx =0;
//        add(classLabel,gc);
//        ///4th row////
////        gc.gridy ++;
//        add(manaLabel,gc);
//        ///5th row///
////        gc.gridy ++;
//        add(cardsLabel,gc);
//
//        ///6th row///
////        gc.gridy ++;
//        gc.gridwidth = 2;
//        add(filterBtn,gc);
//        ///6th row///
////        gc.gridy++;
//        add(NavigationPanel.getInstance(),gc);
//    }
//
//    private void createLabels(){
//        titleLabel = new JLabel(" Filters ");
//        titleLabel.setFont(new Font("Serif",Font.ITALIC,40));
//
//        searchFiled = new TextField(20);
//        nameLabel = new JLabel("Name: ");
//        manaLabel = new JLabel("Mana: ");
//        classLabel = new JLabel("Class: ");
//        cardsLabel = new JLabel("Cards: ");
//    }
//
//    private void createFilterButton(){
//        filterBtn = new JButton("");
//        filterBtn.setIcon(new ImageIcon("src/res/edu/sharif/student/bluesoheil/ap98/hearthstone/Images/filter.png"));
//        filterBtn.setContentAreaFilled(false);
//        filterBtn.setBorderPainted(false);
//        setFilterListener();
//
//    }
//
//    private void setFilterListener() {
//        filterBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String name = searchFiled.getText();
//                Administer.getInstance().filter();
//            }
//        });
//    }
//
//}
