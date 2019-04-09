package fr.lyon1.etu.bigelRouveyre.core.view.javafx;

import fr.lyon1.etu.bigelRouveyre.inter.model.GameResult;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ScoresView extends VBox {

    //CONSTRUCTORS
    public ScoresView(GameResult results) {
        super();
        HBox header = new HBox();
        TextField playerName = new TextField("Player name");
        playerName.setEditable(false);
        playerName.setMouseTransparent(true);
        playerName.setFocusTraversable(false);
        TextField lastTurn = new TextField("Last turn alive");
        lastTurn.setEditable(false);
        lastTurn.setMouseTransparent(true);
        lastTurn.setFocusTraversable(false);
        TextField score = new TextField("Score");
        score.setEditable(false);
        score.setMouseTransparent(true);
        score.setFocusTraversable(false);
        header.getChildren().add(new ImageView(new Image(
                "asset/CuteVampireGhost.png",
                ActorPicture.SIZE,
                ActorPicture.SIZE,
                true,
                true
        )));
        header.getChildren().add(playerName);
        header.getChildren().add(lastTurn);
        header.getChildren().add(score);
        getChildren().add(header);
        results.getGame().getPlayers().forEach(player ->
            getChildren().add(new PlayerScoreView(player.getResult()))
        );

    }

    //FIELDS

    //METHODS
}
