package fr.lyon1.etu.bigelRouveyre.fxImpl.controler;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;

public class LocalPlayer extends BasePlayer {

    //CONSTRUCTORS
    public LocalPlayer(Actor actor, Character leftKey, Character upKey, Character rightKey, Character downKey) {
        super(actor);
        getShortcuts().put(leftKey, (coordinates) -> new int[] {-100, coordinates[1]});
        getShortcuts().put(upKey, (coordinates) -> new int[] {coordinates[0], -100});
        getShortcuts().put(rightKey, (coordinates) -> new int[] {100, coordinates[1]});
        getShortcuts().put(downKey, (coordinates) -> new int[] {coordinates[0], 100});
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
    public void onCommand(Character command) {
        if (getShortcuts().containsKey(command))
            destination = getShortcuts().get(command).apply(new int[]{getActor().getRow(), getActor().getColumn()});
    }
}
