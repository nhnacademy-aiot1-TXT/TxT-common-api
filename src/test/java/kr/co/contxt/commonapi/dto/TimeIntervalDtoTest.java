package kr.co.contxt.commonapi.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TimeIntervalDtoTest {
    private static Validator validator;

    @BeforeAll
    public static void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void timeIntervalRequestValidation() {
        TimeIntervalRequest request = new TimeIntervalRequest(1L, "test", LocalTime.now(), LocalTime.now());
        TimeIntervalRequest requestWithNull = new TimeIntervalRequest(null, "", null, null);

        Set<ConstraintViolation<TimeIntervalRequest>> validate = validator.validate(request);
        Set<ConstraintViolation<TimeIntervalRequest>> validateNull = validator.validate(requestWithNull);

        assertAll(
                () -> assertTrue(validate.isEmpty()),
                () -> assertFalse(validateNull.isEmpty())
        );
    }
}
