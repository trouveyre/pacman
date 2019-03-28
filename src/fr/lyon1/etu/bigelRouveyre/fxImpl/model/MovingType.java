package fr.lyon1.etu.bigelRouveyre.fxImpl.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Moving;

import java.util.List;

public enum MovingType implements Moving {

    //OBJECTS
    Forcing {
        @Override
        public void move(Actor doer, int row, int column) {
            int rowDistance = row - doer.getRow();
            int columnDistance = column - doer.getColumn();
            if (rowDistance == 0 && columnDistance == 0) return;

            int rowStep = 0, columnStep = 0;
            if (rowDistance != 0)
                rowStep = rowDistance / Math.abs(rowDistance);
            else if (columnDistance != 0)
                columnStep = columnDistance / Math.abs(columnDistance);

            List<Actor> metActors = doer.getBoard()
                    .atCase(doer.getRow()+rowStep, doer.getColumn()+columnStep);

            boolean canMove = true;
            for (Actor actor : metActors) {
                actor.onContact(doer);
                if (!doer.onContact(actor)) canMove = false;
            }

            if (canMove)
                doer.shift(doer.getRow() + rowStep, doer.getColumn() + columnStep);
        }
    },
    Rooted {
        @Override
        public void move(Actor doer, int row, int column) {}
    },
    Smart {
        @Override
        public void move(Actor doer, int row, int column) {
            // TODO
        }
    }
}
