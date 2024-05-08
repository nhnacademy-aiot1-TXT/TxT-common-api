package kr.co.contxt.commonapi.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeviceDtoTest {
    private static Validator validator;

    @BeforeAll
    public static void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void deviceRequestValidation() {
        DeviceRequest request = new DeviceRequest("test", LocalTime.now());
        DeviceRequest requestWithNull = new DeviceRequest("", null);

        Set<ConstraintViolation<DeviceRequest>> validate = validator.validate(request);
        Set<ConstraintViolation<DeviceRequest>> validateNull = validator.validate(requestWithNull);

        assertTrue(validate.isEmpty());
        assertFalse(validateNull.isEmpty());
    }

    @Test
    void deviceResponseValidation() {
        DeviceResponse response = new DeviceResponse(1L, "test", LocalTime.now());
        DeviceResponse responseWithNull = new DeviceResponse(null, "", null);

        Set<ConstraintViolation<DeviceResponse>> validate = validator.validate(response);
        Set<ConstraintViolation<DeviceResponse>> validateNull = validator.validate(responseWithNull);

        assertTrue(validate.isEmpty());
        assertFalse(validateNull.isEmpty());
    }
}
