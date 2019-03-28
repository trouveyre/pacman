package fr.lyon1.etu.bigelRouveyre.inter.model;

/**
 * Wraps a method arranging the {@link Actor}s starting on a {@link Board}.
 * @see Board
 * @see Actor
 */
public interface Initiator {

    /**
     * Initiates the {@link Board}.
     * @param board the board to initiate
     * @return 'board' after being initiated
     */
    Board initiate(Board board);
}
