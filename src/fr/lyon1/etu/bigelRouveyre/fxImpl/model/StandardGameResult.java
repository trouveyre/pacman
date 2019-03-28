package fr.lyon1.etu.bigelRouveyre.fxImpl.model;

import fr.lyon1.etu.bigelRouveyre.inter.controler.Game;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;
import fr.lyon1.etu.bigelRouveyre.inter.model.GameResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Player> getLosers() {
        return players.stream().filter(player -> player.getResult().getTime() > 0).collect(Collectors.toList());
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public List<Player> getWinner() {
        return players.stream().filter(player -> player.getResult().getTime() == 0).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        String result = game + "\n Winners :";
        for (Player player : getWinner().stream()
                .sorted(Comparator.comparingInt(player -> player.getResult().getScore()))
                .collect(Collectors.toList()))
            result += "\n\t" + player.getResult();

        result += "\n Losers :";
        for (Player player : getLosers().stream()
                .sorted(Comparator.comparingInt(player -> player.getResult().getTime()))
                .collect(Collectors.toList()))
            result += "\n\t" + player.getResult();

        return result;
    }
}
