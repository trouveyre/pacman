package fr.lyon1.etu.bigelRouveyre.core.controler;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;

import java.util.Arrays;

public class HuntingPlayer extends BasePlayer {

    //CONSTRUCTORS
    public HuntingPlayer(String name, Actor actor, Actor... preys) {
        super(name);
        setActor(actor);
        this.preys = preys;
    }

    //FIELDS
    private Actor[] preys;

    //METHODS
    public Actor[] getPreys() {
        return preys;
    }

    @Override
    public void onClick(double x, double y) {}

    @Override
    public void onCommand(String command) {}

    @Override
    protected void onDrive() {
        if (getActor() == null || preys.length <= 0) return;

        Actor prey = Arrays.stream(preys).filter(actor ->
                actor.getCoordinates() != null
        ).reduce(preys[0], (actor1, actor2) -> {
            double distance1 = Math.hypot(
                    actor1.getCoordinates().get()[0] - getActor().getCoordinates().get()[0],
                    actor1.getCoordinates().get()[1] - getActor().getCoordinates().get()[1]
            );
            double distance2 = Math.hypot(
                    actor2.getCoordinates().get()[0] - getActor().getCoordinates().get()[0],
                    actor2.getCoordinates().get()[1] - getActor().getCoordinates().get()[1]
            );
            if (distance1 < distance2) return actor1;
            else return actor2;
        });

        if (prey != null && prey.getCoordinates() != null)
            getActor().move(prey.getCoordinates());
    }

    @Override
    public void onLose() {}

    @Override
    public void onWin() {}

    public void setPreys(Actor[] preys) {
        this.preys = preys;
    }
}
