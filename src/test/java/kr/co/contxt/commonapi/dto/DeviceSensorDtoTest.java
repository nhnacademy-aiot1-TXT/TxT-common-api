package kr.co.contxt.commonapi.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DeviceSensorDtoTest {
    private static Validator validator;

    @BeforeAll
    public static void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void deviceAndSensorNameDtoValidation() {
        DeviceAndSensorNameAndPlaceNameDto deviceAndSensorNameAndPlaceNameDto = new DeviceAndSensorNameAndPlaceNameDto("test", "test", "test");
        DeviceAndSensorNameAndPlaceNameDto deviceAndSensorNameAndPlaceNameDtoWithNull = new DeviceAndSensorNameAndPlaceNameDto("", "", "");

        Set<ConstraintViolation<DeviceAndSensorNameAndPlaceNameDto>> validate = validator.validate(deviceAndSensorNameAndPlaceNameDto);
        Set<ConstraintViolation<DeviceAndSensorNameAndPlaceNameDto>> validateNull = validator.validate(deviceAndSensorNameAndPlaceNameDtoWithNull);

        assertAll(
                () -> assertTrue(validate.isEmpty()),
                () -> assertFalse(validateNull.isEmpty())
        );
    }

    @Test
    void deviceNameDtoValidation() {
        DeviceNameAndPlaceNameDto deviceNameAndPlaceNameDto = new DeviceNameAndPlaceNameDto("test", "test");
        DeviceNameAndPlaceNameDto deviceNameAndPlaceNameDtoWithNull = new DeviceNameAndPlaceNameDto("", "");

        Set<ConstraintViolation<DeviceNameAndPlaceNameDto>> validate = validator.validate(deviceNameAndPlaceNameDto);
        Set<ConstraintViolation<DeviceNameAndPlaceNameDto>> validateNull = validator.validate(deviceNameAndPlaceNameDtoWithNull);

        assertAll(
                () -> assertTrue(validate.isEmpty()),
                () -> assertFalse(validateNull.isEmpty())
        );
    }

    @Test
    void deviceSensorRequestValidation() {
        DeviceSensorRequest request = new DeviceSensorRequest("test", "test", "test", 1f, 2f);
        DeviceSensorRequest requestWithNull = new DeviceSensorRequest("", "", "", null, null);

        Set<ConstraintViolation<DeviceSensorRequest>> validate = validator.validate(request);
        Set<ConstraintViolation<DeviceSensorRequest>> validateNull = validator.validate(requestWithNull);

        assertAll(
                () -> assertTrue(validate.isEmpty()),
                () -> assertFalse(validateNull.isEmpty())
        );
    }
}
