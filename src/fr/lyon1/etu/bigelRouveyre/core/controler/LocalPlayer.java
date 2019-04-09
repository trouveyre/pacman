package fr.lyon1.etu.bigelRouveyre.core.controler;

import fr.lyon1.etu.bigelRouveyre.core.model.StandardCoordinates;
import fr.lyon1.etu.bigelRouveyre.core.view.javafx.ActorPicture;
import fr.lyon1.etu.bigelRouveyre.core.view.javafx.LocalView;
import fr.lyon1.etu.bigelRouveyre.inter.model.Coordinates;
import fr.lyon1.etu.bigelRouveyre.inter.model.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LocalPlayer extends BasePlayer {

    //CONSTRUCTORS
    public LocalPlayer(String name, String leftKey, String upKey, String rightKey, String downKey) {
        super(name);
        shortcuts = new HashMap<>();
        shortcuts.put(leftKey, (coordinates) -> StandardCoordinates.twoDimensions(coordinates.get()[0], -100));
        shortcuts.put(upKey, (coordinates) -> StandardCoordinates.twoDimensions(-100, coordinates.get()[1]));
        shortcuts.put(rightKey, (coordinates) -> StandardCoordinates.twoDimensions(coordinates.get()[0], 100));
        shortcuts.put(downKey, (coordinates) -> StandardCoordinates.twoDimensions(100, coordinates.get()[1]));
    }

    //FIELDS
    private Coordinates destination;
    private Map<String, Function<Coordinates, Coordinates>> shortcuts;

    //METHODS
    /**
     * The players shortcuts.
     * @return a map with {@link Character} as key and coordinates of case to move as value.
     */
    public Map<String, Function<Coordinates, Coordinates>> getShortcuts() {
        return shortcuts;
    }

    @Override
    public void onClick(double x, double y) {
        x  -= getGame().getBoard().getSizes()[0] * (double) ActorPicture.SIZE / 2;
        y  -= getGame().getBoard().getSizes()[1] * (double) ActorPicture.SIZE / 2;
        if (Math.abs(x) < Math.abs(y)) {
            if (y < 0)
                destination = StandardCoordinates.twoDimensions(-100, getActor().getCoordinates().get()[1]);
            else
                destination = StandardCoordinates.twoDimensions(100, getActor().getCoordinates().get()[1]);
        }
        else {
            if (x < 0)
                destination = StandardCoordinates.twoDimensions(getActor().getCoordinates().get()[0], -100);
            else
                destination = StandardCoordinates.twoDimensions(getActor().getCoordinates().get()[0], 100);
        }
    }

    @Override
    public void onCommand(String command) {
        if (getShortcuts().containsKey(command) && getActor().isAlive())
            destination = getShortcuts().get(command).apply(StandardCoordinates.twoDimensions(
                    getActor().getCoordinates().get()[0],
                    getActor().getCoordinates().get()[1]
            ));
    }

    @Override
    protected void onDrive() {
        if (destination != null)
            getActor().move(destination);
    }

    @Override
    public void onLose() {
        getView().close();
    }

    @Override
    public void onWin() {
        getView().close();
    }

    @Override
    public void setGame(Game game) {
        super.setGame(game);
        setView(new LocalView(game.getBoard().getSizes()[0], game.getBoard().getSizes()[1]));
    }
}
