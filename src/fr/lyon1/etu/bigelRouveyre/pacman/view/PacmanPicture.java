package fr.lyon1.etu.bigelRouveyre.pacman.view;

import fr.lyon1.etu.bigelRouveyre.core.view.javafx.ActorPicture;
import javafx.scene.image.Image;

public class PacmanPicture extends ActorPicture {

    public static final Image IMAGE_FOOD = new Image("asset/block.png", SIZE, SIZE, true, true);
    public static final Image IMAGE_GHOST = new Image("asset/Boo_block.png", SIZE, SIZE, true, true);
    public static final Image IMAGE_PACMAN = new Image("asset/pacman.png", SIZE, SIZE, true, true);

    public static ActorPicture food() {
        return new ActorPicture(IMAGE_FOOD);
    }

    public static ActorPicture ghost() {
        return new ActorPicture(IMAGE_GHOST);
    }

    public static ActorPicture pacman() {
        return new ActorPicture(IMAGE_PACMAN);
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
