package fr.lyon1.etu.bigelRouveyre.fxImpl.model;

import fr.lyon1.etu.bigelRouveyre.fxImpl.view.ActorPicture;
import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Board;
import fr.lyon1.etu.bigelRouveyre.inter.model.Impact;
import fr.lyon1.etu.bigelRouveyre.inter.model.Moving;
import fr.lyon1.etu.bigelRouveyre.inter.view.Drawable;

public class StandardActor implements Actor, Cloneable {

    //CONSTRUCTORS
    public StandardActor(Board board) {
        this.board = board;
    }

    //FIELDS
    private Board board;
    private int i = -1, j = -1;
    private Impact impact;
    private Moving moving;
    private Drawable picture;

    //METHODS
    @Override
    public StandardActor clone() {
        StandardActor result = null;
        try {
            result = (StandardActor) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void die() {
        getBoard().atCase(getRow(), getColumn(), null);
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public int getColumn() {
        return j;
    }
    @Override
    public int getRow() {
        return i;
    }
    @Override
    public void setColumn(int column) {
        j = column;
    }
    @Override
    public void setRow(int row) {
        i = row;
    }
    @Override
    public void shift(int row, int column) {
        board.atCase(i, j, null);
        board.atCase(row, column, this);
    }

    @Override
    public Impact getImpact() {
        return impact;
    }
    @Override
    public void setImpact(Impact impact) {
        this.impact = impact;
    }
    @Override
    public boolean onContact(Actor actor) {
        return impact.apply(this, actor);
    }

    @Override
    public Moving getMoving() {
        return moving;
    }
    @Override
    public void setMoving(Moving moving) {
        this.moving = moving;
    }
    @Override
    public void move(int row, int column) {
        moving.move(this, row, column);
    }

    @Override
    public Drawable getPicture() { return picture; }
    @Override
    public void setPicture(Drawable picture) { this.picture = picture; }

    public static Actor food(Board board) {
        Actor result = new StandardActor(board);
        result.setPicture(ActorPicture.food());
        result.setMoving(MovingType.Rooted);
        result.setImpact(ImpactType.Feed);
        return result;
    }

    public static Actor ghost(Board board) {
        Actor result = new StandardActor(board);
        result.setPicture(ActorPicture.ghost());
        result.setMoving(MovingType.Forcing);
        result.setImpact(ImpactType.Acid);
        return result;
    }

    public static Actor pacman(Board board) {
        Actor result = new StandardActor(board);
        result.setPicture(ActorPicture.pacman());
        result.setMoving(MovingType.Forcing);
        result.setImpact(ImpactType.Weak);
        return result;
    }

    public static Actor wall(Board board) {
        Actor result = new StandardActor(board);
        result.setPicture(ActorPicture.wall());
        result.setMoving(MovingType.Rooted);
        result.setImpact(ImpactType.Solid);
        return result;
    }
}
