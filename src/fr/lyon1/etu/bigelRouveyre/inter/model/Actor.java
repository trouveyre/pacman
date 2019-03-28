package fr.lyon1.etu.bigelRouveyre.inter.model;

import fr.lyon1.etu.bigelRouveyre.inter.view.Drawable;

/**
 * The top level interface for any object on a {@link Board}.
 * @see Board
 * @see Impact
 * @see Moving
 */
public interface Actor {

    Actor clone();

    /**
     * Kills this.
     */
    void die();

    /**
     * @return true if this {@link Actor} is dead.
     */
    boolean isDead();

    /**
     * @return the {@link Board} where this is
     */
    Board getBoard();
    /**
     * @return the column where this is
     */
    int getColumn();
    /**
     * @return the row where this is
     */
    int getRow();
    /**
     * Shifts this at the new column 'column'.
     * @param column the new column where place this
     */
    void setColumn(int column);
    /**
     * Shifts this at the new row 'row'.
     * @param row
     */
    void setRow(int row);
    /**
     * Shifts this at the new location ('row', 'column').
     * @param row the new row where place this
     * @param column the new column where place this
     */
    void shift(int row, int column);

    /**
     * @return the {@link Impact} that this has when meeting another {@link Actor}
     */
    Impact getImpact();
    /**
     * Sets the {@link Impact} that this has when meeting another {@link Actor}
     * @param impact the new {@link Impact}
     */
    void setImpact(Impact impact);
    /**
     * Applies the {@link Impact}.
     * @param actor the {@link Actor} met
     * @return true if "actor" can still move else false
     */
    boolean onContact(Actor actor);

    /**
     * @return the {@link Moving} used by this
     */
    Moving getMoving();
    /**
     * @param movement the new {@link Moving} this will use
     */
    void setMoving(Moving movement);
    /**
     * Moves this toward the case ('row', 'column') according to this' moving field.
     * @param row the row toward moving
     * @param column the column toward moving
     */
    void move(int row, int column);

    /**
     * @return the {@link Drawable} used to draw this
     */
    Drawable getPicture();
    /**
     * Sets this' picture.
     * @param picture the new {@link Drawable} used to draw this
     */
    void setPicture(Drawable picture);
}
