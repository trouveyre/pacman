package fr.lyon1.etu.bigelRouveyre.fxImpl.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Board;
import fr.lyon1.etu.bigelRouveyre.inter.model.Generator;

import java.util.ArrayList;

public class DiggingGenerator implements Generator {

    //FIELDS
    private int borderSize = 0;
    private double diggingCoefficient = 0.4;
    private int inertia = 10;

    //METHODS
    @Override
    public Board build(Board board) {
        if (board.getHeight() <= borderSize*2 || board.getWidth() <= borderSize*2) return board;

        Actor actor;
        for (int i=0;i<board.getHeight();i++)
            for (int j=0;j<board.getWidth();j++)
                board.addAt(i, j, StandardActor.wall(board));

        int i = (int) (Math.random() * (board.getWidth()-1-borderSize) + borderSize);
        int j = (int) (Math.random() * (board.getHeight()-1-borderSize) + borderSize);
        board.atCase(i, j, null);

        Direction direction = null;
        ArrayList<Direction> directions = new ArrayList();
        double threshold = (board.getWidth()-borderSize*2) * (board.getHeight()-borderSize*2) * (1-diggingCoefficient);
        while (board.getActors().size() > threshold) {

            directions.clear();
            for (Direction value : Direction.values()) directions.add(value);
            if (borderSize > 0) {
                if (i <= borderSize || direction == Direction.Down)
                    directions.remove(Direction.Up);
                if (i >= board.getHeight() - 1 - borderSize || direction == Direction.Up)
                    directions.remove(Direction.Down);
                if (j <= borderSize || direction == Direction.Right)
                    directions.remove(Direction.Left);
                if (j >= board.getWidth() - 1 - borderSize || direction == Direction.Left)
                    directions.remove(Direction.Right);
            }
            for (int c=0;c<inertia;c++) if (directions.contains(direction)) directions.add(direction);

            direction = directions.get((int) (Math.random() * directions.size()));
            i = direction.nextCase(i, j)[0];
            j = direction.nextCase(i, j)[1];
            board.atCase(i, j, null);
        }

        return board;
    }

    public int getBorderSize() {
        return borderSize;
    }
    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    public double getDiggingCoefficient() {
        return diggingCoefficient;
    }
    public boolean setDiggingCoefficient(double coefficient) {
        if (coefficient < 1 && coefficient > 0) {
            diggingCoefficient = coefficient;
            return true;
        }
        return false;
    }

    public int getInertia() {
        return inertia;
    }
    public boolean setInertia(int inertia) {
        if (inertia >= 0) {
            this.inertia = inertia;
            return true;
        }
        return false;
    }

    //CLASSES
    private enum Direction {

        //OBJECTS
        Down {
            @Override
            public int[] nextCase(int i, int j) {
                return new int[] {i+1, j};
            }
        },
        Left {
            @Override
            public int[] nextCase(int i, int j) {
                return new int[] {i, j-1};
            }
        },
        Right {
            @Override
            public int[] nextCase(int i, int j) {
                return new int[] {i, j+1};
            }
        },
        Up {
            @Override
            public int[] nextCase(int i, int j) {
                return new int[] {i-1, j};
            }
        };

        //METHODS
        public abstract int[] nextCase(int i, int j);
    }
}
