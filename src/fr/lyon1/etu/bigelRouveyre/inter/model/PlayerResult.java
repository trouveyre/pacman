package fr.lyon1.etu.bigelRouveyre.inter.model;

import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;

/**
 * Contains information about a {@link fr.lyon1.etu.bigelRouveyre.inter.controler.Player}'s results.
 */
public interface PlayerResult {

    /**
     * @return the {@link Player} this tells about
     */
    Player getPlayer();

    /**
     * @return the score the {@link Player} have done
     */
    int getScore();

    /**
     * @return the number of turns the {@link Player} was alive
     */
    int getTime();
}
