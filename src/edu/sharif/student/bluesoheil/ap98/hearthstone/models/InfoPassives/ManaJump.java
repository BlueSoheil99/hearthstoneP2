package edu.sharif.student.bluesoheil.ap98.hearthstone.models.InfoPassives;

public class ManaJump extends InfoPassive {
    private static ManaJump instance;
    public ManaJump(String description) {
        super(description);
    }
    public static ManaJump getInstance() {
        if (instance == null) instance = new ManaJump("You'll have one extra mana until you get to 10th turn");
        return instance;
    }

    @Override
    public void run() {

    }
}
