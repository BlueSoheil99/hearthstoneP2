package edu.sharif.student.bluesoheil.ap98.hearthstone.models;

import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Cards.Card;
import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Heroes.Hero;

import java.util.ArrayList;

public class Deck {
    //TODO use configuration here
    private final int MAXIMUM_NUMBER_OF_CARDS = 20;
    private final int MINIMUM_NUMBER_OF_CARDS = 5;

    private String name;
    private int numberOfCards;
    private ArrayList<Card> cards;
    private int gamesPlayed;
    private int wins;
    private float winRatio;
    private float manaAverage;
    private Hero hero;
    private Card mostUsedCard;

    public Deck(String name){
        this.name =name;
    }

}
