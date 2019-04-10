package fr.lyon1.etu.bigelRouveyre.pacman.view.javafx;

import fr.lyon1.etu.bigelRouveyre.core.view.javafx.ActorPicture;
import fr.lyon1.etu.bigelRouveyre.pacman.model.PacmanTheme;
import javafx.scene.image.Image;

public class PacmanPicture extends ActorPicture {

    public static PacmanPicture food(PacmanTheme theme) {
        return new PacmanPicture(theme.getFoodImage());
    }

    public static PacmanPicture ghost(PacmanTheme theme) {
        return new PacmanPicture(theme.getRandomGhostImage());
    }

    public static PacmanPicture pacman(PacmanTheme theme) {
        return new PacmanPicture(theme.getPacmanImage());
    }

    public static PacmanPicture wall(PacmanTheme theme) {
        return new PacmanPicture(theme.getWallImage());
    }

    //CONSTRUCTORS
    public PacmanPicture(String asset) {
        super(asset);
    }
    public PacmanPicture(Image image) {
        super(image);
    }

    //FIELDS

    //METHODS
}
