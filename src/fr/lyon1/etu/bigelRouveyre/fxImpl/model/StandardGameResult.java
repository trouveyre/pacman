package fr.lyon1.etu.bigelRouveyre.fxImpl.model;

import fr.lyon1.etu.bigelRouveyre.inter.controler.Game;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;
import fr.lyon1.etu.bigelRouveyre.inter.model.GameResult;

import java.util.ArrayList;

public class StandardGameResult implements GameResult {

    //CONSTRUCTORS
    public StandardGameResult(Game game) {
        this.game = game;
    }

    //FIELDS
    private Game game;
    private ArrayList<Player> players = new ArrayList();

    //METHODS
    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
