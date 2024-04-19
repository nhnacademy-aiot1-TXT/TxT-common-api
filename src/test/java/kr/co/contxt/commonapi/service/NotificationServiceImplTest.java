package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.NotificationRequest;
import kr.co.contxt.commonapi.dto.NotificationResponse;
import kr.co.contxt.commonapi.entity.Notification;
import kr.co.contxt.commonapi.entity.Role;
import kr.co.contxt.commonapi.repository.NotificationRepository;
import kr.co.contxt.commonapi.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Test
    void getAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        Role role = new Role(1L, null);
        notifications.add(new Notification(1L, role, "Notification 1", null));
        notifications.add(new Notification(1L, role, "Notification 2", null));
        notifications.add(new Notification(1L, role, "Notification 3", null));

        given(notificationRepository.findAll()).willReturn(notifications);

        List<NotificationResponse> result = notificationService.getAllNotifications();

        verify(notificationRepository).findAll();

        assertEquals(notifications.size(), result.size());
        assertEquals(notifications.stream().map(Notification::toDto).collect(Collectors.toList()), result);
    }

    @Test
    void getUserNotifications() {
        Long roleId = 1L;
        List<Notification> notifications = new ArrayList<>();
        Role role = new Role(1L, null);
        notifications.add(new Notification(1L, role, "Notification 1", null));
        notifications.add(new Notification(1L, role, "Notification 2", null));
        notifications.add(new Notification(1L, role, "Notification 3", null));

        given(notificationRepository.findByRole_Id(roleId)).willReturn(notifications);

        List<NotificationResponse> result = notificationService.getUserNotifications(roleId);

        verify(notificationRepository).findByRole_Id(roleId);

        assertEquals(notifications.size(), result.size());
        assertEquals(notifications.stream().map(Notification::toDto).collect(Collectors.toList()), result);
    }

    @Test
    void createNotification() {
        NotificationRequest request = new NotificationRequest();
        request.setContents("Test Notification");

        notificationService.createNotification(request);

        verify(notificationRepository).save(any(Notification.class));
    }
}