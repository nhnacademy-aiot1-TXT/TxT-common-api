package kr.co.contxt.commonapi.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ApiExceptionDtoTest {
    private static Validator validator;

    @BeforeAll
    public static void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void apiExceptionDtoValidation() {
        ApiExceptionDto apiExceptionDto = new ApiExceptionDto(LocalDateTime.now(), "message");
        ApiExceptionDto apiExceptionDtoWithNull = new ApiExceptionDto(null, "");

        Set<ConstraintViolation<ApiExceptionDto>> validate = validator.validate(apiExceptionDto);
        Set<ConstraintViolation<ApiExceptionDto>> validateNull = validator.validate(apiExceptionDtoWithNull);

        assertAll(
                () -> assertTrue(validate.isEmpty()),
                () -> assertFalse(validateNull.isEmpty())
        );
    }
}