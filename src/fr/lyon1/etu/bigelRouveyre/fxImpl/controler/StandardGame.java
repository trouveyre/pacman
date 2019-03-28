package fr.lyon1.etu.bigelRouveyre.fxImpl.controler;

import fr.lyon1.etu.bigelRouveyre.fxImpl.view.BoardView;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Game;
import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Board;
import fr.lyon1.etu.bigelRouveyre.inter.model.GameResult;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;
import fr.lyon1.etu.bigelRouveyre.inter.view.View;

import java.util.ArrayList;

public class StandardGame implements Game {

    //CONSTRUCTORS
    public StandardGame(Board board, Player[] players) {
        this.board = board;
        this.players = players;
        view = new BoardView(board.getWidth(), board.getHeight());
        view.setOnKeyTyped((event) -> {
            for (Player player : this.players) player.onCommand(event.getCharacter().charAt(0));
        });
    }

    //FIELDS
    private Board board;
    private ArrayList<Actor> deadActors = new ArrayList();  // TODO
    private volatile boolean hasToEnd = false;
    private volatile boolean isRunning = false;
    private GameResult result = null;
    private Player[] players;
    private Long turnTime = 300L;
    private BoardView view;

    //METHODS
    @Override
    public Board getBoard() { return board; }

    @Override
    public ArrayList<Actor> getDeadActors() { return deadActors; }

    @Override
    public Double getMovementFrequency() { return 1000.0 / turnTime; }
    @Override
    public void setMovementFrequency(double frequency) { turnTime = (long) (1000 / frequency); }

    @Override
    public Player[] getPlayers() { return players; }

    @Override
    public View getView() { return view; }

    @Override
    public boolean isRunning() { return isRunning; }

    @Override
    public final void end() {
        hasToEnd = true;
        isRunning = false;
    }

    @Override
    public final GameResult launch() {
        isRunning = true;
        while (!hasToEnd) {
            try {
                Thread.sleep(500);
                while (isRunning) {
                    try {
                        nextTurn();
                        view.clear();
                        board.getActors().forEach((actor) -> {
                            if (actor.getPicture() != null) actor.getPicture().draw(view, actor.getRow(), actor.getColumn());
                        });
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
        return result;
    }
    /**
     * Executes a turn.
     */
    public void nextTurn() {
        for (Player player : players) { player.drive(); }
    }

    @Override
    public final void pause() { isRunning = false; }
    @Override
    public final void resume() { isRunning = true; }
}
