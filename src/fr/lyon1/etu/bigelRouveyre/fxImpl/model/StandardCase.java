package fr.lyon1.etu.bigelRouveyre.fxImpl.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Case;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StandardCase implements Case {

    //FIELDS
    private ArrayList<Actor> actors = new ArrayList();

    //METHODS
    @Override
    public void addActor(Actor actor) {
        actors.add(actor);
    }

    @Override
    public List<Actor> empty() {
        ArrayList<Actor> result = (ArrayList<Actor>) actors.clone();
        actors.clear();
        return result;
    }

    @Override
    public Actor getFirstActor() {
        if (!actors.isEmpty())
            return actors.get(0);
        return null;
    }

    @Override
    public List<Actor> getActors() {
        return (List<Actor>) actors.clone();
    }

    @Override
    public void setActors(Collection<Actor> actors) {
        this.actors.clear();
        if (actors != null)
            actors.addAll(actors);
    }

    @Override
    public void removeActor(Actor actor) {
        if (actor != null) {
            actors.remove(actor);
        }
    }
}
