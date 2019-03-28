package fr.lyon1.etu.bigelRouveyre.inter.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * The top level interface describing a map.
 * This is represented as a two-dimension array of {@link Actor}s.
 * @see Actor
 */
public interface Board {

    /**
     * Adds 'actor' to the case at ('row, 'column').
     * @param row the row where place 'actor'
     * @param column the column where place 'actor'
     * @param actor the {@link Actor} to place
     */
    void addAt(int row, int column, Actor actor);

    /**
     * Returns the {@link Actor} at the row 'row' and the column 'column'.
     * @param row the row number where find a {@link Actor}
     * @param column the column number where find a {@link Actor}
     * @return the found {@link Actor} at ('row', 'column')
     */
    List<Actor> atCase(int row, int column);
    /**
     * Places 'newActors' at the row 'row' and the column 'column' of this {@link Board}.
     * @param row the row number where place the new {@link Actor}s
     * @param column the column number where place the new {@link Actor}s
     * @param newActors the new {@link Actor}s to place
     * @return the old {@link Actor}s at the place ('row', 'column')
     */
    List<Actor> atCase(int row, int column, Collection<Actor> newActors);

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

    /**
     * Removes "actor" at the row "row" and the column 'column'.
     * @param row the row of the {@link Actor} to remove
     * @param column the column of the {@link Actor} to remove
     * @param actor the {@link Actor} to remove
     */
    void removeAt(int row, int column, Actor actor);

    /**
     * Removes all the {@link Actor}s at the row "row" and the column 'column'.
     * @param row the row where remove the {@link Actor}s
     * @param column the column where remove the {@link Actor}s
     * @return a {@link List} containing the removed {@link Actor}s
     */
    List<Actor> removeAllAt(int row, int column);
}
