package fr.lyon1.etu.bigelRouveyre.core.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Impact;

public enum ImpactType implements Impact {

    //OBJECTS
    Solid {
        @Override
        public boolean apply(Actor doer, Actor target) {
            return false;
        }
    }
}
