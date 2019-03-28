package fr.lyon1.etu.bigelRouveyre.inter.model;

/**
 * Wraps a function called when an {@link Actor} meet another {@link Actor}.
 * @see Actor
 */
public interface Impact {

    /**
     * Applies this from doer to target.
     * @param doer the one who produces this {@link Impact}
     * @param target the one who deal with this {@link Impact}
     * @return true if the doer still can move otherwise false
     */
    boolean apply(Actor doer, Actor target);
}
