package fr.lyon1.etu.bigelRouveyre.inter.model;

import java.util.Collection;
import java.util.List;

public interface Case {

    /**
     * Adds 'actor' to this.
     * @param actor the {@link Actor} to add
     */
    void addActor(Actor actor);

    /**
     * Empties this.
     * @return a {@link List} with the {@link Actor}s in this
     */
    List<Actor> empty();

    /**
     * @return the first {@link Actor} arrived in this {@link Case}
     */
    Actor getFirstActor();

    /**
     * @return a {@link List} of the {@link Actor}s in this
     */
    List<Actor> getActors();

    /**
     * Sets the {@link Actor}s in this.
     * @param actors the {@link Actor}s to put in this
     */
    void setActors(Collection<Actor> actors);

    /**
     * Removes the {@link Actor} 'actor'.
     * @param actor the {@link Actor} to remove
     */
    void removeActor(Actor actor);
}
