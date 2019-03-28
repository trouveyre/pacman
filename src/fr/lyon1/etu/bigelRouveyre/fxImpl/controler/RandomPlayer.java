package fr.lyon1.etu.bigelRouveyre.fxImpl.controler;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;

public class RandomPlayer extends BasePlayer {

    public final static double ACTING_REDEFINING_COEFFICIENT = 0.6;

    //CONSTRUCTORS
    public RandomPlayer(
            Actor actor,
            int xLeftTopActionZoneCorner,
            int yLeftTopActionZoneCorner,
            int xRightBottomActionZoneCorner,
            int yRightBottomActionZoneCorner
    ) {
        super(actor);
        actionZone[0] = xLeftTopActionZoneCorner;
        actionZone[1] = yLeftTopActionZoneCorner;
        actionZone[2] = xRightBottomActionZoneCorner - actionZone[0];
        actionZone[3] = yRightBottomActionZoneCorner - actionZone[1];
        destination = random();
    }

    //FIELDS
    private int[] destination;
    private int[] actionZone = new int[4];

    //METHODS
    @Override
    public void onDrive() {
        if (Math.random() > ACTING_REDEFINING_COEFFICIENT)
            destination = random();
        getActor().move(destination[0], destination[1]);
    }

    protected int[] random() {
        return new int[] {
                (int) (Math.random() * actionZone[2] + actionZone[0]),
                (int) (Math.random() * actionZone[3] + actionZone[1])
        };
    }

    @Override
    public void onClick(double x, double y) {}

    @Override
    public void onCommand(String command) {}
}
