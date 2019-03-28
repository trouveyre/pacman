package fr.lyon1.etu.bigelRouveyre.fxImpl.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Impact;

public enum ImpactType implements Impact {

    //OBJECTS
    Acid {
        @Override
        public boolean apply(Actor doer, Actor target) {
            if (target.getImpact() == Weak) target.die();
            return false;
        }
    },
    Feed {
        @Override
        public boolean apply(Actor doer, Actor target) {
            if (target.getImpact() == Weak) doer.die();
            return true;
        }
    },
    Solid {
        @Override
        public boolean apply(Actor doer, Actor target) {
            return false;
        }
    },
    Weak {
        @Override
        public boolean apply(Actor doer, Actor target) {
            return true;
        }
    }
}
