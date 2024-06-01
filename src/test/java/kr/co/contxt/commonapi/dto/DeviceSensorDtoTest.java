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
        DeviceAndSensorAndPlaceNameDto deviceAndSensorAndPlaceNameDto = new DeviceAndSensorAndPlaceNameDto("test", "test", "test");
        DeviceAndSensorAndPlaceNameDto deviceAndSensorAndPlaceNameDtoWithNull = new DeviceAndSensorAndPlaceNameDto("", "", "");

        Set<ConstraintViolation<DeviceAndSensorAndPlaceNameDto>> validate = validator.validate(deviceAndSensorAndPlaceNameDto);
        Set<ConstraintViolation<DeviceAndSensorAndPlaceNameDto>> validateNull = validator.validate(deviceAndSensorAndPlaceNameDtoWithNull);

        assertAll(
                () -> assertTrue(validate.isEmpty()),
                () -> assertFalse(validateNull.isEmpty())
        );
    }

    @Test
    void deviceNameDtoValidation() {
        DeviceAndPlaceNameDto deviceAndPlaceNameDto = new DeviceAndPlaceNameDto("test", "test");
        DeviceAndPlaceNameDto deviceAndPlaceNameDtoWithNull = new DeviceAndPlaceNameDto("", "");

        Set<ConstraintViolation<DeviceAndPlaceNameDto>> validate = validator.validate(deviceAndPlaceNameDto);
        Set<ConstraintViolation<DeviceAndPlaceNameDto>> validateNull = validator.validate(deviceAndPlaceNameDtoWithNull);

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
