package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Notification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class NotificationRepositoryTest {
    @Mock
    private NotificationRepository notificationRepository;

    @Test
    void findAll() {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());

        given(notificationRepository.findAll()).willReturn(notifications);

        List<Notification> resultList = notificationRepository.findAll();

        assertEquals(3, resultList.size());
    }

    @Test
    void findByRole_Id() {
        Long roleId = 1L;
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());

        given(notificationRepository.findByRole_Id(roleId)).willReturn(notifications);

        List<Notification> result = notificationRepository.findByRole_Id(roleId);

        // Assertions
        assertEquals(3, result.size());

    }
}