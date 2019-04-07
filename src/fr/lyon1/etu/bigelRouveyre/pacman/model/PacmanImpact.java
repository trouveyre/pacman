package fr.lyon1.etu.bigelRouveyre.pacman.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Impact;

public enum PacmanImpact implements Impact {

    //OBJECTS
    Food {
        @Override
        public boolean apply(Actor doer, Actor target) {
            if (target.getImpact() == Pacman) {
                doer.die();
                target.getListeningPlayers().forEach(player -> player.addScore(1));
            }
            return true;
        }
    },
    Ghost {
        @Override
        public boolean apply(Actor doer, Actor target) {
            if (target.getImpact() == Pacman) target.die();
            return false;
        }
    },
    Pacman {
        @Override
        public boolean apply(Actor doer, Actor target) {
            return true;
        }
    }
}
