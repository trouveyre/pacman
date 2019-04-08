package fr.lyon1.etu.bigelRouveyre.core.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Coordinates;
import fr.lyon1.etu.bigelRouveyre.inter.model.Moving;

import java.util.HashSet;
import java.util.Set;

public enum TwoDimensionMovingType implements Moving {

    //OBJECTS
    Forcing {
        @Override
        public void move(Actor doer, Coordinates coordinates) {
            if (coordinates == null || !doer.isAlive()) return;

            int rowDistance = coordinates.get()[0] - doer.getCoordinates().get()[0];
            int columnDistance = coordinates.get()[1] - doer.getCoordinates().get()[1];
            if (rowDistance == 0 && columnDistance == 0) return;

            int rowStep = 0, columnStep = 0;
            if (rowDistance != 0)
                rowStep = rowDistance / Math.abs(rowDistance);
            else
                columnStep = columnDistance / Math.abs(columnDistance);

            Coordinates predictedCoordinates = StandardCoordinates.twoDimensions(
                    doer.getCoordinates().get()[0]+rowStep,
                    doer.getCoordinates().get()[1]+columnStep
            );
            Set<Actor> metActors = doer.getBoard().atCase(predictedCoordinates);

            boolean canMove = true;
            for (Actor actor : new HashSet<>(metActors)) {
                actor.onContact(doer);
                if (!doer.onContact(actor)) canMove = false;
            }

            if (canMove)
                doer.shift(predictedCoordinates);
        }
    },
    Rooted {
        @Override
        public void move(Actor doer, Coordinates coordinates) {}
    },
    Smart {
        @Override
        public void move(Actor doer, Coordinates coordinates) {
            // TODO
        }
    }
}
