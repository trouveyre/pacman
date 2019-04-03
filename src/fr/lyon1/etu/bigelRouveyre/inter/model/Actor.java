package fr.lyon1.etu.bigelRouveyre.inter.model;

import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;
import fr.lyon1.etu.bigelRouveyre.inter.view.Drawable;

import java.util.List;

/**
 * The top level interface for any object on a {@link Board}.
 * @see Board
 * @see Impact
 * @see Moving
 */
public interface Actor {

    /**
     * Places this on a {@link Board}.
     * @param board the {@link Board} where place this
     */
    void beBorn(Board board);

    /**
     * Places this on a {@link Board} at the given {@link Coordinates}
     * @param board the {@link Board} where place this
     * @param coordinates the coordinates where place this
     */
    void beBornAt(Board board, Coordinates coordinates);

    Actor clone();

    /**
     * Kills this.
     */
    void die();

    /**
     * @return true if this {@link Actor} is dead.
     */
    boolean isAlive();

    /**
     * @return the {@link Board} where this is
     */
    Board getBoard();

    /**
     * @return the coordinates where this is
     */
    Coordinates getCoordinates();

    /**
     * Shifts this at the new location ('row', 'column').
     * @param coordinates the coordinates where shift this
     */
    void shift(Coordinates coordinates);

    /**
     * @return the {@link Impact} that this has when meeting another {@link Actor}
     */
    Impact getImpact();
    /**
     * Sets the {@link Impact} that this has when meeting another {@link Actor}
     * @param impact the new {@link Impact}
     */
    void setImpact(Impact impact);
    /**
     * Applies the {@link Impact}.
     * @param actor the {@link Actor} met
     * @return true if "actor" can still move else false
     */
    boolean onContact(Actor actor);

    /**
     * @return the {@link Moving} used by this
     */
    Moving getMoving();
    /**
     * @param movement the new {@link Moving} this will use
     */
    void setMoving(Moving movement);
    /**
     * Moves this toward the case ('row', 'column') according to this' moving field.
     * @param coordinates the coordinates toward moving
     */
    void move(Coordinates coordinates);

    /**
     * @return the {@link Drawable} used to draw this
     */
    Drawable getPicture();
    /**
     * Sets this' picture.
     * @param picture the new {@link Drawable} used to draw this
     */
    void setPicture(Drawable picture);

    /**
     * @return the {@link Player}s listenning to this
     */
    List<Player> getListeningPlayers();

    /**
     * @param player a {@link Player} that has to listen to this
     */
    void addListeningPlayer(Player player);

    /**
     * @param player a {@link Player} that has no longer to listen to this
     */
    void removeListeningPlayer(Player player);
}
