package fr.lyon1.etu.bigelRouveyre.inter.model;

import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;

import java.util.List;

/**
 * Defines a game and its rules.
 */
public interface Game {

    /**
     * @param player the {@link Player} to add
     */
    void addPlayer(Player player);

    /**
     * Builds this' {@link Board} according to the {@link Generator} passed.
     * @param generator the {@link Generator} building this' {@link Board}
     * @return this its {@link Board} after was built
     */
    Game build(Generator generator);

    /**
     * Finished the game.
     * Call this function bring 'launch()' to its end.
     */
    void end();

    /**
     * @return the {@link Board} of this {@link Game}
     */
    Board getBoard();

    /**
     * @return the frequency of {@link fr.lyon1.etu.bigelRouveyre.inter.model.Actor}s' moving
     */
    Double getMovementFrequency();

    /**
     * @return the {@link Player} of this {@link Game}
     */
    List<Player> getPlayers();

    /**
     * @return the number of elapsed turns
     */
    int getTime();

    /**
     * Initiates this' {@link Board} according to the {@link Initiator} passed.
     * @param initiator the {@link Initiator} initiating this' {@link Board}
     * @return this its {@link Board} after was initiated
     */
    Game initiate(Initiator initiator);

    /**
     * @return true if the {@link Game} is currently running
     */
    boolean isRunning();
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
     * @param player the {@link Player} to remove
     */
    void removePlayer(Player player);

    /**
     * If this {@link Game} is paused, would resumes this.
     */
    void resume();

    /**
     * Sets the frequency of {@link fr.lyon1.etu.bigelRouveyre.inter.model.Actor}s' moving.
     * @param frequency the new frequency
     */
    void setMovementFrequency(double frequency);
}
