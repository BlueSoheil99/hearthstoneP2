package edu.sharif.student.bluesoheil.ap98.hearthstone.models.InfoPassives;

public class Nurse extends InfoPassive {
    private static Nurse instance;
    Nurse(String description) {
        super(description);
    }
    public static Nurse getInstance() {
        if (instance == null) instance = new Nurse("At the end of each turn, restores a random minion HP");
        return instance;
    }
    @Override
    public void run() {

    }
}
