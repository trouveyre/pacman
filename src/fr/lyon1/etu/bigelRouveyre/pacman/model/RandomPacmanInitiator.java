package fr.lyon1.etu.bigelRouveyre.pacman.model;

import fr.lyon1.etu.bigelRouveyre.core.model.StandardCoordinates;
import fr.lyon1.etu.bigelRouveyre.inter.model.*;
import fr.lyon1.etu.bigelRouveyre.pacman.controler.GhostPlayer;

import java.util.ArrayList;

public class RandomPacmanInitiator implements Initiator {

    //CONSTRUCTORS
    public RandomPacmanInitiator(int nbPoints, int nbGhosts) {
        this.nbPoints = nbPoints;
        this.nbGhosts = nbGhosts;
    }

    //FIELDS
    private int nbGhosts;
    private int nbPoints;

    //METHODS

    @Override
    public void initiate(Game game) {
        for (int i=0; i<nbGhosts; i++)
            game.addPlayer(new GhostPlayer(game));

        ArrayList<Actor> actors = new ArrayList<>();
        game.getPlayers().forEach(player -> actors.add(player.getActor()));
        for (int i=0; i<nbPoints; i++)
            actors.add(PacmanActor.food());

        ArrayList<Coordinates> freeCases = new ArrayList<>();
        Coordinates location;
        for (int i=0; i<game.getBoard().getSizes()[0]; i++) {
            for (int j=0; j<game.getBoard().getSizes()[1]; j++) {
                location = StandardCoordinates.twoDimensions(i, j);
                if (game.getBoard().atCase(location).isEmpty()) freeCases.add(location);
            }
        }

        Coordinates position;
        while (!(actors.isEmpty() || freeCases.isEmpty())) {
            position = freeCases.remove((int) (Math.random()*freeCases.size()));
            actors.remove(0).beBornAt(game.getBoard(), position);
        }
    }
}
