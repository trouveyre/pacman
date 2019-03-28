package fr.lyon1.etu.bigelRouveyre.fxImpl.controler;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;

public class LocalPlayer extends BasePlayer {

    //CONSTRUCTORS
    public LocalPlayer(Actor actor, String leftKey, String upKey, String rightKey, String downKey) {
        super(actor);
        getShortcuts().put(leftKey, (coordinates) -> new int[] {coordinates[0], -100});
        getShortcuts().put(upKey, (coordinates) -> new int[] {-100, coordinates[1]});
        getShortcuts().put(rightKey, (coordinates) -> new int[] {coordinates[0], 100});
        getShortcuts().put(downKey, (coordinates) -> new int[] {100, coordinates[1]});
    }

    //FIELDS
    private int[] destination;

    //METHODS
    @Override
    protected void onDrive() {
        if (destination != null)
            getActor().move(destination[0], destination[1]);
    }

    @Override
    public void onClick(double x, double y) {
        if (Math.abs(x) < Math.abs(y)) {
            if (y < 0)
                destination = new int[]{-100, getActor().getColumn()};
            else
                destination = new int[]{100, getActor().getColumn()};
        }
        else {
            if (x < 0)
                destination = new int[] {getActor().getRow(), -100};
            else
                destination = new int[] {getActor().getRow(), 100};
        }

    }

    @Override
    public void onCommand(String command) {
        if (getShortcuts().containsKey(command))
            destination = getShortcuts().get(command).apply(new int[]{getActor().getRow(), getActor().getColumn()});
    }
}
