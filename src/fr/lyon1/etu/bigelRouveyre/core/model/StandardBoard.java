package fr.lyon1.etu.bigelRouveyre.core.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.*;

import javax.tools.Diagnostic;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class StandardBoard implements Board {

    public StandardBoard(int[] sizes) {
        this.sizes = sizes;
    }

    //FIELDS
    private HashMap<Coordinates, Set<Actor>> actors = new HashMap<>();
    private HashMap<Actor, Coordinates> locations = new HashMap<>();
    private int[] sizes;

    //METHODS
    @Override
    public void add(Actor actor) {
        List<Coordinates> freeCases = new ArrayList<>(spawns());
        if (!freeCases.isEmpty())
            addAt(freeCases.remove((int) (Math.random()*freeCases.size())), actor);
    }

    @Override
    public void addAt(Coordinates coordinates, Actor actor) {
        if (actor != null) {
            coordinates = asValidCoordinates(coordinates);
            if (actors.get(coordinates) == null)
                actors.put(coordinates, new HashSet<>());
            actors.get(coordinates).add(actor);
            locations.put(actor, coordinates);
        }
    }

    private Coordinates asValidCoordinates(Coordinates coordinates) {
        int[] result = new int[coordinates.get().length];
        for (int i=0; i<result.length; i++) {
            result[i] = (int) ((sizes[i] + coordinates.get()[i] % -sizes[i]) % sizes[i]);
        }
        return new StandardCoordinates(result);
    }

    @Override
    public Set<Actor> atCase(Coordinates coordinates) {
        coordinates = asValidCoordinates(coordinates);
        Set<Actor> result = actors.get(coordinates);
        return result == null? new HashSet<>(): result;
    }

    @Override
    public Set<Actor> atCase(Coordinates coordinates, Set<Actor> newActors) {
        Coordinates validCoordinates = asValidCoordinates(coordinates);
        Set<Actor> result = removeAllAt(validCoordinates);
        newActors.forEach(actor -> addAt(validCoordinates, actor));
        return result;
    }

    @Override
    public Coordinates coordinatesOf(Actor actor) {
        return locations.get(actor);
    }

    @Override
    public Set<Actor> getActors() {
        return locations.keySet();
    }

    @Override
    public int[] getSizes() {
        return sizes;
    }

    @Override
    public Coordinates removeActor(Actor actor) {
        Coordinates result = locations.remove(actor);
        actors.get(result).remove(actor);
        if (actors.get(result).isEmpty())
            actors.remove(result);
        return result;
    }

    @Override
    public Set<Actor> removeAllAt(Coordinates coordinates) {
        coordinates = asValidCoordinates(coordinates);
        Set<Actor> result = actors.remove(coordinates);
        if (result != null)
            result.forEach(actor -> locations.remove(actor));
        return result;
    }

    @Override
    public void removeAt(Coordinates coordinates, Actor actor) {
        coordinates = asValidCoordinates(coordinates);
        if (actors.get(coordinates) != null) {
            actors.get(coordinates).remove(actor);
            if (actors.get(coordinates).isEmpty())
                actors.remove(coordinates);
        }
        locations.remove(actor);
    }

    public void saveAs(String filename) {
        Path map = Paths.get("map/"+filename);
        // TODO
    }

    @Override
    public Set<Coordinates> spawns() {
        HashSet<Coordinates> result = new HashSet<>();
        Coordinates location;
        for (int i=0; i<sizes[0]; i++) {
            for (int j=0; j<sizes[1]; j++) {
                location = StandardCoordinates.twoDimensions(i, j);
                if (atCase(location).isEmpty()) result.add(location);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "+";
        for (int j=0; j<getSizes()[0]; j++)
            result += "-+";
        for (int i=0; i<getSizes()[1]; i++) {
            result += "\n|";
            for (int j=0; j<getSizes()[1]; j++)
                result += (actors.get(StandardCoordinates.twoDimensions(i, j)) == null? " ": "x") + "|";
            result += "\n+";
            for (int j=0; j<getSizes()[0]; j++)
                result += "-+";
        }
        return result;
    }
}
