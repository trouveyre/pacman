package fr.lyon1.etu.bigelRouveyre.core.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.Coordinates;

public class StandardCoordinates implements Coordinates {

    public static StandardCoordinates twoDimensions(int i, int j) {
        return new StandardCoordinates(new int[] {i, j});
    }

    //CONSTRUCTORS
    public StandardCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    //FIELDS
    private int[] coordinates;

    //METHODS
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinates) {
            Coordinates c = (Coordinates) obj;
            if (coordinates.length == c.get().length) {
                for (int i = 0; i < coordinates.length; i++) {
                    if (coordinates[i] != c.get()[i])
                        return false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int[] get() {
        return coordinates;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int i=0; i<coordinates.length; i++) result += coordinates[i] * Math.pow(1000, i);
        return result;
    }

    @Override
    public String toString() {
        String result = "(";
        for (int i=0; i<coordinates.length-1; i++) result += coordinates[i]+", ";
        return result + coordinates[coordinates.length-1]+")";
    }
}
