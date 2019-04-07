package fr.lyon1.etu.bigelRouveyre.core.view.javafx;

import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;
import fr.lyon1.etu.bigelRouveyre.inter.view.View;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class LocalView extends Scene implements View {

    //CONSTRUCTORS
    public LocalView(int nbRows, int nbColumns) {
        super(new GridPane(), nbRows * ActorPicture.SIZE, nbColumns * ActorPicture.SIZE);
        gridPane = (GridPane) getRoot();
        for (int i=0; i<nbRows; i++)
            gridPane.getRowConstraints().add(i, new RowConstraints(ActorPicture.SIZE));
        for (int j=0; j<nbColumns; j++)
            gridPane.getColumnConstraints().add(j, new ColumnConstraints(ActorPicture.SIZE));
        setOnKeyTyped(event -> players.forEach(player -> player.onCommand(event.getCharacter())));
        setOnMouseClicked(event -> players.forEach(player -> player.onClick(event.getX(), event.getY())));
    }

    //FIELDS
    private GridPane gridPane;
    private List<Player> players = new ArrayList<>();

    //METHODS
    public void addImageView(int i, int j, ImageView imageView) {
        gridPane.add(imageView, j, i);
    }

    @Override
    public void addListeningPlayer(Player player) {
        players.add(player);
    }

    @Override
    public void clear() {
        Platform.runLater(() -> gridPane.getChildren().clear());
    }

    @Override
    public void close() {
        Platform.runLater(() -> ((Stage) getWindow()).close());
    }

    @Override
    public List<Player> getListeningPlayers() {
        return players;
    }

    @Override
    public void open() {
        // TODO
    }

    public void removeImageView(ImageView imageView) {
        gridPane.getChildren().remove(imageView);
    }

    @Override
    public void removeListeningPlayer(Player player) {
        players.remove(player);
    }
}
