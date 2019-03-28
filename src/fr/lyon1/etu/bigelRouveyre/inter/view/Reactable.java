package fr.lyon1.etu.bigelRouveyre.inter.view;

import fr.lyon1.etu.bigelRouveyre.inter.controler.Game;

/**
 * Represents any element that should react to an input.
 */
public interface Reactable {

    /**
     * @return the {@link Game} notified when this is trigger
     */
    Game getGame();

    /**
     * Reacts to an input.
     * @return true if the input does not have to be handled any more, else false
     */
    boolean trigger();
}
