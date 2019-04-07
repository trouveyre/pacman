package fr.lyon1.etu.bigelRouveyre.core.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.*;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;

import java.util.*;

public abstract class BaseGame implements Game {

    //CONSTRUCTORS

    //FIELDS
    private Board board;
    private volatile boolean hasToEnd = false;
    private volatile boolean isRunning = false;
    private List<Player> players = new ArrayList<>();
    private GameResult result = new StandardGameResult(this);
    private int time = 0;
    private Long turnTime = 300L;

    //METHODS
    @Override
    public void addPlayer(Player player) {
        if (onPlayerAdded(player)) {
            players.add(player);
            if (player.getGame() != this)
                player.setGame(this);
            if (player.getActor() != null)
                player.getActor().beBorn(getBoard());
            board.getActors().forEach(actor -> actor.addListeningPlayer(player));
            players.forEach(p -> p.onPlayerAdded(player));
        }
    }

    @Override
    public Game build(Generator generator) {
        board = generator.build();
        return this;
    }

    @Override
    public final void end() {
        hasToEnd = true;
        isRunning = false;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Double getMovementFrequency() {
        return 1000.0 / turnTime;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public GameResult getResult() {
        return result;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public Game initiate(Initiator initiator) {
        initiator.initiate(this);
        return this;
    }

    public abstract boolean isFinished();

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public final GameResult launch() {
        isRunning = true;
        while (!(hasToEnd || isFinished())) {
            try {
                Thread.sleep(500);
                while (isRunning && !isFinished()) {
                    try {
                        time ++;
                        onTurn(time);
                        Thread.sleep(turnTime);
                    }
                    catch (InterruptedException ie) {
                        pause();
                        ie.printStackTrace();
                    }
                }
            }
            catch (InterruptedException ie) {
                end();
                ie.printStackTrace();
            }
        }
        onEnd();
        return result;
    }

    /**
     * Called when the game is finishing.
     */
    public abstract void onEnd();

    /**
     * Called when a {@link Player} is added to this.
     * @param player the added {@link Player}
     * @return true if the player can be added
     */
    public abstract boolean onPlayerAdded(Player player);

    /**
     * Called when a {@link Player} is removed from this.
     * @param player the removed {@link Player}
     * @return true if the player can be removed
     */
    public abstract boolean onPlayerRemoved(Player player);

    /**
     * Executes a turn.
     */
    public abstract void onTurn(int time);

    @Override
    public final void pause() {
        isRunning = false;
    }

    @Override
    public void removePlayer(Player player) {
        if (onPlayerRemoved(player)) {
            players.remove(player);
            players.forEach(p -> p.onPlayerRemoved(player));
        }
    }

    @Override
    public final void resume() { isRunning = true; }

    @Override
    public void setMovementFrequency(double frequency) { turnTime = (long) (1000 / frequency); }

    @Override
    public String toString() {
        return "Game (" + players.size() + " players)";
    }
}
