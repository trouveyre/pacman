package fr.lyon1.etu.bigelRouveyre.inter.model;

/**
 * Wraps a moving function.
 */
public interface Moving {

    /**
     * Moves the {@link Actor} 'doer' toward the case at ('row', 'column').
     * @param doer the {@link Actor} moving
     * @param row the row toward moving
     * @param column the column toward moving
     */
    void move(Actor doer, int row, int column);
}
