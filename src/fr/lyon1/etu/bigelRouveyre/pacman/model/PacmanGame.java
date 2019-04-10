package fr.lyon1.etu.bigelRouveyre.pacman.model;

import fr.lyon1.etu.bigelRouveyre.core.controler.BasePlayer;
import fr.lyon1.etu.bigelRouveyre.core.controler.RandomPlayer;
import fr.lyon1.etu.bigelRouveyre.core.model.BaseGame;
import fr.lyon1.etu.bigelRouveyre.core.view.javafx.LocalView;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;
import fr.lyon1.etu.bigelRouveyre.inter.model.Generator;
import fr.lyon1.etu.bigelRouveyre.pacman.controler.PacmanPlayer;

public class PacmanGame extends BaseGame {

    //CONSTRUCTORS
    public PacmanGame(Generator generator, int nbFoods, int nbGhosts, PacmanTheme theme) {
        super();
        this.theme = theme;
        generator.setPrototype(PacmanActor.wall(theme));
        build(generator);
        initiate(new RandomPacmanInitiator(nbFoods, nbGhosts, theme));
    }

    //FIELDS
    private LocalView view = null;
    private PacmanTheme theme;

    //METHODS
    public LocalView getLocalView() {
        return view;
    }

    @Override
    public boolean isFinished() {
        return getBoard().getActors().stream().noneMatch(actor -> actor.getImpact().equals(PacmanImpact.Food)) ||
                getPlayers().stream()
                        .filter(player -> player instanceof PacmanPlayer)
                        .noneMatch(player -> player.getActor().isAlive());
    }

    public RandomPlayer newGhost() {
        return new RandomPlayer(BasePlayer.randomName(),
                PacmanActor.ghost(theme),
                -getBoard().getSizes()[0],
                -getBoard().getSizes()[1],
                getBoard().getSizes()[0]*2,
                getBoard().getSizes()[1]*2
        );
    }

    public PacmanPlayer newPacman(String name, String leftKey, String upKey, String rightKey, String downKey) {
        PacmanPlayer result = new PacmanPlayer(name, leftKey, upKey, rightKey, downKey);
        result.setGame(this);
        result.setActor(PacmanActor.pacman(theme));
        if (view == null) view = (LocalView) result.getView();
        else result.setView(view);
        return result;
    }

    @Override
    public void onEnd() {
        if (getBoard().getActors().stream().noneMatch(actor -> actor.getImpact().equals(PacmanImpact.Food))) {
            getPlayers().stream()
                    .filter(player -> player.getActor().getImpact().equals(PacmanImpact.Pacman))
                    .forEach(player -> getResult().addWinner(player));
            getPlayers().stream()
                    .filter(player -> player.getActor().getImpact().equals(PacmanImpact.Ghost))
                    .forEach(player -> getResult().addLoser(player));
        }
        else {
            getPlayers().stream()
                    .filter(player -> player.getActor().getImpact().equals(PacmanImpact.Pacman))
                    .forEach(player -> getResult().addLoser(player));
            getPlayers().stream()
                    .filter(player -> player.getActor().getImpact().equals(PacmanImpact.Ghost))
                    .forEach(player -> getResult().addWinner(player));
        }
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
