package fr.lyon1.etu.bigelRouveyre.core.view.javafx;

import fr.lyon1.etu.bigelRouveyre.inter.view.Drawable;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ActorPicture extends ImageView implements Drawable<LocalView>, Cloneable {

    public static final int SIZE = 52;

    //CONSTRUCTORS
    public ActorPicture(String asset) {
        this(new Image(asset, SIZE, SIZE, true, true));
    }
    public ActorPicture(Image image) {
        super();
        setImage(image);
    }

    //FIELDS

    //METHODS
    @Override
    public void clean(LocalView view) {
        if (view != null)
            Platform.runLater(() -> view.removeImageView(this));
    }

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
    public void draw(LocalView view, double x, double y) {
        if (view != null)
            Platform.runLater(() -> view.addImageView((int) x, (int) y, this));
    }
}
