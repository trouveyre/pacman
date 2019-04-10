package fr.lyon1.etu.bigelRouveyre.core.model;

import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;
import fr.lyon1.etu.bigelRouveyre.inter.model.*;
import fr.lyon1.etu.bigelRouveyre.inter.view.Drawable;

import java.util.ArrayList;
import java.util.List;

public class StandardActor implements Actor, Cloneable {

    //CONSTRUCTORS

    //FIELDS
    private Board board;
    private Impact impact;
    private Moving moving;
    private Drawable picture;
    private List<Player> players = new ArrayList<>();

    //METHODS
    @Override
    public void addListeningPlayer(Player player) {
        players.add(player);
    }

    @Override
    public void beBorn(Board board) {
        this.board = board;
        this.board.add(this);
        players.forEach(player -> player.onBirth(this));
    }

    @Override
    public void beBornAt(Board board, Coordinates coordinates) {
        board.addAt(coordinates, this);
        this.board = board;
        players.forEach(player -> player.onBirth(this));
    }

    @Override
    public StandardActor clone() {
        StandardActor result = null;
        try {
            result = (StandardActor) super.clone();
            result.setPicture(getPicture().clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void die() {
        board.removeActor(this);
        board = null;
        players.forEach(player -> player.onDeath(this));
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Coordinates getCoordinates() {
        if (isAlive())
            return board.coordinatesOf(this);
        else
            return null;
    }

    @Override
    public Impact getImpact() {
        return impact;
    }

    @Override
    public List<Player> getListeningPlayers() {
        return players;
    }

    @Override
    public Moving getMoving() {
        return moving;
    }

    @Override
    public Drawable getPicture() {
        return picture;
    }

    @Override
    public boolean isAlive() {
        return board != null;
    }

    @Override
    public void move(Coordinates coordinates) {
        moving.move(this, coordinates);
    }

    @Override
    public boolean onContact(Actor actor) {
        return actor.getImpact().apply(actor, this);
    }

    @Override
    public void removeListeningPlayer(Player player) {
        players.remove(player);
    }

    @Override
    public void setImpact(Impact impact) {
        this.impact = impact;
    }

    @Override
    public void setMoving(Moving moving) {
        this.moving = moving;
    }

    @Override
    public void setPicture(Drawable picture) {
        this.picture = picture;
    }

    @Override
    public void shift(Coordinates coordinates) {
        board.removeActor(this);
        board.addAt(coordinates, this);
        players.forEach(player -> player.onMove(this));
    }
}
