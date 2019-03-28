package fr.lyon1.etu.bigelRouveyre.fxImpl.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Moving;

public enum MovingType implements Moving {

    //OBJECTS
    Forcing {
        @Override
        public void move(Actor doer, int row, int column) {
            if (row < 0 || column < 0) return;
            int rowDistance = row - doer.getRow();
            int columnDistance = column - doer.getColumn();
            if (rowDistance == 0 && columnDistance == 0) return;
            int rowStep = 0;
            int columnStep = 0;
            if (rowDistance != 0)
                rowStep = rowDistance / Math.abs(rowDistance);
            else if (columnDistance != 0)
                columnStep = columnDistance / Math.abs(columnDistance);
            Actor metActor = doer.getBoard().atCase(doer.getRow() + rowStep, doer.getColumn() + columnStep);
            if (metActor == null || metActor.onContact(doer))
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
