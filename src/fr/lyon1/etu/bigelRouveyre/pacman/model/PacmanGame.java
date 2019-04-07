package fr.lyon1.etu.bigelRouveyre.pacman.model;

import fr.lyon1.etu.bigelRouveyre.core.model.BaseGame;
import fr.lyon1.etu.bigelRouveyre.core.controler.LocalPlayer;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;
import fr.lyon1.etu.bigelRouveyre.inter.model.Generator;

public class PacmanGame extends BaseGame {

    //CONSTRUCTORS
    public PacmanGame(Generator generator, int nbFoods, int nbGhosts) {
        super();
        build(generator);
        initiate(new RandomPacmanInitiator(nbFoods, nbGhosts));
    }

    //FIELDS

    //METHODS
    @Override
    public boolean isFinished() {
        return getBoard().getActors().stream().noneMatch(actor -> actor.getImpact() == PacmanImpact.Food) ||
                getPlayers().stream()
                        .filter(player -> player instanceof LocalPlayer)
                        .noneMatch(player -> player.getActor().isAlive());
    }

    @Override
    public void onEnd() {
        getPlayers().stream().filter(player -> player.getView() != null).forEach(player -> player.getView().close());
    }

    @Override
    public boolean onPlayerAdded(Player player) {
        return true;
    }

    @Override
    public boolean onPlayerRemoved(Player player) {
        return true;
    }

    public void onTurn(int time) {
        getPlayers().forEach(Player::drive);
    }
}
