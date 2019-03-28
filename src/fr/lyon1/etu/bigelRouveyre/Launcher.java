package fr.lyon1.etu.bigelRouveyre;

import fr.lyon1.etu.bigelRouveyre.fxImpl.controler.LocalPlayer;
import fr.lyon1.etu.bigelRouveyre.fxImpl.controler.RandomPlayer;
import fr.lyon1.etu.bigelRouveyre.fxImpl.controler.StandardGame;
import fr.lyon1.etu.bigelRouveyre.fxImpl.model.*;
import fr.lyon1.etu.bigelRouveyre.fxImpl.view.BoardView;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Game;
import fr.lyon1.etu.bigelRouveyre.inter.controler.Player;
import fr.lyon1.etu.bigelRouveyre.inter.model.*;
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
        Board board = new StandardBoard(17, 17)
                .build(new DiggingGenerator());
        Actor actor0 = StandardActor.pacman(board);
        Actor actor1 = StandardActor.ghost(board);
        Actor actor2 = StandardActor.ghost(board);
        Actor actor3 = StandardActor.ghost(board);
        Player[] players = new Player[] {
                new LocalPlayer(actor0, 'q', 'z', 'd', 's'),
                new RandomPlayer(actor1, 0, 0, 15, 15),
                new RandomPlayer(actor2, 0, 0, 15, 15),
                new RandomPlayer(actor3, 0, 0, 15, 15)
        };
        board.initiate(new RandomPacmanInitiator(players, 10));
        Game game = new StandardGame(board, players);

        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("You re playing a remastered PACMAN ! Great ! :D");
        stage.setOnCloseRequest((event) -> game.end());

        Scene scene = (BoardView) game.getView();
        stage.setScene(scene);
        stage.show();

        new Thread(() -> game.launch()).start();
    }
}
