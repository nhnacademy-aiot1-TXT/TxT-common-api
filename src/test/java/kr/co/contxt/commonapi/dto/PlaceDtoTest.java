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

class PlaceDtoTest {
    private static Validator validator;

    @BeforeAll
    public static void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void placeRequestValidation() {
        PlaceRequest request = new PlaceRequest("test", LocalTime.now());
        PlaceRequest resultWithBlank = new PlaceRequest("", null);

        Set<ConstraintViolation<PlaceRequest>> validate = validator.validate(request);
        Set<ConstraintViolation<PlaceRequest>> validateNull = validator.validate(resultWithBlank);

        assertAll(
                () -> assertTrue(validate.isEmpty()),
                () -> assertFalse(validateNull.isEmpty())
        );
    }
}
