package fr.lyon1.etu.bigelRouveyre.fxImpl.controler;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;
import fr.lyon1.etu.bigelRouveyre.inter.model.PlayerResult;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class BasePlayer implements Player {

    //CONSTRUCTORS
    public BasePlayer(String name, Actor actor) {
        this.actor = actor;
        this.name = name;
    }

    //FIELDS
    private Actor actor;
    boolean isAllow = true;
    private String name;
    private PlayerResult result = null;
    private Map<String, Function<int[], int[]>> shortcuts = new HashMap();

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
    public String getName() {
        return name;
    }

    @Override
    public PlayerResult getResult() {
        return result;
    }
    @Override
    public void setResult(PlayerResult result) {
        this.result = result;
    }

    @Override
    public Map<String, Function<int[], int[]>> getShortcuts() {
        return shortcuts;
    }

    /**
     * Executed if allowed when drive is called.
     */
    protected abstract void onDrive();

    public static String randomName() {
        return "Player" + (int) (Math.random() * 100);
    }
}
