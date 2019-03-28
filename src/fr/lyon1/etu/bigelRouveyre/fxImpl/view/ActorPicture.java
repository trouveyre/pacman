package fr.lyon1.etu.bigelRouveyre.fxImpl.view;

import fr.lyon1.etu.bigelRouveyre.inter.view.Drawable;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ActorPicture extends ImageView implements Drawable<BoardView>, Cloneable {

    public static final int SIZE = 52;

    public static final Image IMAGE_FOOD = new Image("asset/block.png", SIZE, SIZE, true, true);
    public static final Image IMAGE_GHOST = new Image("asset/Boo_block.png", SIZE, SIZE, true, true);
    public static final Image IMAGE_PACMAN = new Image("asset/pacman.png", SIZE, SIZE, true, true);
    public static final Image IMAGE_WALL = new Image("asset/wall_black.png", SIZE, SIZE, true, true);

    //CONSTRUCTORS
    public ActorPicture(String asset) {
        this(new Image(asset, SIZE, SIZE, true, true));
    }
    public ActorPicture(Image image) {
        super();
        setImage(image);
    }

    //FIELDS
    private int i;
    private int j;

    //METHODS
    @Override
    public ActorPicture clone() {
        ActorPicture result = null;
        try {
            result = (ActorPicture) super.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void draw(BoardView view, double x, double y) {
        Platform.runLater(() -> {
            if (x >= 0 && y >= 0)
                view.addImageView((int) y, (int) x, this);
        });
    }

    public static ActorPicture food() {
        return new ActorPicture(IMAGE_FOOD);
    }

    public static ActorPicture ghost() {
        return new ActorPicture(IMAGE_GHOST);
    }

    public static ActorPicture pacman() {
        return new ActorPicture(IMAGE_PACMAN);
    }

    public static ActorPicture wall() {
        return new ActorPicture(IMAGE_WALL);
    }
}
