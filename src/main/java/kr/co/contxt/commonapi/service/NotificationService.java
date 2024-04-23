package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.NotificationRequest;
import kr.co.contxt.commonapi.dto.NotificationResponse;

import java.util.List;

/**
 * Notification service interface
 *
 * @author jongsikk
 * @version 1.0.0
 */
public interface NotificationService {
    /**
     * Gets all notifications.
     *
     * @return the all notifications
     */
    List<NotificationResponse> getAllNotifications();

    /**
     * roleId에 해당하는 알림만 가져오는 메서드
     *
     * @param roleId the role id
     * @return roleId와 일치하는 notifications
     */
    List<NotificationResponse> getUserNotifications(Long roleId);

    /**
     * Create notification.
     *
     * @param notificationRequest the notification request
     */
    void createNotification(NotificationRequest notificationRequest);
}
