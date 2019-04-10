package fr.lyon1.etu.bigelRouveyre.inter.model;

import java.util.Set;

/**
 * Wraps a map generator method.
 * @see Board
 */
public interface Generator {

    /**
     * Makes a playable {@link Board}.
     * @return a playable {@link Board}
     */
    Board build();

    /**
     * @return a {@link Set} of {@link Coordinates} that will never be filled by this
     */
    Set<Coordinates> freeCases();

    /**
     * @return the {@link Actor} used as wall
     */
    Actor getPrototype();

    /**
     * @param prototype the {@link Actor} to use as wall
     */
    void setPrototype(Actor prototype);

    /**
     * @return a {@link Set} of {@link Coordinates} that will always be filled as wall
     */
    Set<Coordinates> wallCases();
}
