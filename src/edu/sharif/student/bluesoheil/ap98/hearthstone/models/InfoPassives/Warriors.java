package edu.sharif.student.bluesoheil.ap98.hearthstone.models.InfoPassives;

public class Warriors extends InfoPassive{
    public static Warriors instance;
    Warriors(String description) {
        super(description);
    }
    public static Warriors getInstance() {
        if (instance == null) instance = new Warriors("If a summoned minion dies, hero gets 2 defence");
        return instance;
    }
    @Override
    public void run() {

    }
}
