package fr.lyon1.etu.bigelRouveyre.inter.controler;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;

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
     * The players shortcuts.
     * @return a map with {@link Character} as key and coordinates of case to move as value.
     */
    Map<Character, Function<int[], int[]>> getShortcuts();

    /**
     * Called when a command is entered.
     * @param command the command executing
     */
    void onCommand(Character command);
}
