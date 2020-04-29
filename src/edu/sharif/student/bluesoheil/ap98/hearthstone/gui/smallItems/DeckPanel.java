package edu.sharif.student.bluesoheil.ap98.hearthstone.gui.smallItems;

import edu.sharif.student.bluesoheil.ap98.hearthstone.Interefaces.ClickListener;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.log.LogTypes;
import edu.sharif.student.bluesoheil.ap98.hearthstone.util.log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeckPanel extends SidePanel implements ActionListener {

    private ClickListener listener;
    private ArrayList<DeckShape> decks;

    public DeckPanel(int width, int height) {
        super(width, height);
    }

    public void setDecks(HashMap<String, String> decksToShow) {
        this.decks = new ArrayList<>();

        for (Map.Entry<String, String> entry : decksToShow.entrySet())
            decks.add(new DeckShape(entry.getKey(), entry.getValue()));

        paintDecksInPanel();

        for (DeckShape deckShape : decks) deckShape.addActionListener(this);
    }

    private void paintDecksInPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 2, 5, 2);
        gc.gridy = 0;
        gc.fill = GridBagConstraints.BOTH;
        for (DeckShape deckShape : decks) {
            add(deckShape, gc);
            gc.gridy++;
        }

    }

    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (listener != null) {
            DeckShape selectedDeck = (DeckShape) e.getSource();
            listener.select(selectedDeck.getDeckName());
            Logger.log(LogTypes.CLICK_BUTTON, "deck:  " + selectedDeck.getDeckName() + "  selected .");
        }
    }
}
