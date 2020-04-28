package edu.sharif.student.bluesoheil.ap98.hearthstone.models.InfoPassives;

public abstract class InfoPassive {
    public static enum Types {  ManaJump, Nurse, OffCards, TwiceDraw, Warriors }

    private String description;

    InfoPassive(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract void run();
}
