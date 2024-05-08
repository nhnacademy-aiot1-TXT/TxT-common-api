package kr.co.contxt.commonapi.entity;

import kr.co.contxt.commonapi.dto.NotificationResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NotificationTest {
    @Test
    void notificationEntityTest() {
        Notification notification = Notification.builder()
                .notificationId(1L)
                .role(new Role(1L, "ADMIN"))
                .contents("test")
                .time(LocalDateTime.now())
                .build();

        NotificationResponse notificationResponse = notification.toDto();

        assertNotNull(notificationResponse);
        assertEquals(notification.getRole().getRoleId(), notificationResponse.getRoleId());
        assertEquals(notification.getContents(), notificationResponse.getContents());
        assertEquals(notification.getTime(), notificationResponse.getTime());
    }
}