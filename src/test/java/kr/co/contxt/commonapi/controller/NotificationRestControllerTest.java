package kr.co.contxt.commonapi.controller;

import kr.co.contxt.commonapi.adapter.UserAdapter;
import kr.co.contxt.commonapi.dto.NotificationRequest;
import kr.co.contxt.commonapi.dto.NotificationResponse;
import kr.co.contxt.commonapi.dto.RoleResponse;
import kr.co.contxt.commonapi.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class NotificationRestControllerTest {
    @Mock
    private UserAdapter userAdapter;
    @Mock
    private NotificationService notificationService;
    @InjectMocks
    private NotificationRestController notificationRestController;

    @Test
    void getNotifications() {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(1L);
        List<NotificationResponse> notificationList = List.of(new NotificationResponse());

        given(userAdapter.getRoleData(anyString())).willReturn(roleResponse);
        given(notificationService.getAllNotifications()).willReturn(notificationList);

        ResponseEntity<List<NotificationResponse>> responseEntity = notificationRestController.getNotifications("testId");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(notificationList, responseEntity.getBody());
    }

    @Test
    void addNotification() {
        NotificationRequest notificationRequest = new NotificationRequest();

        ResponseEntity<Void> responseEntity = notificationRestController.addNotification(notificationRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}