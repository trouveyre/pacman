package fr.lyon1.etu.bigelRouveyre.inter.model;

import java.util.Set;

/**
 * The top level interface describing a map.
 * This is represented as a two-dimension array of {@link Actor}s.
 * @see Actor
 */
public interface Board {

    /**
     * Returns the {@link Actor} at the row 'row' and the column 'column'.
     * @param row the row number where find a {@link Actor}
     * @param column the column number where find a {@link Actor}
     * @return the found {@link Actor} at ('row', 'column')
     */
    Actor atCase(int row, int column);
    /**
     * Places 'newLocatable' at the row 'row' and the column 'column' of this {@link Board}
     * @param row the row number where place the new {@link Actor}
     * @param column the column number where place the new {@link Actor}
     * @param newActor the new {@link Actor} to place
     * @return the old {@link Actor} at the place ('row', 'column')
     */
    Actor atCase(int row, int column, Actor newActor);

    /**
     * Builds this map according to the {@link Generator} passed.
     * @param generator the {@link Generator} building this map
     * @return this {@link Board} after being built
     */
    Board build(Generator generator);

    /**
     * @return all the {@link Actor}s on this.
     */
    Set<Actor> getActors();

    /**
     * Returns a two-dimension array with the {@link Actor}s this {@link Board} contains.
     * @return the {@link Actor}s of this {@link Board}
     */
    Case[][] getCases();

    /**
     * @return the height of this {@link Board}
     */
    int getHeight();
    /**
     * @return the width of this {@link Board}
     */
    int getWidth();

    /**
     * Initiates this map according to the {@link Initiator} passed.
     * @param initiator the {@link Initiator} initiating this map
     * @return this {@link Board} after being initiated
     */
    Board initiate(Initiator initiator);
}
