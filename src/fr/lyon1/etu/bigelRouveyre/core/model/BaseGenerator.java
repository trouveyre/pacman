package fr.lyon1.etu.bigelRouveyre.core.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.Actor;
import fr.lyon1.etu.bigelRouveyre.inter.model.Generator;

public abstract class BaseGenerator implements Generator {

    public static final Actor DEFAULT_PROTOTYPE = new StandardActor();

    //CONSTRUCTORS

    //FIELDS
    private Actor prototype = DEFAULT_PROTOTYPE;

    //METHODS
    @Override
    public Actor getPrototype() {
        return prototype;
    }

    @Override
    public void setPrototype(Actor prototype) {
        this.prototype = prototype;
    }
}
