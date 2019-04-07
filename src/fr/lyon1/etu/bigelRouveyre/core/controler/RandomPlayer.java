package fr.lyon1.etu.bigelRouveyre.core.controler;

import fr.lyon1.etu.bigelRouveyre.core.model.StandardCoordinates;
import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Coordinates;
import fr.lyon1.etu.bigelRouveyre.inter.model.Game;
import fr.lyon1.etu.bigelRouveyre.inter.view.View;

public class RandomPlayer extends BasePlayer {

    public final static double ACTING_REDEFINING_COEFFICIENT = 0.6;

    //CONSTRUCTORS
    public RandomPlayer(String name, Game game, Actor actor,
            int xLeftTopActionZoneCorner,
            int yLeftTopActionZoneCorner,
            int xRightBottomActionZoneCorner,
            int yRightBottomActionZoneCorner
    ) {
        super(name);
        setActor(actor);
        actionZone[0] = xLeftTopActionZoneCorner;
        actionZone[1] = yLeftTopActionZoneCorner;
        actionZone[2] = xRightBottomActionZoneCorner - actionZone[0];
        actionZone[3] = yRightBottomActionZoneCorner - actionZone[1];
        destination = random();
    }

    //FIELDS
    private Coordinates destination;
    private int[] actionZone = new int[4];

    //METHODS
    @Override
    public View getView() {
        return null;
    }

    @Override
    public void onDrive() {
        if (Math.random() > ACTING_REDEFINING_COEFFICIENT)
            destination = random();
        getActor().move(destination);
    }

    protected Coordinates random() {
        return StandardCoordinates.twoDimensions(
                (int) (Math.random() * actionZone[2] + actionZone[0]),
                (int) (Math.random() * actionZone[3] + actionZone[1])
        );
    }

    @Override
    public void onClick(double x, double y) {}

    @Override
    public void onCommand(String command) {}
}
