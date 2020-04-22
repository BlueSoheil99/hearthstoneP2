package edu.sharif.student.bluesoheil.ap98.hearthstone.Interefaces;

//todo use this for creating objects in cardController playerController DeckController etc.
public interface ObjectFactory<T> {

    /**
     * Returns a new instance of an object type T.
     *
     * @return T an new instance of the object type T
     */
    public abstract T createNew();
}
