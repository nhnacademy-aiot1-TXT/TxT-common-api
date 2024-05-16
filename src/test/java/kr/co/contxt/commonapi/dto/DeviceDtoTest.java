package kr.co.contxt.commonapi.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DeviceDtoTest {
    private static Validator validator;

    @BeforeAll
    public static void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void deviceRequestValidation() {
        DeviceRequest request = new DeviceRequest(1L, "test");
        DeviceRequest requestWithNull = new DeviceRequest(null, "");

        Set<ConstraintViolation<DeviceRequest>> validate = validator.validate(request);
        Set<ConstraintViolation<DeviceRequest>> validateNull = validator.validate(requestWithNull);

        assertAll(
                () -> assertTrue(validate.isEmpty()),
                () -> assertFalse(validateNull.isEmpty())
        );
    }
}
