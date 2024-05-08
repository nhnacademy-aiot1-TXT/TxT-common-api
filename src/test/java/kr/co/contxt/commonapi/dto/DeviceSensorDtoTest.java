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

public class DeviceSensorDtoTest {
    private static Validator validator;

    @BeforeAll
    public static void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void deviceAndSensorNameDtoValidation() {
        DeviceAndSensorNameDto request = new DeviceAndSensorNameDto("test", "test");
        DeviceAndSensorNameDto requestWithNull = new DeviceAndSensorNameDto("", "");

        Set<ConstraintViolation<DeviceAndSensorNameDto>> validate = validator.validate(request);
        Set<ConstraintViolation<DeviceAndSensorNameDto>> validateNull = validator.validate(requestWithNull);

        assertTrue(validate.isEmpty());
        assertFalse(validateNull.isEmpty());
    }

    @Test
    void deviceNameDtoValidation() {
        DeviceNameDto response = new DeviceNameDto("test");
        DeviceNameDto responseWithNull = new DeviceNameDto("");

        Set<ConstraintViolation<DeviceNameDto>> validate = validator.validate(response);
        Set<ConstraintViolation<DeviceNameDto>> validateNull = validator.validate(responseWithNull);

        assertTrue(validate.isEmpty());
        assertFalse(validateNull.isEmpty());
    }

    @Test
    void deviceSensorRequestValidation() {
        DeviceSensorRequest response = new DeviceSensorRequest("test", "test", 1f, 2f);
        DeviceSensorRequest responseWithNull = new DeviceSensorRequest("", "", null, null);

        Set<ConstraintViolation<DeviceSensorRequest>> validate = validator.validate(response);
        Set<ConstraintViolation<DeviceSensorRequest>> validateNull = validator.validate(responseWithNull);

        assertTrue(validate.isEmpty());
        assertFalse(validateNull.isEmpty());
    }

    @Test
    void deviceSensorResponseValidation() {
        DeviceSensorResponse response = new DeviceSensorResponse(1L, 2L, "test", 3f, 4f);
        DeviceSensorResponse responseWithNull = new DeviceSensorResponse(null, null, "", null, null);

        Set<ConstraintViolation<DeviceSensorResponse>> validate = validator.validate(response);
        Set<ConstraintViolation<DeviceSensorResponse>> validateNull = validator.validate(responseWithNull);

        assertTrue(validate.isEmpty());
        assertFalse(validateNull.isEmpty());
    }
}
