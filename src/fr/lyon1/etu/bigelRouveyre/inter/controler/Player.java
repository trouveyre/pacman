package fr.lyon1.etu.bigelRouveyre.inter.controler;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Game;
import fr.lyon1.etu.bigelRouveyre.inter.model.PlayerResult;
import fr.lyon1.etu.bigelRouveyre.inter.view.View;

/**
 * Represents any class that makes an {@link Actor} take decisions.
 * @see Actor
 */
public interface Player {

    /**
     * Adds 'points' at this' score.
     * @param points the number of points to add at this' score
     */
    void addScore(int points);

    /**
     * Allows the player driving its {@link Actor}
     */
    void allow();

    /**
     * Disallows the player driving its {@link Actor}
     */
    void disallow();

    /**
     * Function called by a {@link Game} to define this' {@link Actor} acting.
     */
    void drive();

    /**
     * @return the {@link Actor} this drive
     */
    Actor getActor();

    /**
     * @return the {@link fr.lyon1.etu.bigelRouveyre.inter.model.Game} this is playing
     */
    Game getGame();

    /**
     * @return the name of this
     */
    String getName();

    /**
     * @return this' results
     */
    PlayerResult getResult();

    /**
     * @return this' score
     */
    int getScore();

    /**
     * @return the {@link View} this player uses to play or null if this player has no {@link View}
     */
    View getView();

    /**
     * Called when an {@link Actor} is added to the board.
     * @param actor the added {@link Actor}
     */
    void onBirth(Actor actor);

    /**
     * Called when a mouse button is clicked.
     * @param x the x coordinate of the mouse's cursor
     * @param y the y coordinate of the mouse's cursor
     */
    void onClick(double x, double y);

    /**
     * Called when a command is entered.
     * @param command the command executing
     */
    void onCommand(String command);

    /**
     * Called when an {@link Actor} dies.
     * @param actor the {@link Actor} that just dies
     */
    void onDeath(Actor actor);

    /**
     * Called when this lose.
     */
    void onLose();

    /**
     * Called when a listened {@link Actor} moves
     * @param actor the {@link Actor} that just moves
     */
    void onMove(Actor actor);

    /**
     * Called when a {@link Player} is added to the {@link Game}.
     * @param player the added {@link Player}
     */
    void onPlayerAdded(Player player);

    /**
     * Called when a {@link Player} is removed from the {@link Game}.
     * @param player the removed {@link Player}
     */
    void onPlayerRemoved(Player player);

    /**
     * Called when this win.
     */
    void onWin();

    /**
     * @param actor the new {@link Actor} to drive
     */
    void setActor(Actor actor);

    /**
     * @param game the {@link Game} this is going to play
     */
    void setGame(Game game);

    /**
     * @param score the new score of this
     */
    void setScore(int score);

    /**
     * @param view the {@link View} this player uses to play
     */
    void setView(View view);
}
