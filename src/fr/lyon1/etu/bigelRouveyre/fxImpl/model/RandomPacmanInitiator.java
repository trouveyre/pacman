package fr.lyon1.etu.bigelRouveyre.fxImpl.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Board;
import fr.lyon1.etu.bigelRouveyre.inter.model.Initiator;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;

import java.util.ArrayList;

public class RandomPacmanInitiator implements Initiator {

    //CONSTRUCTORS
    public RandomPacmanInitiator(Player[] players, int nbPoints) {
        this.players = players;
        this.nbPoints = nbPoints;
    }

    //FIELDS
    Player[] players;
    int nbPoints;

    //METHODS

    @Override
    public Board initiate(Board board) {
        ArrayList<Actor> actors = new ArrayList();
        for (Player player : players)
            actors.add(player.getActor());

        for (int i=0; i<nbPoints; i++)
            actors.add(StandardActor.food(board));

        ArrayList<int[]> freeCases = new ArrayList();
        for (int i=0; i<board.getHeight(); i++) {
            for (int j=0; j<board.getWidth(); j++)
                if (board.atCase(i, j) == null) freeCases.add(new int[]{i, j});
        }

        while (!actors.isEmpty() || freeCases.isEmpty()) {
            int[] position = freeCases.remove((int) (Math.random()*freeCases.size()));
            board.atCase(position[0], position[1], actors.remove(0));
        }

        return board;
    }
}
