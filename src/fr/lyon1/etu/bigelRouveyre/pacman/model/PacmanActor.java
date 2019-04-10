package fr.lyon1.etu.bigelRouveyre.pacman.model;

import fr.lyon1.etu.bigelRouveyre.core.model.ImpactType;
import fr.lyon1.etu.bigelRouveyre.core.model.TwoDimensionMovingType;
import fr.lyon1.etu.bigelRouveyre.core.model.StandardActor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.pacman.view.javafx.PacmanPicture;

public abstract class PacmanActor extends StandardActor {

    public static Actor food(PacmanTheme theme) {
        Actor result = new StandardActor();
        result.setPicture(PacmanPicture.food(theme));
        result.setMoving(TwoDimensionMovingType.Rooted);
        result.setImpact(PacmanImpact.Food);
        return result;
    }

    public static Actor ghost(PacmanTheme theme) {
        Actor result = new StandardActor();
        result.setPicture(PacmanPicture.ghost(theme));
        result.setMoving(TwoDimensionMovingType.Forcing);
        result.setImpact(PacmanImpact.Ghost);
        return result;
    }

    public static Actor pacman(PacmanTheme theme) {
        Actor result = new StandardActor();
        result.setPicture(PacmanPicture.pacman(theme));
        result.setMoving(TwoDimensionMovingType.Forcing);
        result.setImpact(PacmanImpact.Pacman);
        return result;
    }

    public static Actor wall(PacmanTheme theme) {
        Actor result = new StandardActor();
        result.setPicture(PacmanPicture.wall(theme));
        result.setMoving(TwoDimensionMovingType.Rooted);
        result.setImpact(ImpactType.Solid);
        return result;
    }

    //CONSTRUCTORS

    //FIELDS

    //METHODS
}
