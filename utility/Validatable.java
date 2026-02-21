package utility;

import manager.ValidationManager;

public interface Validatable {
    default boolean isValid() {
        return ValidationManager.isValidObject(this);
    };
}
