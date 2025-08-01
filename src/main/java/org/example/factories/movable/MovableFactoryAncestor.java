package org.example.factories.movable;

import org.example.entities.Entity;
import org.example.validators.DefaultMovedValidator;
import org.example.validators.FactoryValidator;

public abstract class MovableFactoryAncestor implements FactoryMovable {

    private final FactoryValidator validator;
    protected final Class<? extends Entity>[] targets;

    public MovableFactoryAncestor(Class<? extends Entity> ... targets) {
        this(new DefaultMovedValidator(), targets);
    }

    public MovableFactoryAncestor(FactoryValidator validator, Class<? extends Entity> ... targets) {
        this.validator = validator;
        validator.validateTargets(targets);
        this.targets = targets;
    }
}