package kr.co.contxt.commonapi.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherDtoTest {
    private static Validator validator;

    @BeforeAll
    public static void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void weatherResponseValidation() {
        WeatherResponseDto response = new WeatherResponseDto(1f, "message");
        WeatherResponseDto responseWithNull = new WeatherResponseDto(null, "");

        Set<ConstraintViolation<WeatherResponseDto>> validate = validator.validate(response);
        Set<ConstraintViolation<WeatherResponseDto>> validateNull = validator.validate(responseWithNull);

        assertTrue(validate.isEmpty());
        assertFalse(validateNull.isEmpty());
    }
}
