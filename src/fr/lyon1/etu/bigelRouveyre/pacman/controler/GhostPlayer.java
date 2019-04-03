package fr.lyon1.etu.bigelRouveyre.pacman.controler;

import fr.lyon1.etu.bigelRouveyre.core.controler.BasePlayer;
import fr.lyon1.etu.bigelRouveyre.core.controler.RandomPlayer;
import fr.lyon1.etu.bigelRouveyre.inter.model.Game;
import fr.lyon1.etu.bigelRouveyre.pacman.model.PacmanActor;

public class GhostPlayer extends RandomPlayer {

    //CONSTRUCTORS
    public GhostPlayer(Game game) {
        super(
                BasePlayer.randomName(),
                game,
                PacmanActor.ghost(),
                0,
                0,
                game.getBoard().getSizes()[0],
                game.getBoard().getSizes()[0]
        );
    }

    //FIELDS

    //METHODS
}
