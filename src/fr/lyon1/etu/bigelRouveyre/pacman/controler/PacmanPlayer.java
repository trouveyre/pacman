package fr.lyon1.etu.bigelRouveyre.pacman.controler;

import fr.lyon1.etu.bigelRouveyre.core.controler.LocalPlayer;
import fr.lyon1.etu.bigelRouveyre.pacman.view.javafx.EndScreen;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PacmanPlayer extends LocalPlayer {

    //CONSTRUCTORS
    public PacmanPlayer(String name, String leftKey, String upKey, String rightKey, String downKey) {
        super(name, leftKey, upKey, rightKey, downKey);
    }

    //FIELDS

    //METHODS
    private void showEndScreen(EndScreen endScreen) {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            stage.initOwner(((Scene) getView()).getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(endScreen);
            stage.show();
        });
    }

    @Override
    public void onLose() {
        try {
            showEndScreen(new EndScreen("asset/GameOverRetro.jpg", getView()));
        }
        catch (IOException ioe) {
            getView().close();
            ioe.printStackTrace();
        }
    }

    @Override
    public void onWin() {
        try {
            showEndScreen(new EndScreen("asset/YouWinRetro.png", getView()));
        }
        catch (IOException ioe) {
            getView().close();
            ioe.printStackTrace();
        }
    }
}
