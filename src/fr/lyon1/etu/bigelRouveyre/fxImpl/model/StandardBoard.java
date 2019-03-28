package fr.lyon1.etu.bigelRouveyre.fxImpl.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class StandardBoard implements Board {

    public StandardBoard(int height, int width) {
        cases = new Case[width][height];
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                cases[i][j] = new StandardCase();
            }
        }
    }

    private Case[][] cases;

    @Override
    public Actor atCase(int row, int column) {
        if (row >= 0 && row < getWidth() && column >= 0 && column < getHeight())
            return cases[row][column].getActor();
        return null;
    }
    @Override
    public Actor atCase(int row, int column, Actor newActor) {
        Actor result = null;
        if (row >= 0 && row < getWidth() && column >= 0 && column < getHeight()) {
            result = cases[row][column].getActor();
            if (result != null) {
                result.setRow(-1);
                result.setColumn(-1);
            }
            cases[row][column].putActor(newActor);
            if (newActor != null) {
                newActor.setRow(row);
                newActor.setColumn(column);
            }
        }
        return result;
    }

    @Override
    public Board build(Generator generator) {
        return generator.build(this);
    }

    @Override
    public Set<Actor> getActors() {
        HashSet<Actor> result = new HashSet();
        for (Case[] cases : cases) {
            for (Case aCase : cases)
                result.addAll(aCase.getAllActor());
        }
        result.remove(null);
        return result;
    }

    @Override
    public Case[][] getCases() {
        return cases;
    }

    @Override
    public int getHeight() {
        if (cases.length > 0) return cases[0].length;
        else return 0;
    }
    @Override
    public int getWidth() {
        return cases.length;
    }

    @Override
    public Board initiate(Initiator initiator) {
        return initiator.initiate(this);
    }

    public void saveAs(String filename) {
        Path map = Paths.get("map/"+filename);

    }

    @Override
    public String toString() {
        String result = "+";
        for (int j=0; j<getHeight(); j++)
            result += "-+";
        for (int i=0; i<getWidth(); i++) {
            result += "\n|";
            for (int j=0; j<getHeight(); j++)
                result += (cases[i][j] == null? " ": "x") + "|";
            result += "\n+";
            for (int j=0; j<getHeight(); j++)
                result += "-+";
        }
        return result;
    }
}
