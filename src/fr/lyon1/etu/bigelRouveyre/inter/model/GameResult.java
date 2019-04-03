package fr.lyon1.etu.bigelRouveyre.inter.model;

import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;

import java.util.List;
import java.util.Map;

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
     * @param player the new loser
     */
    void addLoser(Player player);

    /**
     * @return the {@link PlayerResult}s of the {@link Game}
     */
    //Map<Integer, PlayerResult> getPlayerResults();

    /**
     * @return the {@link Actor}s who have won
     */
    List<Player> getWinners();
    /**
     * @param player the new winner
     */
    void addWinner(Player player);
}
