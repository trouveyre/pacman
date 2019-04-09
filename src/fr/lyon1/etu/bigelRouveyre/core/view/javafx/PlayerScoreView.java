package fr.lyon1.etu.bigelRouveyre.core.view.javafx;

import fr.lyon1.etu.bigelRouveyre.inter.model.PlayerResult;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PlayerScoreView extends HBox {

    //CONSTRUCTORS
    public PlayerScoreView(PlayerResult result) {
        super();
        TextField playerName = new TextField(result.getPlayer().getName());
        playerName.setEditable(false);
        playerName.setMouseTransparent(true);
        playerName.setFocusTraversable(false);
        TextField lastTurn = new TextField(Integer.toString(result.getTime()));
        lastTurn.setEditable(false);
        lastTurn.setMouseTransparent(true);
        lastTurn.setFocusTraversable(false);
        TextField score = new TextField(Integer.toString(result.getScore()));
        score.setEditable(false);
        score.setMouseTransparent(true);
        score.setFocusTraversable(false);
        Platform.runLater(() -> {
            getChildren().add((ImageView) (result.getPlayer().getActor().getPicture()));
            getChildren().add(playerName);
            getChildren().add(lastTurn);
            getChildren().add(score);
        });
    }

    //FIELDS

    //METHODS
}
