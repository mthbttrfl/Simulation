package org.example.validators;

import org.example.entities.Entity;

public class DefaultMovedValidator implements FactoryValidator {

    @Override
    public void validateTargets(Class<? extends Entity> ... targets) {
        if (targets == null || targets.length == 0) {
            throw new IllegalArgumentException("Targets cannot be null or empty");
        }
    }
}
