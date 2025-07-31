package org.example.validators;

import org.example.entities.Entity;

public interface FactoryValidator {
    void validateTargets(Class<? extends Entity> ... targets);
}
