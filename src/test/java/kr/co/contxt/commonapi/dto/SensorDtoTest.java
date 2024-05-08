package kr.co.contxt.commonapi.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SensorDtoTest {
    private static Validator validator;

    @BeforeAll
    public static void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void sensorNameDtoValidation() {
        SensorNameDto sensorNameDto = new SensorNameDto("test");
        SensorNameDto sensorNameDtoWithNull = new SensorNameDto("");

        Set<ConstraintViolation<SensorNameDto>> validate = validator.validate(sensorNameDto);
        Set<ConstraintViolation<SensorNameDto>> validateNull = validator.validate(sensorNameDtoWithNull);

        assertAll(
                () -> assertTrue(validate.isEmpty()),
                () -> assertFalse(validateNull.isEmpty())
        );
    }

    @Test
    void sensorRequestValidation() {
        SensorRequest request = new SensorRequest("test");
        SensorRequest requestWithNull = new SensorRequest("");

        Set<ConstraintViolation<SensorRequest>> validate = validator.validate(request);
        Set<ConstraintViolation<SensorRequest>> validateNull = validator.validate(requestWithNull);

        assertAll(
                () -> assertTrue(validate.isEmpty()),
                () -> assertFalse(validateNull.isEmpty())
        );
    }

    @Test
    void sensorResponseValidation() {
        SensorResponse response = new SensorResponse(1L, "test");
        SensorResponse responseWithNull = new SensorResponse(null, "");

        Set<ConstraintViolation<SensorResponse>> validate = validator.validate(response);
        Set<ConstraintViolation<SensorResponse>> validateNull = validator.validate(responseWithNull);

        assertAll(
                () -> assertTrue(validate.isEmpty()),
                () -> assertFalse(validateNull.isEmpty())
        );
    }
}
