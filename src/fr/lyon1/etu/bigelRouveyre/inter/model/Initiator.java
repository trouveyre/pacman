package fr.lyon1.etu.bigelRouveyre.inter.model;

/**
 * Wraps a method arranging the {@link Actor}s starting on a {@link Board}.
 * @see Board
 * @see Actor
 */
public interface Initiator<G extends Game> {

    /**
     * Initiates the {@link Game}.
     * @param game the {@link Game} to initiate
     */
    void initiate(G game);
}
