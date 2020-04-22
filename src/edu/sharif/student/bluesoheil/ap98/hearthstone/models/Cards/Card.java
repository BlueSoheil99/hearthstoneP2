package edu.sharif.student.bluesoheil.ap98.hearthstone.models.Cards;

import edu.sharif.student.bluesoheil.ap98.hearthstone.controllers.CardController;

public abstract class Card {
    enum Rarity  {Common , Rare, Epic , Legendary}
    public enum HeroClass { Mage , Rogue , Warlock , Neutral}

    //properties
    private String name;
    private  int manaCost;
    private String type ;
    private HeroClass heroClass;
    private Rarity rarity;
    private String description;
    private int cost;

    public Card(String name , int manaCost , Rarity rarity, HeroClass heroClass , String description, int cost){
        setName(name);
        setManaCost(manaCost);
        type = getClass().getSimpleName();
        setHeroClass(heroClass);
        setRarity(rarity);
        setDescription(description);
        setCost(cost);
//        CardController.getInstance().saveCard(this);
    }

    //todo delete methods that only use here , for example i THINK setManaCost is not necessary
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getManaCost() {
        return manaCost;
    }
    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public HeroClass getHeroClass() {
        return heroClass;
    }
    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }
    public Rarity getRarity() {
        return rarity;
    }
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }


    @Override
    public String toString() {
        return name;
    }
}
