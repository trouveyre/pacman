package fr.lyon1.etu.bigelRouveyre.pacman.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.*;

import java.util.ArrayList;

public class RandomPacmanInitiator implements Initiator<PacmanGame> {

    //CONSTRUCTORS
    public RandomPacmanInitiator(int nbPoints, int nbGhosts, PacmanTheme theme) {
        this.nbPoints = nbPoints;
        this.nbGhosts = nbGhosts;
        this.theme = theme;
    }

    //FIELDS
    private int nbGhosts;
    private int nbPoints;
    private PacmanTheme theme;

    //METHODS
    @Override
    public void initiate(PacmanGame game) {
        for (int i=0; i<nbGhosts; i++)
            game.addPlayer(game.newGhost());

        ArrayList<Actor> actors = new ArrayList<>();
        game.getPlayers().forEach(player -> actors.add(player.getActor()));
        for (int i=0; i<nbPoints; i++)
            actors.add(PacmanActor.food(theme));

        ArrayList<Coordinates> spawnCases = new ArrayList<>(game.getBoard().spawns());

        Coordinates position;
        while (!(actors.isEmpty() || spawnCases.isEmpty())) {
            position = spawnCases.remove((int) (Math.random()*spawnCases.size()));
            actors.remove(0).beBornAt(game.getBoard(), position);
        }
    }
}
