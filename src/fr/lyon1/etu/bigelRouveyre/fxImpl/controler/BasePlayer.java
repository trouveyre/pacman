package fr.lyon1.etu.bigelRouveyre.fxImpl.controler;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class BasePlayer implements Player {

    //CONSTRUCTORS
    public BasePlayer(Actor actor) {
        this.actor = actor;
    }

    //FIELDS
    private Actor actor;
    boolean isAllow = true;
    private Map<Character, Function<int[], int[]>> shortcuts = new HashMap();

    //METHODS
    @Override
    public void allow() {
        isAllow = true;
    }

    @Override
    public void disallow() {
        isAllow = false;
    }

    @Override
    public void drive() {
        if (isAllow)
            onDrive();
    }

    @Override
    public Actor getActor() {
        return actor;
    }

    @Override
    public Map<Character, Function<int[], int[]>> getShortcuts() {
        return shortcuts;
    }

    /**
     * Executed if allowed when drive is called.
     */
    protected abstract void onDrive();
}
