package fr.lyon1.etu.bigelRouveyre.pacman.model;

import fr.lyon1.etu.bigelRouveyre.core.model.TwoDimensionMovingType;
import fr.lyon1.etu.bigelRouveyre.core.model.StandardActor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.pacman.view.javafx.PacmanPicture;

public abstract class PacmanActor extends StandardActor {

    public static Actor food() {
        Actor result = new StandardActor();
        result.setPicture(PacmanPicture.food());
        result.setMoving(TwoDimensionMovingType.Rooted);
        result.setImpact(PacmanImpact.Food);
        return result;
    }

    public static Actor ghost() {
        Actor result = new StandardActor();
        result.setPicture(PacmanPicture.ghost());
        result.setMoving(TwoDimensionMovingType.Forcing);
        result.setImpact(PacmanImpact.Ghost);
        return result;
    }

    public static Actor pacman() {
        Actor result = new StandardActor();
        result.setPicture(PacmanPicture.pacman());
        result.setMoving(TwoDimensionMovingType.Forcing);
        result.setImpact(PacmanImpact.Pacman);
        return result;
    }

    //CONSTRUCTORS

    //FIELDS

    //METHODS
}
