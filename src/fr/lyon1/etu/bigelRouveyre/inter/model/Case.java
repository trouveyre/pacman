package fr.lyon1.etu.bigelRouveyre.inter.model;

import java.util.List;

public interface Case {

    /**
     * @return the {@link Actor} in this {@link Case}
     */
    Actor getActor();

    /**
     * @return a {@link List} of the {@link Actor}s in this
     */
    List<Actor> getAllActor();

    /**
     * Puts "actor" in this.
     * @param actor the {@link Actor} to put
     */
    void putActor(Actor actor);
}
