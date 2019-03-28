package fr.lyon1.etu.bigelRouveyre.inter.model;

import fr.lyon1.etu.bigelRouveyre.inter.controler.Game;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;

import java.util.List;

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

    /**
     * @return the {@link Actor}s who have lost
     */
    List<Player> getLosers();

    /**
     * @return the {@link Player}s of the {@link Game}
     */
    List<Player> getPlayers();

    /**
     * @return the {@link Actor}s who have won
     */
    List<Player> getWinner();
}
