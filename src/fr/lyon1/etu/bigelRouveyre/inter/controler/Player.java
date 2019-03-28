package fr.lyon1.etu.bigelRouveyre.inter.controler;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.PlayerResult;

import java.util.Map;
import java.util.function.Function;

/**
 * Represents any class that makes an {@link Actor} take decisions.
 * @see Actor
 */
public interface Player {

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
     * @return the name of this
     */
    String getName();

    /**
     * @return the results or null if this has not finished
     */
    PlayerResult getResult();

    /**
     * @param result this' results
     */
    void setResult(PlayerResult result);

    /**
     * The players shortcuts.
     * @return a map with {@link Character} as key and coordinates of case to move as value.
     */
    Map<String, Function<int[], int[]>> getShortcuts();

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
}
