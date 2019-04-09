package fr.lyon1.etu.bigelRouveyre.pacman.view.javafx;

import fr.lyon1.etu.bigelRouveyre.inter.view.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class EndScreen extends Scene {

    public static final int ENDSCREEN_HEIGHT = 338;
    public static final int ENDSCREEN_WIDTH = 600;

    //CONSTRUCTORS
    public EndScreen(String image, View endedView) throws IOException {
        super(new StackPane(), ENDSCREEN_WIDTH, ENDSCREEN_HEIGHT);
        StackPane pane = new FXMLLoader(getClass().getResource("endScreen.fxml")).load();
        setRoot(pane);
        ImageView imageView = (ImageView) pane.lookup("#imageView");
        imageView.setImage(new Image(image, ENDSCREEN_WIDTH, ENDSCREEN_HEIGHT, true, true));
        Button leaveButton = (Button) pane.lookup("#leaveButton");
        leaveButton.setOnAction(event -> endedView.close());
    }

    //FIELDS

    //METHODS
}
