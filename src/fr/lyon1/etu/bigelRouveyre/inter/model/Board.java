package fr.lyon1.etu.bigelRouveyre.inter.model;

import java.util.List;
import java.util.Set;

/**
 * The top level interface describing a map.
 * This is represented as a two-dimension array of {@link Actor}s.
 * @see Actor
 */
public interface Board {

    /**
     * Adds an {@link Actor} to this according to the adding tuning.
     * @param actor the {@link Actor} to add
     */
    void add(Actor actor);

    /**
     * Adds 'actor' to the case at ('row, 'column').
     * @param coordinates the coordinates where place 'actor'
     * @param actor the {@link Actor} to place
     */
    void addAt(Coordinates coordinates, Actor actor);

    /**
     * Returns the {@link Actor} at the row 'row' and the column 'column'.
     * @param coordinates the coordinates where search
     * @return the found {@link Actor} at ('row', 'column')
     */
    Set<Actor> atCase(Coordinates coordinates);
    /**
     * Places 'newActors' at the row 'row' and the column 'column' of this {@link Board}.
     * @param coordinates the coordinates where set the {@link Actor}s
     * @param newActors the new {@link Actor}s to place
     * @return the old {@link Actor}s at the place ('row', 'column')
     */
    Set<Actor> atCase(Coordinates coordinates, Set<Actor> newActors);

    /**
     * @param actor the {@link Actor} to find
     * @return the coordinates of 'actor'
     */
    Coordinates coordinatesOf(Actor actor);

    /**
     * @return all the {@link Actor}s on this.
     */
    Set<Actor> getActors();
    /**
     * Removes of this 'actor'.
     * @param actor the {@link Actor} to remove
     * @return the removed {@link Actor}'s coordinates
     */
    Coordinates removeActor(Actor actor);

    /**
     * @return the sizes of this {@link Board}
     */
    int[] getSizes();

    /**
     * Removes "actor" at the row "row" and the column 'column'.
     * @param coordinates the coordinates of the {@link Actor} to remove
     * @param actor the {@link Actor} to remove
     */
    void removeAt(Coordinates coordinates, Actor actor);

    /**
     * Removes all the {@link Actor}s at the row "row" and the column 'column'.
     * @param coordinates the coordinates where remove the {@link Actor}s
     * @return a {@link List} containing the removed {@link Actor}s
     */
    Set<Actor> removeAllAt(Coordinates coordinates);

    /**
     * Returns the cases where an {@link Actor} can be added.
     * This is used by the 'addActor' method.
     * @return a {@link Set} {@link Coordinates}
     */
    Set<Coordinates> spawns();
}
