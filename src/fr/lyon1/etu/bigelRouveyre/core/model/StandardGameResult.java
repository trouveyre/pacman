package fr.lyon1.etu.bigelRouveyre.core.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.Game;
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
    private ArrayList<Player> losers = new ArrayList();
    private ArrayList<Player> winners = new ArrayList();

    //METHODS
    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public List<Player> getLosers() {
        return losers;
    }
    @Override
    public void addLoser(Player player) {
        losers.add(player);
    }

    @Override
    public List<Player> getWinners() {
        return winners;
    }
    @Override
    public void addWinner(Player player) {
        winners.add(player);
    }

    @Override
    public String toString() {
        String result = game + "\n Winners :";
        for (Player player : getWinners().stream()
                .sorted(Comparator.comparingInt(player -> player.getScore()))
                .collect(Collectors.toList()))
            result += "\n\t" + player.getResult();

        result += "\n Losers :";
        for (Player player : getLosers().stream()
                .sorted(Comparator.comparingInt(player -> player.getScore()))
                .collect(Collectors.toList()))
            result += "\n\t" + player.getResult();

        return result;
    }
}
