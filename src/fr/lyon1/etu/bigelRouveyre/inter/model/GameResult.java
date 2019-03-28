package fr.lyon1.etu.bigelRouveyre.inter.model;

import fr.lyon1.etu.bigelRouveyre.inter.controler.Game;

/**
 * Wraps data about an ended {@link Game}.
 * @see Game
 *
 */
public interface GameResult {

    /**
     * @return the {@link Game} this describes
     */
    Game getGame();
}
