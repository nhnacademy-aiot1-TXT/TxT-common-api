package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.NotificationRequest;
import kr.co.contxt.commonapi.dto.NotificationResponse;

import java.util.List;

public interface NotificationService {
    List<NotificationResponse> getAllNotifications();

    List<NotificationResponse> getUserNotifications(Long roleId);

    void createNotification(NotificationRequest notificationRequest);
}
