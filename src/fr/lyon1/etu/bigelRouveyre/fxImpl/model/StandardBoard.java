package fr.lyon1.etu.bigelRouveyre.fxImpl.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StandardBoard implements Board {

    //CONSTRUCTORS
    public StandardBoard(int height, int width) {
        cases = new Case[width][height];
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                cases[i][j] = new StandardCase();
            }
        }
    }

    //FIELDS
    private Case[][] cases;

    //METHODS
    @Override
    public void addAt(int row, int column, Actor actor) {
        if (actor != null) {
            row = asValidRow(row);
            column = asValidColumn(column);
            cases[row][column].addActor(actor);
            actor.setRow(row);
            actor.setColumn(column);
        }
    }

    private int asValidColumn(int column) {
        double width = (double) getWidth();
        return (int) ((width + column % -width) % width);
    }
    private int asValidRow(int row) {
        double height = (double) getHeight();
        return (int) ((height + row % -height) % height);
    }

    @Override
    public List<Actor> atCase(int row, int column) {
        row = asValidRow(row);
        column = asValidColumn(column);
        return cases[row][column].getActors();
    }
    @Override
    public List<Actor> atCase(int row, int column, Collection<Actor> newActors) {
        row = asValidRow(row);
        column = asValidColumn(column);
        List<Actor> result = cases[row][column].getActors();
        cases[row][column].setActors(newActors);
        return result;
    }

    @Override
    public Board build(Generator generator) {
        return generator.build(this);
    }

    @Override
    public Set<Actor> getActors() {
        HashSet<Actor> result = new HashSet();
        for (Case[] fewCases : cases) {
            for (Case aCase : fewCases)
                result.addAll(aCase.getActors());
        }
        return result;
    }

    @Override
    public Case[][] getCases() {
        return cases.clone();
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

    @Override
    public void removeAt(int row, int column, Actor actor) {
        row = asValidRow(row);
        column = asValidColumn(column);
        cases[row][column].removeActor(actor);
    }

    @Override
    public List<Actor> removeAllAt(int row, int column) {
        row = asValidRow(row);
        column = asValidColumn(column);
        return cases[row][column].empty();
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
                result += (atCase(i, j) == null? " ": "x") + "|";
            result += "\n+";
            for (int j=0; j<getHeight(); j++)
                result += "-+";
        }
        return result;
    }
}
