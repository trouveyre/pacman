package fr.lyon1.etu.bigelRouveyre.fxImpl.controler;

import fr.lyon1.etu.bigelRouveyre.fxImpl.model.ImpactType;
import fr.lyon1.etu.bigelRouveyre.fxImpl.model.StandardGameResult;
import fr.lyon1.etu.bigelRouveyre.fxImpl.model.StandardPlayerResult;
import fr.lyon1.etu.bigelRouveyre.fxImpl.view.ActorPicture;
import fr.lyon1.etu.bigelRouveyre.fxImpl.view.BoardView;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Game;
import fr.lyon1.etu.bigelRouveyre.inter.model.Board;
import fr.lyon1.etu.bigelRouveyre.inter.model.GameResult;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;
import fr.lyon1.etu.bigelRouveyre.inter.model.PlayerResult;
import fr.lyon1.etu.bigelRouveyre.inter.view.View;

import java.util.*;

public class StandardGame implements Game {

    //CONSTRUCTORS
    public StandardGame(Board board, Player[] players) {
        this.board = board;
        this.players = new ArrayList();
        for (Player player : players) this.players.add(player);
        view = new BoardView(board.getWidth(), board.getHeight());
        view.setOnKeyTyped(event ->
            this.players.stream().filter(player -> !player.getActor().isDead()).forEach(player ->
                player.onCommand(event.getCharacter())
            )
        );
        view.setOnMouseClicked(event ->
                this.players.stream().filter(player -> !player.getActor().isDead()).forEach(player ->
                        player.onClick(
                                event.getX() - board.getWidth() * (double) ActorPicture.SIZE / 2,
                                event.getY() - board.getHeight() * (double) ActorPicture.SIZE / 2
                        )
                )
        );
    }

    //FIELDS
    private Board board;
    private HashSet<PlayerResult> playerResults = new HashSet();
    private volatile boolean hasToEnd = false;
    private volatile boolean isRunning = false;
    private GameResult result = new StandardGameResult(this);
    private ArrayList<Player> players;
    private int time = 0;
    private Long turnTime = 300L;
    private BoardView view;

    //METHODS
    @Override
    public Board getBoard() { return board; }

    @Override
    public Double getMovementFrequency() { return 1000.0 / turnTime; }
    @Override
    public void setMovementFrequency(double frequency) { turnTime = (long) (1000 / frequency); }

    @Override
    public List<Player> getPlayers() { return (List<Player>) players.clone(); }
    @Override
    public void addPlayer(Player player) {
        // TODO
    }
    @Override
    public void removePlayer(Player player) {
        // TODO
    }

    @Override
    public View getView() { return view; }

    @Override
    public boolean isRunning() { return isRunning; }

    @Override
    public final void end() {
        hasToEnd = true;
        isRunning = false;
    }

    public boolean isWonOrLost() {
        return board.getActors().stream().noneMatch(actor -> actor.getImpact() == ImpactType.Feed) ||
                players.stream().noneMatch(player -> player instanceof LocalPlayer);
    }
    @Override
    public final GameResult launch() {
        isRunning = true;
        while (!(hasToEnd || isWonOrLost())) {
            try {
                Thread.sleep(500);
                while (isRunning && !isWonOrLost()) {
                    try {
                        time ++;
                        nextTurn();
                        view.clear();
                        board.getActors().forEach((actor) -> {
                            if (actor.getPicture() != null)
                                actor.getPicture().draw(view, actor.getColumn(), actor.getRow());
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
        view.close();
        return result;
    }
    /**
     * Executes a turn.
     */
    public void nextTurn() {
        players.stream().filter(player -> player.getActor().isDead()).forEach(player -> {
            player.setResult(new StandardPlayerResult(player, 0, time));
            result.getPlayers().add(player);
        });
        result.getPlayers().forEach(player -> players.remove(player));
        players.forEach(player -> player.drive());
    }

    @Override
    public final void pause() { isRunning = false; }
    @Override
    public final void resume() { isRunning = true; }
}
