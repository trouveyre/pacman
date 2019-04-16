package fr.lyon1.etu.bigelRouveyre.pacman;

import fr.lyon1.etu.bigelRouveyre.core.model.*;
import fr.lyon1.etu.bigelRouveyre.core.view.javafx.ScoresView;
import fr.lyon1.etu.bigelRouveyre.inter.model.GameResult;
import fr.lyon1.etu.bigelRouveyre.pacman.model.PacmanGame;
import fr.lyon1.etu.bigelRouveyre.pacman.model.PacmanTheme;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URISyntaxException;

public class Launcher extends Application {

    public static void main(String... args) {
        Application.launch(args);
    }

    //FIELDS
    private Stage primaryStage;
    private Scene mainScene;
    private TitledPane rootPane;

    //METHODS
    @Override
    public void start(Stage primaryStage) throws Exception {
        rootPane = new FXMLLoader(getClass().getResource("launcher.fxml")).load();
        mainScene = new Scene(rootPane);
        primaryStage.setScene(mainScene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        this.primaryStage = primaryStage;
        primaryStage.show();

        Button playButton = (Button) rootPane.lookup("#playButton");
        playButton.setOnAction((event) -> onClickOnPlay());

        Spinner<Integer> pacgums = (Spinner<Integer>) rootPane.lookup("#pacgums");
        pacgums.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 10));

        ChoiceBox<String> themeMenu = (ChoiceBox<String>) rootPane.lookup("#themeMenu");
        themeMenu.getItems().addAll("normal", "water", "zombie");
        themeMenu.valueProperty().setValue("normal");

        ImageView background = (ImageView) rootPane.lookup("#background");
        background.setImage(new Image("asset/pacmanWallpaper.jpg"));
    }

    public void onClickOnPlay() {
        TextField mapPath = (TextField) rootPane.lookup("#mapPath");
        String map = mapPath.getText();
        Slider boardHeight = (Slider) (rootPane.lookup("#boardHeight"));
        int height = (int) boardHeight.getValue();
        Slider boardWidth = (Slider) (rootPane.lookup("#boardWidth"));
        int width = (int) boardWidth.getValue();

        Slider slider = (Slider) (rootPane.lookup("#nbPlayersSlider"));
        Slider ghostsSlider = (Slider) (rootPane.lookup("#nbGhostsSlider"));
        int nbGhosts = (int) ghostsSlider.valueProperty().get();
        Spinner<Integer> pacgums = (Spinner<Integer>) rootPane.lookup("#pacgums");
        int nbPacgums = pacgums.getValue();
        ChoiceBox<String> themeMenu = (ChoiceBox<String>) rootPane.lookup("#themeMenu");
        PacmanTheme theme = PacmanTheme.Normal;
        switch (themeMenu.getValue()) {
            case "normal":
                theme = PacmanTheme.Normal;
                break;
            case "water":
                theme = PacmanTheme.Water;
                break;
            case "zombie":
                theme = PacmanTheme.Zombie;
                break;
        }

        PacmanGame gameInit;
        try {
            gameInit = new PacmanGame(new LoaderGenerator(map + ".txt"), nbPacgums, nbGhosts, theme);
        }
        catch (IOException | URISyntaxException e) {
            gameInit = new PacmanGame(new TwoDimensionDiggingGenerator(height, width), nbPacgums, nbGhosts, theme);
            e.printStackTrace();
        }

        PacmanGame game = gameInit;
        game.newPacman("Mario", "q", "z", "d", "s");
        if (slider.valueProperty().get() >= 2)
            game.newPacman("Luigi", "k", "o", "m", "l");

        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("You re playing a remastered PACMAN ! Great ! :D");
        stage.setOnCloseRequest((event) -> game.end());

        Scene scene = game.getLocalView();
        stage.setScene(scene);
        stage.show();

        new Thread(() -> {
            GameResult result = game.launch();
            VBox vbox = new VBox();
            ScrollPane root = new ScrollPane(vbox);
            Button playAgainButton = new Button("PLAY AGAIN");
            playAgainButton.setOnAction(event -> Platform.runLater(() -> mainScene.setRoot(rootPane)));
            vbox.getChildren().add(playAgainButton);
            vbox.getChildren().add(new ScoresView(result));
            Platform.runLater(() -> mainScene.setRoot(root));
        }).start();
    }
}
