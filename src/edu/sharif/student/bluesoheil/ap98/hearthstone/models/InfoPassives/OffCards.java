package edu.sharif.student.bluesoheil.ap98.hearthstone.models.InfoPassives;

public class OffCards extends InfoPassive {
    private static OffCards instance;

    OffCards(String description) {
        super(description);
    }

    public static OffCards getInstance() {
        if (instance == null) instance = new OffCards("Every card will cost 1 less mana ");
        return instance;
    }

    @Override
    public void run() {

    }
}
