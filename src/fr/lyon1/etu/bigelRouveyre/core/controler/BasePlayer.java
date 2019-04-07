package fr.lyon1.etu.bigelRouveyre.core.controler;

import fr.lyon1.etu.bigelRouveyre.core.model.StandardPlayerResult;
import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;
import fr.lyon1.etu.bigelRouveyre.inter.model.Game;
import fr.lyon1.etu.bigelRouveyre.inter.model.PlayerResult;
import fr.lyon1.etu.bigelRouveyre.inter.view.View;

public abstract class BasePlayer implements Player {

    //CONSTRUCTORS
    public BasePlayer(String name) {
        this.name = name;
    }

    //FIELDS
    private Actor actor;
    private Game game;
    private boolean isAllow = true;
    private String name;
    private PlayerResult result = new StandardPlayerResult(this, 0, 0);
    private View view;

    //METHODS
    @Override
    public void addScore(int points) {
        result = new StandardPlayerResult(this, getScore() + points, game.getTime());
    }

    @Override
    public void allow() {
        isAllow = true;
    }

    @Override
    public void disallow() {
        isAllow = false;
    }

    @Override
    public void drive() {
        if (isAllow)
            onDrive();
    }

    @Override
    public Actor getActor() {
        return actor;
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PlayerResult getResult() {
        return result;
    }

    @Override
    public int getScore() {
        return result.getScore();
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void onBirth(Actor actor) {
        actor.getPicture().draw(view, actor.getCoordinates().get()[1], actor.getCoordinates().get()[0]);
    }

    @Override
    public void onDeath(Actor actor) {
        if (view != null)
            actor.getPicture().clean(view);
    }

    /**
     * Executed if allowed when drive is called.
     */
    protected abstract void onDrive();

    @Override
    public void onMove(Actor actor) {
        actor.getPicture().clean(view);
        actor.getPicture().draw(view, actor.getCoordinates().get()[1], actor.getCoordinates().get()[0]);
    }

    @Override
    public void onPlayerAdded(Player player) {}

    @Override
    public void onPlayerRemoved(Player player) {}

    public static String randomName() {
        return "Player" + (int) (Math.random() * 100);
    }

    @Override
    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
        if (!this.game.getPlayers().contains(this))
            this.game.addPlayer(this);
        if (actor != null)
            actor.beBorn(game.getBoard());
    }

    @Override
    public void setScore(int score) {
        result = new StandardPlayerResult(this, score, game.getTime());
    }

    public void setView(View view) {
        if (this.view != null)
            this.view.removeListeningPlayer(this);
        this.view = view;
        if (this.view != null) {
            this.view.addListeningPlayer(this);
            if (game != null)
                game.getBoard().getActors().stream().filter(actor ->
                        actor.getCoordinates() != null && actor.getPicture() != null
                ).forEach(actor ->
                        actor.getPicture().draw(view, actor.getCoordinates().get()[1], actor.getCoordinates().get()[0])
                );
        }
    }
}
