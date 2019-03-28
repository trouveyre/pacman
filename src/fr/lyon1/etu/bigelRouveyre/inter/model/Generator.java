package fr.lyon1.etu.bigelRouveyre.inter.model;

/**
 * Wraps a map generator method.
 * @see Board
 */
public interface Generator {

    /**
     * Makes the {@link Board} to be playable.
     * @param board the board to build
     * @return 'board' after being built
     */
    Board build(Board board);
}
