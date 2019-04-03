package fr.lyon1.etu.bigelRouveyre.inter.view;

import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;

import java.util.List;

/**
 * Marks a class as able to be painted with {@link Drawable}s.
 */
public interface View {

    //METHODS
    /**
     * Clears this.
     */
    void clear();

    /**
     * Closes this.
     */
    void close();

    /**
     * Opens this {@link View}
     */
    void open();

    /**
     * @return the {@link Player}s listenning to this
     */
    List<Player> getListeningPlayers();
    /**
     * @param player a {@link Player} that has to listen to this
     */
    void addListeningPlayer(Player player);
    /**
     * @param player a {@link Player} that has no longer to listen to this
     */
    void removeListeningPlayer(Player player);
}
