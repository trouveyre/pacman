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
     * @return a {@link Set} of {@link Coordinates} that will always be filled as wall
     */
    Set<Coordinates> wallCases();
}
