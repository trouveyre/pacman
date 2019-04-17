package fr.lyon1.etu.bigelRouveyre.core.controler;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;

import java.util.Arrays;
import java.util.List;

public class HuntingPlayer extends BasePlayer {

    //CONSTRUCTORS
    public HuntingPlayer(String name, Actor actor, Actor... preys) {
        super(name);
        setActor(actor);
        this.preys = Arrays.asList(preys);
    }

    //FIELDS
    private List<Actor> preys;

    //METHODS
    private double distanceFrom(Actor actor) {
        double result = 100;
        if (actor.getCoordinates() != null)
            result = Math.hypot(
                    actor.getCoordinates().get()[0] - getActor().getCoordinates().get()[0],
                    actor.getCoordinates().get()[1] - getActor().getCoordinates().get()[1]
            );
        return result;
    }

    public List<Actor> getPreys() {
        return preys;
    }

    @Override
    public void onClick(double x, double y) {}

    @Override
    public void onCommand(String command) {}

    @Override
    public void onDeath(Actor actor) {
        super.onDeath(actor);
        preys.remove(actor);
    }

    @Override
    protected void onDrive() {
        if (getActor() == null || preys.size() <= 0) return;

        Actor prey = preys.stream().reduce(preys.get(0), (actor1, actor2) -> {
            if (distanceFrom(actor1) < distanceFrom(actor2))
                return actor1;
            else
                return actor2;
        });

        if (prey != null && prey.getCoordinates() != null)
            getActor().move(prey.getCoordinates());
    }

    @Override
    public void onLose() {}

    @Override
    public void onWin() {}

    public void setPreys(List<Actor> preys) {
        this.preys = preys;
    }
}
