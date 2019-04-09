package fr.lyon1.etu.bigelRouveyre.pacman;

import fr.lyon1.etu.bigelRouveyre.core.model.*;
import fr.lyon1.etu.bigelRouveyre.pacman.model.PacmanGame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launcher extends Application {

    public static void main(String... args) {
        Application.launch(args);
    }

    //FIELDS
    private Stage primaryStage;

    //METHODS
    @Override
    public void start(Stage primaryStage) throws Exception {
        TitledPane rootPane = new FXMLLoader(getClass().getResource("launcher.fxml")).load();
        Scene mainScene = new Scene(rootPane);
        primaryStage.setScene(mainScene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        this.primaryStage = primaryStage;
        primaryStage.show();

        Button playButton = (Button) rootPane.lookup("#playButton");
        playButton.setOnAction((event) -> onClickOnPlay());
    }

    public void onClickOnPlay() {
        PacmanGame game = new PacmanGame(new TwoDimensionDiggingGenerator(17, 17), 15, 4);
        game.newPacman("Mario", "q", "z", "d", "s");
        game.newPacman("Luigi", "k", "o", "m", "l");

        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("You re playing a remastered PACMAN ! Great ! :D");
        stage.setOnCloseRequest((event) -> game.end());

        Scene scene = game.getLocalView();
        stage.setScene(scene);
        stage.show();

        new Thread(() -> System.out.println(game.launch())).start();
    }
}
