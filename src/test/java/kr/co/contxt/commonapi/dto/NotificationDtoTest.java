package kr.co.contxt.commonapi.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotificationDtoTest {
    private static Validator validator;

    @BeforeAll
    public static void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void notificationRequestValidation() {
        NotificationRequest request = new NotificationRequest(LocalDateTime.now(), 1L, "test");
        NotificationRequest requestWithNull = new NotificationRequest(null, null, "");

        Set<ConstraintViolation<NotificationRequest>> validate = validator.validate(request);
        Set<ConstraintViolation<NotificationRequest>> validateNull = validator.validate(requestWithNull);

        assertTrue(validate.isEmpty());
        assertFalse(validateNull.isEmpty());
    }

    @Test
    void notificationResponseValidation() {
        NotificationResponse response = new NotificationResponse(1L, "test", LocalDateTime.now());
        NotificationResponse responseWithNull = new NotificationResponse(null, "", null);

        Set<ConstraintViolation<NotificationResponse>> validate = validator.validate(response);
        Set<ConstraintViolation<NotificationResponse>> validateNull = validator.validate(responseWithNull);

        assertTrue(validate.isEmpty());
        assertFalse(validateNull.isEmpty());
    }
}
