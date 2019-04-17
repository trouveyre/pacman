package fr.lyon1.etu.bigelRouveyre.core.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.Board;
import fr.lyon1.etu.bigelRouveyre.inter.model.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoDimensionDiggingGenerator extends BaseGenerator {

    //CONSTRUCTORS
    public TwoDimensionDiggingGenerator(int nbRows, int nbColumns) {
        super();
        sizes[0] = nbRows;
        sizes[1] = nbColumns;
    }

    //FIELDS
    private int borderSize = 0;
    private double diggingCoefficient = 0.6;
    private int inertia = 10;
    private int[] sizes = new int[2];

    //METHODS
    @Override
    public final Board build() {
        Board result = new StandardBoard(sizes);

        if (result.getSizes()[0] <= borderSize*2 || result.getSizes()[1] <= borderSize*2) return result;

        for (int i=0;i<result.getSizes()[0];i++) {
            for (int j = 0; j < result.getSizes()[1]; j++)
                getPrototype().clone().beBornAt(result, StandardCoordinates.twoDimensions(i, j));
        }

        freeCases().forEach(freeCase -> result.removeAllAt(freeCase));

        Coordinates location = StandardCoordinates.twoDimensions(
                (int) (Math.random() * (result.getSizes()[1] - 1 - borderSize) + borderSize),
                (int) (Math.random() * (result.getSizes()[0] - 1 - borderSize) + borderSize)
        );
        result.removeAllAt(location);

        Direction2D direction = null;
        ArrayList<Direction2D> directions = new ArrayList<>();
        double threshold = (result.getSizes()[1]-borderSize*2) * (result.getSizes()[0]-borderSize*2) * (1-diggingCoefficient);
        while (result.getActors().size() > threshold) {
            directions.clear();
            directions.addAll(Arrays.asList(Direction2D.values()));
            if (borderSize > 0) {
                if (location.get()[0] <= borderSize || direction == Direction2D.Down)
                    directions.remove(Direction2D.Up);
                if (location.get()[0] >= result.getSizes()[0] - 1 - borderSize || direction == Direction2D.Up)
                    directions.remove(Direction2D.Down);
                if (location.get()[1] <= borderSize || direction == Direction2D.Right)
                    directions.remove(Direction2D.Left);
                if (location.get()[1] >= result.getSizes()[1] - 1 - borderSize || direction == Direction2D.Left)
                    directions.remove(Direction2D.Right);
            }
            for (int c=0;c<inertia;c++) if (directions.contains(direction)) directions.add(direction);

            direction = directions.get((int) (Math.random() * directions.size()));
            Coordinates nextLocation = direction.nextCase(location.get()[0], location.get()[1]);
            if (!wallCases().contains(nextLocation)) {
                location = nextLocation;
                result.removeAllAt(location);
            }
        }

        return result;
    }

    @Override
    public Set<Coordinates> freeCases() {
        return new HashSet<>();
    }

    public int getBorderSize() {
        return borderSize;
    }

    public double getDiggingCoefficient() {
        return diggingCoefficient;
    }

    public int getInertia() {
        return inertia;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    public boolean setDiggingCoefficient(double coefficient) {
        if (coefficient < 1 && coefficient > 0) {
            diggingCoefficient = coefficient;
            return true;
        }
        return false;
    }

    public boolean setInertia(int inertia) {
        if (inertia >= 0) {
            this.inertia = inertia;
            return true;
        }
        return false;
    }

    @Override
    public Set<Coordinates> wallCases() {
        return new HashSet<>();
    }

    //CLASSES
    private enum Direction2D {

        //OBJECTS
        Down {
            @Override
            public Coordinates nextCase(int i, int j) {
                return StandardCoordinates.twoDimensions(i+1, j);
            }
        },
        Left {
            @Override
            public Coordinates nextCase(int i, int j) {
                return StandardCoordinates.twoDimensions(i, j-1);
            }
        },
        Right {
            @Override
            public Coordinates nextCase(int i, int j) {
                return StandardCoordinates.twoDimensions(i, j+1);
            }
        },
        Up {
            @Override
            public Coordinates nextCase(int i, int j) {
                return StandardCoordinates.twoDimensions(i-1, j);
            }
        };

        //METHODS
        public abstract Coordinates nextCase(int i, int j);
    }
}
