package edu.sharif.student.bluesoheil.ap98.hearthstone.models.InfoPassives;

public class TwiceDraw extends InfoPassive {
    public static TwiceDraw instance;

    TwiceDraw(String description) {
        super(description);
    }
    public static TwiceDraw getInstance() {
        if (instance == null) instance = new TwiceDraw("In each turn you can have 2 cards instead of one");
        return instance;
    }

    @Override
    public void run() {

    }
}
