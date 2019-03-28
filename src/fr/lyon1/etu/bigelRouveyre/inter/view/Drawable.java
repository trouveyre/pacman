package fr.lyon1.etu.bigelRouveyre.inter.view;

/**
 * Represents any element which should be drawn on the screen.
 */
public interface Drawable<V extends View> {

    //METHODS
    /**
     * Draws this on the {@link View} "view" at the coordinates ("x", "y").
     * @param view the view where this is drawn
     * @param x the x coordinates
     * @param y the y coordinates
     */
    void draw(V view, double x, double y);
}
