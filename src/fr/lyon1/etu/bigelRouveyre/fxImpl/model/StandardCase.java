package fr.lyon1.etu.bigelRouveyre.fxImpl.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Case;

import java.util.ArrayList;
import java.util.List;

public class StandardCase implements Case {

    //FIELDS
    private ArrayList<Actor> actors = new ArrayList();

    //METHODS
    @Override
    public Actor getActor() {
        if (!actors.isEmpty())
            return actors.get(0);
        else
            return null;
    }

    @Override
    public List<Actor> getAllActor() {
        return actors;
    }

    @Override
    public void putActor(Actor actor) {
        if (actor == null && !actors.isEmpty())
            actors.remove(0);
        else
            actors.add(actors.size(), actor);
    }
}
