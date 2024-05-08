package kr.co.contxt.commonapi.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ApiInfoTest {
    private static Validator validator;

    @BeforeAll
    public static void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void apiInfoValidation() {
        ApiInfo info = new ApiInfo("test", "test", "test", "test", "test", "test", 1, 2);
        ApiInfo infoWithNull = new ApiInfo(null, null, null, null, null, null, 1, 2);

        Set<ConstraintViolation<ApiInfo>> validate = validator.validate(info);
        Set<ConstraintViolation<ApiInfo>> validateNull = validator.validate(infoWithNull);

        assertAll(
                () -> assertTrue(validate.isEmpty()),
                () -> assertFalse(validateNull.isEmpty())
        );
    }
}