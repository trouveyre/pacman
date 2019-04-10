package fr.lyon1.etu.bigelRouveyre.pacman.model;

import fr.lyon1.etu.bigelRouveyre.core.view.javafx.ActorPicture;
import javafx.scene.image.Image;

public enum PacmanTheme {

    //OBJECTS
    Normal("wall_normal.png", "pacman_normal.png", "block.png", "blue_normal.png", "orange_normal.png", "pink_normal.png", "red_normal.png"),
    Water("wall_water.png", "pacman_normal.png", "block.png", "blue_water.png", "orange_water.png", "pink_water.png", "red_water.png"),
    Zombie("wall_zombie.png", "pacman_zombie.png", "block.png", "blue_zombie.png", "orange_zombie.png", "pink_zombie.png", "red_zombie.png");

    //CONSTRUCTORS
    private PacmanTheme(String wall, String pacman, String food, String... ghosts) {
        this.wall = wall;
        this.pacman = pacman;
        this.ghosts = ghosts;
        this.food = food;
    }

    //FIELDS
    private String wall;
    private String pacman;
    private String[] ghosts;
    private String food;

    //METHODS
    public Image getFoodImage() {
        return new Image("asset/"+food, ActorPicture.SIZE, ActorPicture.SIZE, true, true);
    }
    public Image getPacmanImage() {
        return new Image("asset/"+pacman, ActorPicture.SIZE, ActorPicture.SIZE, true, true);
    }
    public Image getRandomGhostImage() {
        return new Image("asset/"+ghosts[(int) (Math.random() * ghosts.length)], ActorPicture.SIZE, ActorPicture.SIZE, true, true);
    }
    public Image getWallImage() {
        return new Image("asset/"+wall, ActorPicture.SIZE, ActorPicture.SIZE, true, true);
    }
}
