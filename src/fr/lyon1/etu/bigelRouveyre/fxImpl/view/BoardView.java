package fr.lyon1.etu.bigelRouveyre.fxImpl.view;

import fr.lyon1.etu.bigelRouveyre.inter.view.View;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BoardView extends Scene implements View {

    //CONSTRUCTORS
    public BoardView(int nbRows, int nbColumns) {
        super(new GridPane(), nbRows * ActorPicture.SIZE, nbColumns * ActorPicture.SIZE);
        gridPane = (GridPane) getRoot();
        imageViews = new ImageView[nbRows][nbColumns];
    }

    //FIELDS
    private GridPane gridPane;
    private ImageView[][] imageViews;

    //METHODS
    public void addImageView(int i, int j, ImageView imageView) {
        gridPane.add(imageView, j, i);
    }

    @Override
    public void clear() {
        Platform.runLater(() -> {
            gridPane.getChildren().clear();
        });
    }

    @Override
    public void close() {
        Platform.runLater(() -> ((Stage) getWindow()).close());
    }

    public ImageView[][] getImageViews() {
        return imageViews.clone();
    }

    public void removeImageView(ImageView imageView) {
        gridPane.getChildren().remove(imageView);
    }
}
