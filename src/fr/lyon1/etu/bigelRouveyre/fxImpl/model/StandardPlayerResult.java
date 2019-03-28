package fr.lyon1.etu.bigelRouveyre.fxImpl.model;

import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;
import fr.lyon1.etu.bigelRouveyre.inter.model.PlayerResult;

public class StandardPlayerResult implements PlayerResult {

    //CONSTRUCTORS
    public StandardPlayerResult(Player player, int score, int time) {
        this.player = player;
        this.time = time;
        this.score = score;
    }

    //FIELDS
    private Player player;
    private int time;
    private int score;

    //METHODS
    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return player.getName() + " has " + (time==0? "won the game": "lost at turn " + time) + " with " + score + " points";
    }
}
