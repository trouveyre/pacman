package fr.lyon1.etu.bigelRouveyre.inter.controler;

import fr.lyon1.etu.bigelRouveyre.inter.model.Board;
import fr.lyon1.etu.bigelRouveyre.inter.model.GameResult;
import fr.lyon1.etu.bigelRouveyre.inter.model.PlayerResult;
import fr.lyon1.etu.bigelRouveyre.inter.view.View;

import java.util.List;
import java.util.Set;

/**
 * Defines a game and its rules.
 */
public interface Game {

    /**
     * @return the {@link Board} of this {@link Game}
     */
    Board getBoard();

    /**
     * @return the {@link Player} of this {@link Game}
     */
    List<Player> getPlayers();
    /**
     * @param player the {@link Player} to add
     */
    void addPlayer(Player player);
    /**
     * @param player the {@link Player} to remove
     */
    void removePlayer(Player player);

    /**
     * @return the frequency of {@link fr.lyon1.etu.bigelRouveyre.inter.model.Actor}s' moving
     */
    Double getMovementFrequency();
    /**
     * Sets the frequency of {@link fr.lyon1.etu.bigelRouveyre.inter.model.Actor}s' moving.
     * @param frequency the new frequency
     */
    void setMovementFrequency(double frequency);

    /**
     * @return the {@link View} displaying this {@link Game}
     */
    View getView();

    /**
     * @return true if the {@link Game} is currently running
     */
    boolean isRunning();

    /**
     * Finished the game.
     * Call this function bring 'launch()' to its end.
     */
    void end();
    /**
     * Starts this {@link Game}.
     * @return a describing object of this' results.
     */
    GameResult launch();
    /**
     * Pauses the game until 'resume()' is called.
     */
    void pause();
    /**
     * If this {@link Game} is paused, would resumes this.
     */
    void resume();
}
