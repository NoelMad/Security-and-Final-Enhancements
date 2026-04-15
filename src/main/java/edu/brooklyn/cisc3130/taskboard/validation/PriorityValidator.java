package edu.brooklyn.cisc3130.taskboard.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PriorityValidator implements ConstraintValidator<ValidPriority, String> {

    private static final List<String> VALID_PRIORITIES =
            Arrays.asList("LOW", "MEDIUM", "HIGH");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true; // let @NotNull handle nulls
        return VALID_PRIORITIES.contains(value.toUpperCase());
    }
}